package com.om.project_B.repository;

import com.om.project_B.model.BouquetLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BouquetLikeRepository extends JpaRepository<BouquetLike, Long> {

    Optional<BouquetLike> findByUserIdAndBouquetId(
            String userId,
            Long bouquetId
    );

    boolean existsByUserIdAndBouquetId(
            String userId,
            Long bouquetId
    );

    List<BouquetLike> findByUserId(String userId);
}