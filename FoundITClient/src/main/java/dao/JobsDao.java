package dao;

import java.util.ArrayList;
import java.util.List;

import model.Job;

public class JobsDao {
	private List<Job> Jobs = new ArrayList<Job>();

	
	public JobsDao() {
	}

	public JobsDao(List<Job> jobs) {
		Jobs = jobs;
	}

	public List<Job> getJobs() {
		return Jobs;
	}

	public void setJobs(List<Job> jobs) {
		Jobs = jobs;
	}

	@Override
	public String toString() {
		return "JobsDao [Jobs=" + Jobs + "]";
	}
	
}
