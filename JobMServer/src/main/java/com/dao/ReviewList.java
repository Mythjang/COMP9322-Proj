package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Review;

public class ReviewList {

	private List<Review> rev = new ArrayList<Review>();

	public ReviewList() {

	}

	public List<Review> getRev() {
		return rev;
	}

	public void setRev(List<Review> rev) {
		this.rev = rev;
	}

	
	
}
