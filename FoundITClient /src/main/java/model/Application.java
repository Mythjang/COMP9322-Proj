package model;


public class Application {
	
	private String appKey=null;
	private String jobId=null;
	private String email=null;
	private String dob=null;
	private String name=null;
	private String title = null;
	private String currentPosition = null;
	private String education =null;
	private String pastExperience =null;
	private String professionalSkills =null ;
	private String coverLetter= null;
	
	public Application() {
		
	}
	


	public Application(String appKey, String jobId, String email, String dob, String name, String title,
			String currentPosition, String education, String pastExperience, String professionalSkills,
			String coverLetter) {
		super();
		this.appKey = appKey;
		this.jobId = jobId;
		this.email = email;
		this.dob = dob;
		this.name = name;
		this.title = title;
		this.currentPosition = currentPosition;
		this.education = education;
		this.pastExperience = pastExperience;
		this.professionalSkills = professionalSkills;
		this.coverLetter = coverLetter;
	}






	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}



	@Override
	public String toString() {
		return "Application [appKey=" + appKey + ", jobId=" + jobId + ", email=" + email + ", dob=" + dob + ", name="
				+ name + ", title=" + title + ", currentPosition=" + currentPosition + ", education=" + education
				+ ", pastExperience=" + pastExperience + ", professionalSkills=" + professionalSkills + ", coverLetter="
				+ coverLetter + "]";
	}








	




	
	
	
}

