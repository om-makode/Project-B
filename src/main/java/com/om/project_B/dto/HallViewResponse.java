package com.om.project_B.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HallViewResponse {

    private Long totalViews;
    private Long uniqueUsers;
//    private List<LocalDateTime> viewTimestamps;
//    private LocalDateTime lastTimestamp;

    public HallViewResponse(
            Long totalViews,
            Long uniqueUsers) {

        this.totalViews = totalViews;
        this.uniqueUsers = uniqueUsers;
//        this.lastTimestamp = lastTimestamp;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public Long getUniqueUsers() {
        return uniqueUsers;
    }

//    public LocalDateTime getViewTimestamps() {
//        return lastTimestamp;
//    }
}