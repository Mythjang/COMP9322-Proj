package model;

public class Review {
	private String reKey;
	private String appKey;
	private String reviewerEmail;
	private String decision;
	private String comments;

	
	public Review() {
	}
	public String getReKey() {
		return reKey;
	}
	public void setReKey(String key) {
		this.reKey = key;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getReviewerEmail() {
		return reviewerEmail;
	}
	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}


	
	
	
}
