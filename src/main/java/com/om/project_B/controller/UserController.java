package com.om.project_B.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.om.project_B.model.User;
import com.om.project_B.repository.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sync")
    public ResponseEntity<?> syncUser(@RequestBody User user, @RequestHeader(value="Authorization", required = false) String token) {
        // Debugging logs to verify incoming data in your Spring Boot console
        System.out.println("Received Sync Request for UID: " + user.getUid());
        System.out.println("Received Mobile Number: " + user.getMobileNumber());
        System.out.println("Received Name: " + user.getName());

        // 1. Quick Validation Guard
        if (user.getUid() == null || user.getMobileNumber() == null) {
            return ResponseEntity.badRequest().body("Error: UID or Mobile Number cannot be null.");
        }

        try {
            // 2. Prevent Overwriting Existing Profiles
            // If the user already logged in before, we want to update/keep their profile intact 
            // rather than completely resetting their address, role, or name back to defaults.
            Optional<User> existingUser = userRepository.findById(user.getUid());
            if (existingUser.isPresent()) {
                User dbUser = existingUser.get();
                // Update only fields that might change safely, keeping original registrations intact
                dbUser.setMobileNumber(user.getMobileNumber());
                System.out.println("Updating existing user info...");
                return ResponseEntity.ok(userRepository.save(dbUser));
            }

            // 3. Fallback values for new records to prevent Hibernate constraint drops
            if (user.getName() == null || user.getName().isEmpty()) {
                user.setName("New User");
            }

            User savedUser = userRepository.save(user);
            System.out.println("Successfully saved new user to DB!");
            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            System.err.println("Database saving failed layout error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("DB Sync Failure: " + e.getLocalizedMessage());
        }
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody User updatedUserData) {
        System.out.println("Processing explicit profile update for UID: " + updatedUserData.getUid());
        
        if (updatedUserData.getUid() == null) {
            return ResponseEntity.badRequest().body("Error: Missing UID for profile update.");
        }

        return userRepository.findById(updatedUserData.getUid())
            .map(existingUser -> {
                // Map modifications over from the client payload safely
                if (updatedUserData.getName() != null) existingUser.setName(updatedUserData.getName());
                if (updatedUserData.getEmail() != null) existingUser.setEmail(updatedUserData.getEmail());
                if (updatedUserData.getAddress() != null) existingUser.setAddress(updatedUserData.getAddress());
                if (updatedUserData.getProfileImagePath() != null) existingUser.setProfileImagePath(updatedUserData.getProfileImagePath());
                
                User savedUser = userRepository.save(existingUser);
                System.out.println("Profile successfully updated in database!");
                return ResponseEntity.ok(savedUser);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{uid}")
    public ResponseEntity<User> getUser(@PathVariable String uid) {
        return userRepository.findById(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uid) {
        userRepository.deleteById(uid);
        return ResponseEntity.noContent().build();
    }
}