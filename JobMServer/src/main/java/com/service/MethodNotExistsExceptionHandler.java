package com.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.model.ErrorMessage;


@Provider
public final class MethodNotExistsExceptionHandler implements
	ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception exception) {
		return Response.status(Status.BAD_REQUEST)
			.entity(new ErrorMessage(Status.BAD_REQUEST.toString(),"No resource method found")
			).type(MediaType.APPLICATION_JSON).build();
	}	
}