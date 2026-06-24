package com.om.project_B.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "hall_ratings",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hallId", "userId"})
    }
)
public class HallRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hallId;

    private String userId;
    
    private String userName;

    private Integer rating; // 1-5

    @Column(length = 1000)
    private String feedback;

    private LocalDateTime createdAt;

    public HallRating() {}

    public HallRating(
            Long hallId,
            String userId,
            String userName,
            Integer rating,
            String feedback
    ) {
        this.hallId = hallId;
        this.userId = userId;
        this.userName = userName;
        this.rating = rating;
        this.feedback = feedback;
        this.createdAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHallId() {
		return hallId;
	}

	public void setHallId(Long hallId) {
		this.hallId = hallId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

    // getters setters...
    
}