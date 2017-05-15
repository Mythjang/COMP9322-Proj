package model;


public class User {

	private String type="";
	private String companyName ="";
	private String email="";
	private String password="";
	private String dob="";
	private String name="";
	private String sex = "";
	private String introduction="";

	public User(){

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [type=" + type + ", companyName=" + companyName + ", email=" + email + ", password=" + password
				+ ", dob=" + dob + ", name=" + name + ", sex=" + sex + ", introduction=" + introduction + "]";
	}

}
