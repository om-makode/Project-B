package com.om.project_B.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hall_bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hall_id", nullable = false)
    private Long hallId;

    @Column(name = "start_date_ms", nullable = false)
    private Long startDateMs; // Stored in milliseconds format

    @Column(name = "end_date_ms", nullable = false)
    private Long endDateMs;   // Stored in milliseconds format

    // --- Constructors ---
    public Booking() {}

    public Booking(Long hallId, Long startDateMs, Long endDateMs) {
        this.hallId = hallId;
        this.startDateMs = startDateMs;
        this.endDateMs = endDateMs;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getHallId() { return hallId; }
    public void setHallId(Long hallId) { this.hallId = hallId; }

    public Long getStartDateMs() { return startDateMs; }
    public void setStartDateMs(Long startDateMs) { this.startDateMs = startDateMs; }

    public Long getEndDateMs() { return endDateMs; }
    public void setEndDateMs(Long endDateMs) { this.endDateMs = endDateMs; }
}