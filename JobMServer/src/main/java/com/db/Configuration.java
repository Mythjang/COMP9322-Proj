package com.db;

public class Configuration {

	  
	 public String DB_URL;
	  
	 public String DB_DRIVER;
	  
	 public Integer DB_MAX_CONNECTIONS;
	  
	 private static Configuration configuration = new Configuration();
	 
	 public Configuration(){
		  init();
		 }
	 public static Configuration getInstance(){ 
	  return configuration;
	 }
	  
	 private void init() {
	  DB_URL = "jdbc:sqlite:jobsManage.db";
	  DB_DRIVER = "org.sqlite.JDBC";
	  DB_MAX_CONNECTIONS = 50;
	 }
	}