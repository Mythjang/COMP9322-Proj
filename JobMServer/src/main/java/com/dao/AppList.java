package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Application;

public class AppList {

	private List<Application> apps = new ArrayList<Application>();

	
	
	public AppList() {

	}
	

	public AppList(List<Application> apps) {
		this.apps = apps;
	}


	public List<Application> getApps() {
		return apps;
	}

	public void setApps(List<Application> apps) {
		this.apps = apps;
	}

	
}
