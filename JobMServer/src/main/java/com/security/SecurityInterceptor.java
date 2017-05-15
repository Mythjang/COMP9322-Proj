package com.security;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

import com.dao.UserDao;

/**
 * This interceptor verify the access permissions for a user 
 * based on username and passowrd provided in request
 * */
@Provider
public class SecurityInterceptor implements ContainerRequestFilter
{
	private static final String AUTHORIZATION_PROPERTY1 = "securityKey";
	private static final String AUTHORIZATION_PROPERTY2 = "shortKey";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());;
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());;
//	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
	{
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		Method method = methodInvoker.getMethod();
		System.out.println(method.getName());
		//Access allowed for all 
		System.out.println("afasfasfsad");
		if(!method.isAnnotationPresent(PermitAll.class))
		{
			
			//Access denied for all 
			if(method.isAnnotationPresent(DenyAll.class))
			{
				requestContext.abortWith(ACCESS_FORBIDDEN);
				return;
			}
			
			//Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			
			//Fetch securityKey header
		    final List<String> securityKey = headers.get(AUTHORIZATION_PROPERTY1);
		    final List<String> shortKey = headers.get(AUTHORIZATION_PROPERTY2);
		    
	


		    if(shortKey==null || securityKey==null || shortKey.isEmpty() || securityKey.isEmpty()){
		    	requestContext.abortWith(ACCESS_DENIED);
		    	return;
		    	
		    }
		    if(!UserDao.instance.getUsers().contains(securityKey.get(0))){
		    	requestContext.abortWith(ACCESS_DENIED);
		    	return;
		    }
		    
		    if(method.isAnnotationPresent(RolesAllowed.class)){
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
				
				//Is user valid?
				if(!rolesSet.contains(shortKey.get(0)))
				{
					requestContext.abortWith(ACCESS_DENIED);
			    	return;
				}
			}
			
	
			}
		}
	

	
}
