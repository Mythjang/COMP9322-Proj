package com.dao;


import java.util.ArrayList;
import java.util.List;

	public enum UserDao {
	    instance;

	    private List<String> users = new ArrayList<String>();

	    private UserDao() {
	    	users.add("i-am-foundit");
	    }

		public List<String> getUsers() {
			return users;
		}

		public void setUsers(List<String> users) {
			this.users = users;
		}

	

	}


	
	
	

