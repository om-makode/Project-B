package com.om.project_B.dto;

import java.util.List;

public class HallRatingResponse {

    private Double averageRating;

    private Long totalRatings;

    private List<FeedbackResponse> feedbacks;

    public HallRatingResponse(
            Double averageRating,
            Long totalRatings,
            List<FeedbackResponse> feedbacks
    ) {
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.feedbacks = feedbacks;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Long getTotalRatings() {
        return totalRatings;
    }

    public List<FeedbackResponse> getFeedbacks() {
        return feedbacks;
    }
}