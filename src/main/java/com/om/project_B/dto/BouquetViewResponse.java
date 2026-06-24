package com.om.project_B.dto;

public class BouquetViewResponse {

	
	private Long totalViews;
	private Long uniqueUserViews;
	
	
	public BouquetViewResponse(Long totalViews, Long uniqueUserViews) {
		this.totalViews = totalViews;
		this.uniqueUserViews = uniqueUserViews;
	}
	public Long getTotalViews() {
		return totalViews;
	}
	public void setTotalViews(Long totalViews) {
		this.totalViews = totalViews;
	}
	public Long getUniqueUserViews() {
		return uniqueUserViews;
	}
	public void setUniqueUserViews(Long uniqueUserViews) {
		this.uniqueUserViews = uniqueUserViews;
	}
	
	
}
