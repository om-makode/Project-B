package com.om.project_B.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "uid", updatable = false, nullable = false)
    private String uid; // Firebase Auth UID as the Primary Key
    
    @Column(name = "name") // Made nullable to support initial optional profile states
    private String name;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "address", length = 500) // Allocation headroom for longer address patterns
    private String address;
    
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber; // Matches your strict client-side auth paradigm
    
    @Column(name = "profile_image_path", length = 1024) // Added safety buffer length for deep URLs/S3 keys
    private String profileImagePath; 
    
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // ==========================================
    // GETTERS AND SETTERS
    // ==========================================

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        // Cleaning inputs removes hidden string white spaces when parsing payloads
        this.uid = (uid != null) ? uid.trim() : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email != null) ? email.trim().toLowerCase() : null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}