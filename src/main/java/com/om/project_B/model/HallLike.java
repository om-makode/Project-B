package com.om.project_B.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "hall_likes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "hallId"})
    }
)
public class HallLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // The ID of the user who liked it
    private Long hallId; // The ID of the hall being liked

    public HallLike() {}

    public HallLike(String userId, Long hallId) {
        this.userId = userId;
        this.hallId = hallId;
    }

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Long getHallId() { return hallId; }
    public void setHallId(Long hallId) { this.hallId = hallId; }
}
