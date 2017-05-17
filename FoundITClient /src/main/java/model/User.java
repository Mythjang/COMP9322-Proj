package model;


public class User {

	private String type=null;
	private String email=null;
	private String password=null;
	private String dob=null;
	private String name=null;
	private String title = null;
	private String currentPosition = null;
	private String education = null;
	private String pastExperience = null;
	private String professionalSkills = null;
	private String workFor = null;
	public User(){

	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPastExperience() {
		return pastExperience;
	}
	public void setPastExperience(String pastExperience) {
		this.pastExperience = pastExperience;
	}
	public String getProfessionalSkills() {
		return professionalSkills;
	}
	public void setProfessionalSkills(String professionalSkills) {
		this.professionalSkills = professionalSkills;
	}
	
	public String getWorkFor() {
		return workFor;
	}
	public void setWorkFor(String workFor) {
		this.workFor = workFor;
	}
	@Override
	public String toString() {
		return "User [type=" + type + ", email=" + email + ", password=" + password + ", dob=" + dob + ", name=" + name
				+ ", title=" + title + ", currentPosition=" + currentPosition + ", education=" + education
				+ ", pastExperience=" + pastExperience + ", professionalSkills=" + professionalSkills + ", workFor="
				+ workFor + "]";
	}


}
