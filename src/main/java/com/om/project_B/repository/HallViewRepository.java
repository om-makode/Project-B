package com.om.project_B.repository;

import com.om.project_B.model.HallView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface HallViewRepository extends JpaRepository<HallView, Long> {

    long countByHallId(Long hallId);

    @Query("SELECT COUNT(DISTINCT hv.userId) FROM HallView hv WHERE hv.hallId = :hallId")
    long countDistinctUserIdByHallId(@Param("hallId") Long hallId);

    List<HallView> findByHallIdOrderByViewedAtDesc(Long hallId);
}