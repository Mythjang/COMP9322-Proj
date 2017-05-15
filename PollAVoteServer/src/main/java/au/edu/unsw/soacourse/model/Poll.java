package au.edu.unsw.soacourse.model;



public class Poll {

	private String key;
	private String email;
	private String title;
	private String description;
	private String pollOptionType;
	private String options;
	private String comments;
	private String finalChoice;
	
	
	public Poll() {
	}


	public Poll(String key, String email, String title, String description, String pollOptionType, String options,
			String comments, String finalChoice) {
		super();
		this.key = key;
		this.email = email;
		this.title = title;
		this.description = description;
		this.pollOptionType = pollOptionType;
		this.options = options;
		this.comments = comments;
		this.finalChoice = finalChoice;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPollOptionType() {
		return pollOptionType;
	}


	public void setPollOptionType(String pollOptionType) {
		this.pollOptionType = pollOptionType;
	}


	public String getOptions() {
		return options;
	}


	public void setOptions(String options) {
		this.options = options;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getFinalChoice() {
		return finalChoice;
	}


	public void setFinalChoice(String finalChoice) {
		this.finalChoice = finalChoice;
	}


	@Override
	public String toString() {
		return "poll [key=" + key + ", email=" + email + ", title=" + title + ", description=" + description
				+ ", pollOptionType=" + pollOptionType + ", options=" + options + ", comments=" + comments
				+ ", finalChoice=" + finalChoice + "]";
	}

}
