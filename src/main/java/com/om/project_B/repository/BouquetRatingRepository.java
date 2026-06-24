package com.om.project_B.repository;

import com.om.project_B.model.BouquetRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BouquetRatingRepository
        extends JpaRepository<BouquetRating, Long> {

    List<BouquetRating> findByBouquetId(Long bouquetId);

    Optional<BouquetRating> findByBouquetIdAndUserId(
            Long bouquetId,
            String userId
    );
}