package com.om.project_B.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "bouquet_likes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "bouquetId"})
    }
)
public class BouquetLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Long bouquetId;

    public BouquetLike() {
    }

    public BouquetLike(String userId, Long bouquetId) {
        this.userId = userId;
        this.bouquetId = bouquetId;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(Long bouquetId) {
        this.bouquetId = bouquetId;
    }
}