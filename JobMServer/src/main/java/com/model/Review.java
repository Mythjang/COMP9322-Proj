package com.model;

public class Review {
	private int revkey;
	private int appID;
	private String details;
	private String comments;
	private String decision;
	
	
	public Review(int revkey, int appID, String details, String comments, String decision) {
		super();
		this.revkey = revkey;
		this.appID = appID;
		this.details = details;
		this.comments = comments;
		this.decision = decision;
	}
	public int getRevkey() {
		return revkey;
	}
	public void setRevkey(int revkey) {
		this.revkey = revkey;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
