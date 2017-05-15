package dao;

import java.util.ArrayList;
import java.util.List;

import model.Poll;

public class PollsDao {
	
	private List<Poll> Polls = new ArrayList<Poll>();

	
	public PollsDao() {

	}
	public PollsDao(List<Poll> polls) {
		Polls = polls;
	}
	public List<Poll> getPolls() {
		return Polls;
	}

	public void setPolls(List<Poll> polls) {
		Polls = polls;
	}
	
	
	@Override
	public String toString() {
		return "PollsDao [Polls=" + Polls + "]";
	}
}
