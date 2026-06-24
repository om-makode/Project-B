package com.om.project_B.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "en", column = @Column(name = "hall_name_en")),
        @AttributeOverride(name = "mr", column = @Column(name = "hall_name_mr")),
        @AttributeOverride(name = "hi", column = @Column(name = "hall_name_hi"))
    })
    @JsonProperty("hallName")
    private LocalizedString hallName;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "en", column = @Column(name = "location_address_en")),
        @AttributeOverride(name = "mr", column = @Column(name = "location_address_mr")),
        @AttributeOverride(name = "hi", column = @Column(name = "location_address_hi"))
    })
    @JsonProperty("locationAddress")
    private LocalizedString locationAddress;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "en", column = @Column(name = "description_en", length = 1000)),
        @AttributeOverride(name = "mr", column = @Column(name = "description_mr", length = 1000)),
        @AttributeOverride(name = "hi", column = @Column(name = "description_hi", length = 1000))
    })
    @JsonProperty("description")
    private LocalizedString description;
    
    private String ownerContact;
    private Double latitude;
    private Double longitude;
    private Integer seatingAvailability;
    private String hallSize;
    private Integer roomCount;
    private Integer parkingCars;
    private Integer parkingBikes;
    private BigDecimal pricePerDay;
    private BigDecimal lightBillPerUnit;
    
    // CRITICAL: Explicitly map the boolean properties to match Swift
    @JsonProperty("isACAvailable")
    private Boolean isACAvailable;

    @JsonProperty("isPowerBackupAvailable")
    private Boolean isPowerBackupAvailable;
    
    private Boolean allowsExternalCatering;
    private Boolean hasSoundSystem;
    private String cancellationPolicy;
    private String mainScreenImagePath;

    @ElementCollection
    @CollectionTable(name = "hall_gallery_images", joinColumns = @JoinColumn(name = "hall_id"))
    @Column(name = "image_path")
    private List<String> galleryImagePaths;
    
    @Column(nullable = false)
    private Long viewCount = 0L;

    // --- Default Constructor ---
    public Hall() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalizedString getHallName() { return hallName; }
    public void setHallName(LocalizedString hallName) { this.hallName = hallName; }

    public LocalizedString getLocationAddress() { return locationAddress; }
    public void setLocationAddress(LocalizedString locationAddress) { this.locationAddress = locationAddress; }

    public LocalizedString getDescription() { return description; }
    public void setDescription(LocalizedString description) { this.description = description; }

    public String getOwnerContact() { return ownerContact; }
    public void setOwnerContact(String ownerContact) { this.ownerContact = ownerContact; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Integer getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(Integer seatingAvailability) { this.seatingAvailability = seatingAvailability; }

    public String getHallSize() { return hallSize; }
    public void setHallSize(String hallSize) { this.hallSize = hallSize; }

    public Integer getRoomCount() { return roomCount; }
    public void setRoomCount(Integer roomCount) { this.roomCount = roomCount; }

    public Integer getParkingCars() { return parkingCars; }
    public void setParkingCars(Integer parkingCars) { this.parkingCars = parkingCars; }

    public Integer getParkingBikes() { return parkingBikes; }
    public void setParkingBikes(Integer parkingBikes) { this.parkingBikes = parkingBikes; }

    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }

    public BigDecimal getLightBillPerUnit() { return lightBillPerUnit; }
    public void setLightBillPerUnit(BigDecimal lightBillPerUnit) { this.lightBillPerUnit = lightBillPerUnit; }

    public Boolean getIsACAvailable() { return isACAvailable; }
    public void setIsACAvailable(Boolean isACAvailable) { this.isACAvailable = isACAvailable; }

    public Boolean getIsPowerBackupAvailable() { return isPowerBackupAvailable; }
    public void setIsPowerBackupAvailable(Boolean isPowerBackupAvailable) { this.isPowerBackupAvailable = isPowerBackupAvailable; }

    public Boolean getAllowsExternalCatering() { return allowsExternalCatering; }
    public void setAllowsExternalCatering(Boolean allowsExternalCatering) { this.allowsExternalCatering = allowsExternalCatering; }

    public Boolean getHasSoundSystem() { return hasSoundSystem; }
    public void setHasSoundSystem(Boolean hasSoundSystem) { this.hasSoundSystem = hasSoundSystem; }

    public String getCancellationPolicy() { return cancellationPolicy; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }

    public String getMainScreenImagePath() { return mainScreenImagePath; }
    public void setMainScreenImagePath(String mainScreenImagePath) { this.mainScreenImagePath = mainScreenImagePath; }

    public List<String> getGalleryImagePaths() { return galleryImagePaths; }
    public void setGalleryImagePaths(List<String> galleryImagePaths) { this.galleryImagePaths = galleryImagePaths; }
    
    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}