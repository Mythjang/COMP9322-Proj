package au.edu.unsw.soacourse.dao;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.soacourse.model.Poll;



public class PollList {

	private List<Poll> Polls = new ArrayList<Poll>();

	
	public PollList() {

	}
	public PollList(List<Poll> polls) {
		Polls = polls;
	}
	public List<Poll> getPolls() {
		return Polls;
	}

	public void setPolls(List<Poll> polls) {
		Polls = polls;
	}
}
