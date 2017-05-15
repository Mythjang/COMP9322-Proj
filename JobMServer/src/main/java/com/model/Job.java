package com.model;

import java.util.ArrayList;
import java.util.List;

public class Job {
	private String key;
	private String companyName;
	private double salaryRate;
	private String positionType;
	private String location;
	private String detail;
	private List<Link> link = new ArrayList<Link>();
	private String status;
	
		
	
		
		
	
	
	public Job() {
	}


	public Job(String key, String email, String companyName, double salaryRate, String positionType, String location,
			String detail, String status) {
		super();
		this.key = key;
		this.companyName = companyName;
		this.salaryRate = salaryRate;
		this.positionType = positionType;
		this.location = location;
		this.detail = detail;
		this.status = status;
	}


	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getSalaryRate() {
		return salaryRate;
	}
	public void setSalaryRate(double salaryRate) {
		this.salaryRate = salaryRate;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job [key=" + key + ", companyName=" + companyName + ", salaryRate=" + salaryRate + ", positionType="
				+ positionType + ", location=" + location + ", detail=" + detail + ", status=" + status + "]";
	}


	public List<Link> getLink() {
		return link;
	}


	public void setLink(List<Link> link) {
		this.link = link;
	}










	
	
	
}
