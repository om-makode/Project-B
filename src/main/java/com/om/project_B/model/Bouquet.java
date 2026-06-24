package com.om.project_B.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bouquets")
public class Bouquet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "bouquet_flowers", joinColumns = @JoinColumn(name = "bouquet_id"))
    @Column(name = "flower")
    private List<String> flowersUsed;

    private String sellerName;
    private String sellerAddress;
    private Double latitude;
    private Double longitude;
    private Double price;
    private String availability;
    private Double sizeWidth;
    private Double sizeHeight;
    private String mainScreenImage;

    @ElementCollection
    @CollectionTable(name = "bouquet_gallery", joinColumns = @JoinColumn(name = "bouquet_id"))
    @Column(name = "image_url")
    private List<String> galleryImages;

    @Column(length = 2000) // Expanded length for descriptions
    private String description;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFlowersUsed() {
		return flowersUsed;
	}

	public void setFlowersUsed(List<String> flowersUsed) {
		this.flowersUsed = flowersUsed;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Double getSizeWidth() {
		return sizeWidth;
	}

	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	public Double getSizeHeight() {
		return sizeHeight;
	}

	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	public String getMainScreenImage() {
		return mainScreenImage;
	}

	public void setMainScreenImage(String mainScreenImage) {
		this.mainScreenImage = mainScreenImage;
	}

	public List<String> getGalleryImages() {
		return galleryImages;
	}

	public void setGalleryImages(List<String> galleryImages) {
		this.galleryImages = galleryImages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}