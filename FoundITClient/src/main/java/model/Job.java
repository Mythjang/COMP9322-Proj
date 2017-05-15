package model;


public class Job {
	private long key;
	private String location;
	private String status = "open";
	private double salaryRate;
	private String positionType;
	private String companyName;
	private String detail;
	
	
	public Job() {
	}


	public Job(long key, String location, String status, double salaryRate, String positionType, String companyName,
			String detail) {
		this.key = key;
		this.location = location;
		this.status = status;
		this.salaryRate = salaryRate;
		this.positionType = positionType;
		this.companyName = companyName;
		this.detail = detail;
	}






	public long getKey() {
		return key;
	}






	public void setKey(long key) {
		this.key = key;
	}






	public String getLocation() {
		return location;
	}






	public void setLocation(String location) {
		this.location = location;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
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






	public String getCompanyName() {
		return companyName;
	}






	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}






	public String getDetail() {
		return detail;
	}






	public void setDetail(String detail) {
		this.detail = detail;
	}






	@Override
	public String toString() {
		return "Job [key=" + key + ", companyName=" + companyName + ", salaryRate=" + salaryRate + ", positionType="
				+ positionType + ", location=" + location + ", detail=" + detail + ", status=" + status + "]";
	}




	
	
	
}
