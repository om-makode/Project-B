package com.om.project_B.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "bouquet_ratings",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bouquetId", "userId"})
    }
)
public class BouquetRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bouquetId;

    private String userId;

    private String userName;

    private Integer rating;

    @Column(length = 1000)
    private String feedback;

    private LocalDateTime createdAt;

    public BouquetRating() {}

    public BouquetRating(
            Long bouquetId,
            String userId,
            String userName,
            Integer rating,
            String feedback
    ) {
        this.bouquetId = bouquetId;
        this.userId = userId;
        this.userName = userName;
        this.rating = rating;
        this.feedback = feedback;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Long getBouquetId() { return bouquetId; }
    public void setBouquetId(Long bouquetId) { this.bouquetId = bouquetId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}