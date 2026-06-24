package com.om.project_B.repository;

import com.om.project_B.model.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, UUID> {
}
