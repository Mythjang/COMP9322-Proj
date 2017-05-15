package com.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.customException.CustomException;
import com.model.ErrorMessage;


@Provider
public final class CustomExceptionHandler implements
	ExceptionMapper<CustomException> {

	@Override
	public Response toResponse(final CustomException exception) {
		return Response.status(exception.getStatus())
			.entity(new ErrorMessage(exception.getStatus().toString(),exception.getErrorMessage())
			).type(MediaType.APPLICATION_JSON).build();
	}	
}