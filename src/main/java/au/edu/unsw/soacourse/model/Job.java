package au.edu.unsw.soacourse.model;

public class Job {
	private int key;
	private String companyName;
	private double salaryRate;
	private String positionType;
	private String location;
	private String detail;
	private String status;
	
	
	public Job(int key, String companyName, double salaryRate, String positionType, String location, String detail,
			String status) {
		super();
		this.key = key;
		this.companyName = companyName;
		this.salaryRate = salaryRate;
		this.positionType = positionType;
		this.location = location;
		this.detail = detail;
		this.status = status;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
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
	
	
}
