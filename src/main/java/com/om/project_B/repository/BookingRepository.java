package com.om.project_B.repository;

import com.om.project_B.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Fetches all bookings belonging to a specific hall
    List<Booking> findByHallId(Long hallId);
}