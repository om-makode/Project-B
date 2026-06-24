package com.om.project_B.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.om.project_B.model.BouquetViews;

public interface BouquetViewsRepository extends JpaRepository<BouquetViews, Long> {

	
	long countByBouquetId(Long bouquetId);
	
	@Query("SELECT COUNT(DISTINCT bv.userId) FROM BouquetViews bv WHERE bv.bouquetId = :bouquetId")
	long countDistinctUserIdByBouquetId(@Param("bouquetId") Long bouquetId);
	
//	List<BouquetViews> findByBouquetIdOrderByViewsAtDesc(Long bouquetId);
	List<BouquetViews> findByBouquetIdOrderByViewdAtDesc(Long bouquetId);
}
