package com.om.project_B.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bouquet_views")
public class BouquetViews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bouquetId;
	private String userId;
	private LocalDateTime viewdAt;
	
	public BouquetViews() {}

	public BouquetViews(Long bouquetId, String userId) {
		this.bouquetId = bouquetId;
		this.userId = userId;
		this.viewdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBouquetId() {
		return bouquetId;
	}

	public void setBouquetId(Long bouquetId) {
		this.bouquetId = bouquetId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getViewdAt() {
		return viewdAt;
	}

	public void setViewdAt(LocalDateTime viewdAt) {
		this.viewdAt = viewdAt;
	}
	
	
	

}
