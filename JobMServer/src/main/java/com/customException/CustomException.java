package com.customException;

import javax.ws.rs.core.Response.Status;

public class CustomException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Status status;
	private String errorMessage;
	
	
	
	public CustomException(Status status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

	
	
}
