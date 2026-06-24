package com.om.project_B.controller;

import com.om.project_B.dto.BouquetRatingRequest;
import com.om.project_B.dto.BouquetRatingResponse;
import com.om.project_B.dto.BouquetViewResponse;
import com.om.project_B.dto.FeedbackResponse;
import com.om.project_B.model.Bouquet;
import com.om.project_B.model.BouquetLike;
import com.om.project_B.model.BouquetRating;
import com.om.project_B.model.BouquetViews;
import com.om.project_B.repository.BouquetLikeRepository;
import com.om.project_B.repository.BouquetRatingRepository;
import com.om.project_B.repository.BouquetViewsRepository;
import com.om.project_B.service.BouquetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bouquets")
public class BouquetController {

    private final BouquetService service;
    private final BouquetLikeRepository bouquetLikeRepository;
    private final BouquetRatingRepository bouquetRatingRepository;
    private final BouquetViewsRepository bouquetViewsRepository;

    public BouquetController(BouquetService service, BouquetLikeRepository bouquetLikeRepository, BouquetRatingRepository bouquetRatingRepository, BouquetViewsRepository bouquetViewsRepository) {
        this.service = service;
        this.bouquetLikeRepository = bouquetLikeRepository;
        this.bouquetRatingRepository = bouquetRatingRepository;
        this.bouquetViewsRepository = bouquetViewsRepository;
    }

    @PostMapping
    public ResponseEntity<Bouquet> createBouquet(@RequestBody Bouquet bouquet) {
        return new ResponseEntity<>(service.createBouquet(bouquet), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bouquet>> getAllBouquets() {
        return ResponseEntity.ok(service.getAllBouquets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bouquet> getBouquetById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getBouquetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bouquet> updateBouquet(@PathVariable UUID id, @RequestBody Bouquet bouquet) {
        return ResponseEntity.ok(service.updateBouquet(id, bouquet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBouquet(@PathVariable UUID id) {
        service.deleteBouquet(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
    //=====================================================
    //Bouquet Likes
    
    @PostMapping("/{id}/like")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long id,
            @RequestParam String userId,
            @RequestParam Boolean isLiked) {

        java.util.Optional<BouquetLike> existingLike =
                bouquetLikeRepository.findByUserIdAndBouquetId(userId, id);

        if (isLiked) {

            if (existingLike.isEmpty()) {
                bouquetLikeRepository.save(
                        new BouquetLike(userId, id)
                );
            }

            return ResponseEntity.ok("Liked successfully");

        } else {

            existingLike.ifPresent(bouquetLikeRepository::delete);

            return ResponseEntity.ok("Unliked successfully");
        }
    }
    
    @GetMapping("/{id}/like/status")
    public ResponseEntity<Boolean> getLikeStatus(
            @PathVariable Long id,
            @RequestParam String userId) {

        boolean isLiked =
                bouquetLikeRepository.existsByUserIdAndBouquetId(
                        userId,
                        id
                );

        return ResponseEntity.ok(isLiked);
    }
    
    @GetMapping("/liked")
    public ResponseEntity<List<Long>> getLikedBouquets(
            @RequestParam String userId) {

        List<Long> likedBouquetIds =
                bouquetLikeRepository
                        .findByUserId(userId)
                        .stream()
                        .map(BouquetLike::getBouquetId)
                        .toList();

        return ResponseEntity.ok(likedBouquetIds);
    }
    
    //==================================================================================================
    //Bouquet Rating
    
    
    @PostMapping("/{id}/rating")
    public ResponseEntity<String> submitRating(
            @PathVariable Long id,
            @RequestParam String userId,
            @RequestBody BouquetRatingRequest request) {

        String userName = request.getUserName();

        if (userName == null || userName.isBlank()) {
            userName = "Anonymous";
        }

        BouquetRating rating =
                bouquetRatingRepository
                        .findByBouquetIdAndUserId(id, userId)
                        .orElse(null);

        if (rating == null) {

            bouquetRatingRepository.save(
                    new BouquetRating(
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

            bouquetRatingRepository.save(rating);
        }

        return ResponseEntity.ok("Rating saved");
    }
    
    
    @GetMapping("/{id}/rating")
    public ResponseEntity<BouquetRatingResponse> getRating(
            @PathVariable Long id) {

        List<BouquetRating> ratings =
                bouquetRatingRepository.findByBouquetId(id);

        long totalRatings = ratings.size();

        double averageRating = ratings.stream()
                .mapToInt(BouquetRating::getRating)
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
                new BouquetRatingResponse(
                        Math.round(averageRating * 10.0) / 10.0,
                        totalRatings,
                        feedbacks
                )
        );
    }
    
    //===================================================================
    //Bouquet Views
    
    @PostMapping("{id}/view")
    public ResponseEntity<String> resordView(
    		@PathVariable Long id,
    		@RequestParam String userId){
    		
    	bouquetViewsRepository.save(new BouquetViews(id, userId));
    	
    	return ResponseEntity.ok("View Recorded");
    }
    
    @GetMapping("{id}/views")
    public ResponseEntity<BouquetViewResponse> getViewCount(@PathVariable Long id){
    	
    	long totalViews = bouquetViewsRepository.countByBouquetId(id);
    	long uniqueUsers = bouquetViewsRepository.countDistinctUserIdByBouquetId(id);
    	
    	return ResponseEntity.ok(
    			new BouquetViewResponse(totalViews, uniqueUsers));
    }
    
    
}