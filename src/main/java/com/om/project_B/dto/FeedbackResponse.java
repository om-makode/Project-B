package com.om.project_B.dto;

public class FeedbackResponse {

    private String userId;
    private String userName;
    private String feedback;
    private Integer rating;

    public FeedbackResponse(
            String userId,
            String userName,
            String feedback,
            Integer rating
    ) {
        this.userId = userId;
        this.userName = userName;
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFeedback() {
        return feedback;
    }
    
    public Integer getRating() {
    	return rating;
    }
}