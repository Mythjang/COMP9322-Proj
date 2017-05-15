package com.model;

public class Application {
	private int appkey;
	private String jobID;
	private String details;
	private double coverletter;
	private String status;
	
	
	
	public Application(int appkey, String jobID, String details, double coverletter, String status) {
		super();
		this.appkey = appkey;
		this.jobID = jobID;
		this.details = details;
		this.coverletter = coverletter;
		this.status = status;
	}
	public int getAppkey() {
		return appkey;
	}
	public void setAppkey(int appkey) {
		this.appkey = appkey;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public double getCoverletter() {
		return coverletter;
	}
	public void setCoverletter(double coverletter) {
		this.coverletter = coverletter;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

