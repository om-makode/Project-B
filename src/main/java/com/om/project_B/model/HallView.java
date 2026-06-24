package com.om.project_B.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hall_views")
public class HallView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hallId;

    private String userId;

    private LocalDateTime viewedAt;

    public HallView() {}

    public HallView(Long hallId, String userId) {
        this.hallId = hallId;
        this.userId = userId;
        this.viewedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}