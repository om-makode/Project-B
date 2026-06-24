package com.om.project_B.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.om.project_B.dto.FeedbackResponse;
import com.om.project_B.dto.HallRatingRequest;
import com.om.project_B.dto.HallRatingResponse;
import com.om.project_B.dto.HallViewResponse;
import com.om.project_B.model.Booking;
import com.om.project_B.model.Hall;
import com.om.project_B.model.HallLike;
import com.om.project_B.model.HallRating;
import com.om.project_B.model.HallView;
import com.om.project_B.repository.BookingRepository;
import com.om.project_B.repository.HallLikeRepository;
import com.om.project_B.repository.HallRatingRepository;
import com.om.project_B.repository.HallViewRepository;
import com.om.project_B.service.HallService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    private final HallService hallService;
    private final HallLikeRepository hallLikeRepository;
    private final BookingRepository bookingRepository;
    private final HallViewRepository hallViewRepository;
    private final HallRatingRepository hallRatingRepository;

    public HallController(HallService hallService, HallLikeRepository hallLikeRepository, BookingRepository bookingRepository, HallViewRepository hallViewRepository, HallRatingRepository hallRatingRepository) {
        this.hallService = hallService;
        this.hallLikeRepository = hallLikeRepository;
        this.bookingRepository = bookingRepository;
        this.hallViewRepository = hallViewRepository;
        this.hallRatingRepository = hallRatingRepository;
    }

    @PostMapping
    public Hall create(@RequestBody Hall hall) {
        return hallService.createHall(hall);
    }

    @GetMapping
    public List<Hall> getAll() {
        return hallService.getAllHalls();
    }

    @GetMapping("/{id}")
    public Hall getById(@PathVariable Long id) {
        return hallService.getHallById(id);
    }

    @PutMapping("/{id}")
    public Hall update(@PathVariable Long id, @RequestBody Hall hall) {
        return hallService.updateHall(id, hall);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hallService.deleteHall(id);
    }
    
    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder", defaultValue = "halls") String folder) throws Exception {
        
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        // 1. Establish separate directories: "uploads/halls" or "uploads/profiles"
        String uploadDir = "uploads/" + folder + "/";
        File directory = new File(uploadDir);
        
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // 2. Format unique name using timestamps to prevent overwrites
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        
        // 3. Write binary streams to folder location safely
        Files.copy(file.getInputStream(), Paths.get(filePath));
        
        // Return resource URL back to Alamofire
        return filePath;
    }
    
   // Like Status Operations ------------------------------------------------------------
    
    @PostMapping("/{id}/like")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long id, 
            @RequestParam String userId, 
            @RequestParam Boolean isLiked) {
            
        java.util.Optional<HallLike> existingLike = hallLikeRepository.findByUserIdAndHallId(userId, id);

        if (isLiked) {
            // Wants to LIKE: Only save if it doesn't already exist
            if (existingLike.isEmpty()) {
                hallLikeRepository.save(new HallLike(userId, id));
            }
            return ResponseEntity.ok("Liked successfully");
        } else {
            // Wants to UNLIKE: Remove it if it exists
            existingLike.ifPresent(hallLikeRepository::delete);
            return ResponseEntity.ok("Unliked successfully");
        }
    }
    @GetMapping("/{id}/like/status")
    public ResponseEntity<Boolean> getLikeStatus(
            @PathVariable Long id, 
            @RequestParam String userId) {
        
        boolean isLiked = hallLikeRepository.existsByUserIdAndHallId(userId, id);
        return ResponseEntity.ok(isLiked);
    }
    
    @GetMapping("/liked")
    public ResponseEntity<List<Long>> getLikedHalls(
            @RequestParam String userId
    ) {

        List<Long> likedHallIds =
                hallLikeRepository
                        .findByUserId(userId)
                        .stream()
                        .map(HallLike::getHallId)
                        .toList();

        return ResponseEntity.ok(likedHallIds);
    }
    
    // -------------------------------------------------------------------------------------
    
    // Booking Status Operations -----------------------------------------------------------
    
    @PostMapping("/{id}/bookings")
    public ResponseEntity<Booking> createBooking(
            @PathVariable Long id, 
            @RequestBody Booking bookingRequest) {
        
        // Explicitly pair the booking request to the Path Variable Hall ID
        bookingRequest.setHallId(id);
        Booking savedBooking = bookingRepository.save(bookingRequest);
        return ResponseEntity.ok(savedBooking);
    }

    /**
     * 2. READ: Get all booked intervals (Start & End dates) for a specific hall
     * GET http://localhost:8081/api/halls/{id}/bookings
     */
    @GetMapping("/{id}/bookings")
    public ResponseEntity<List<Booking>> getBookedDatesByHall(@PathVariable Long id) {
        List<Booking> bookings = bookingRepository.findByHallId(id);
        return ResponseEntity.ok(bookings);
    }

    /**
     * 3. UPDATE: Modify an existing booking's properties by its unique booking ID
     * PUT http://localhost:8081/api/halls/{id}/bookings/{bookingId}
     */
    @PutMapping("/{id}/bookings/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @PathVariable Long bookingId,
            @RequestBody Booking updatedBookingDetails) {
        
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking assignment not found with ID: " + bookingId));
        
        // Update structural metrics
        existingBooking.setStartDateMs(updatedBookingDetails.getStartDateMs());
        existingBooking.setEndDateMs(updatedBookingDetails.getEndDateMs());
        existingBooking.setHallId(id); // Safety fallback assertion
        
        Booking savedUpdates = bookingRepository.save(existingBooking);
        return ResponseEntity.ok(savedUpdates);
    }

    /**
     * 4. DELETE: Cancel/Remove an existing booking footprint
     * DELETE http://localhost:8081/api/halls/{id}/bookings/{bookingId}
     */
    @DeleteMapping("/{id}/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id, @PathVariable Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Booking assignment not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
        return ResponseEntity.ok("Booking dropped successfully");
    }
    
    //--------------hall view count---------------------------------------------------------
    
    @PostMapping("/{id}/view")
    public ResponseEntity<String> recordView(
            @PathVariable Long id,
            @RequestParam String userId) {
    	System.out.println("Incoming view for Hall " + id + " from User ID: [" + userId + "]");
        // 1. Simply save the view record (Tracks every unique timestamp and user)
        hallViewRepository.save(new HallView(id, userId));

        return ResponseEntity.ok("View recorded");
    }

    @GetMapping("/{id}/views")
    public ResponseEntity<HallViewResponse> getViewCount(@PathVariable Long id) {

        long totalViews = hallViewRepository.countByHallId(id);
        long uniqueUsers = hallViewRepository.countDistinctUserIdByHallId(id);

        return ResponseEntity.ok(
                new HallViewResponse(
                        totalViews,
                        uniqueUsers
                )
        );
    }
    
    //----------Hall Rating -------------------------------------------------------
    
    @PostMapping("/{id}/rating")
    public ResponseEntity<String> submitRating(
            @PathVariable Long id,
            @RequestParam String userId,
            @RequestBody HallRatingRequest request) {

        String userName = request.getUserName();

        if (userName == null || userName.isBlank()) {
            userName = "Anonymous";
        }

        HallRating rating =
                hallRatingRepository
                        .findByHallIdAndUserId(id, userId)
                        .orElse(null);

        if (rating == null) {

            hallRatingRepository.save(
                    new HallRating(
                            id,
                            userId,
                            userName,
                            request.getRating(),
                            request.getFeedback()
                    )
            );

        } else {

            rating.setUserName(userName);
            rating.setRating(request.getRating());
            rating.setFeedback(request.getFeedback());

            hallRatingRepository.save(rating);
        }

        return ResponseEntity.ok("Rating saved");
    }
    
    @GetMapping("/{id}/rating")
    public ResponseEntity<HallRatingResponse> getRating(
            @PathVariable Long id) {

        List<HallRating> ratings =
                hallRatingRepository.findByHallId(id);

        long totalRatings = ratings.size();

        double averageRating = ratings.stream()
                .mapToInt(HallRating::getRating)
                .average()
                .orElse(0.0);

        List<FeedbackResponse> feedbacks =
                ratings.stream()
                        .filter(r ->
                                r.getFeedback() != null &&
                                !r.getFeedback().isBlank())
                        .map(r -> new FeedbackResponse(
                                r.getUserId(),
                                r.getUserName() == null || r.getUserName().isBlank()
                                        ? "Anonymous"
                                        : r.getUserName(),
                                r.getFeedback(),
                                r.getRating()
                        ))
                        .toList();

        return ResponseEntity.ok(
                new HallRatingResponse(
                        Math.round(averageRating * 10.0) / 10.0,
                        totalRatings,
                        feedbacks
                )
        );
    }
}