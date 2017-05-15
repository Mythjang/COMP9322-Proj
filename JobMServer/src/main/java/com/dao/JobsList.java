package com.dao;



import java.util.ArrayList;
import java.util.List;

import com.model.Job;





public class JobsList {
	
	private List<Job> Jobs = new ArrayList<Job>();

	
	public JobsList() {

	}
	public JobsList(List<Job> jobs) {
		Jobs = jobs;
	}
	public List<Job> getJobs() {
		return Jobs;
	}

	public void setJobs(List<Job> jobs) {
		Jobs = jobs;
	}
}
