package com.om.project_B.repository;


import com.om.project_B.model.HallLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// CRITICAL: Make sure "extends JpaRepository<HallLike, Long>" is added here
@Repository
public interface HallLikeRepository extends JpaRepository<HallLike, Long> {
    
    Optional<HallLike> findByUserIdAndHallId(String userId, Long hallId);
    
    boolean existsByUserIdAndHallId(String userId, Long hallId);
    List<HallLike> findByUserId(String userId);
}