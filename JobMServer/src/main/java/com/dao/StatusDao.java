package com.dao;


import java.util.HashMap;
import java.util.Map;

	public enum StatusDao {
	    instance;
		Map<String,Integer> status = new HashMap<String,Integer>();
		

	    private StatusDao() {
	    	status.put("open", 1);
	    	status.put("inReview", 2);
	    	status.put("close", 3);
	    }


		public Map<String, Integer> getStatus() {
			return status;
		}


		public void setStatus(Map<String, Integer> status) {
			this.status = status;
		}



	

	}


	
	
	

