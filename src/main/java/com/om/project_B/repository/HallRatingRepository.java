package com.om.project_B.repository;

import com.om.project_B.model.HallRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HallRatingRepository
        extends JpaRepository<HallRating, Long> {

    Optional<HallRating> findByHallIdAndUserId(
            Long hallId,
            String userId
    );

    List<HallRating> findByHallId(Long hallId);

    long countByHallId(Long hallId);
}