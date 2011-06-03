package com.akkineni.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.akkineni.rest.resource.CustomerResource;
import com.akkineni.rest.util.CustomResolver;

public class AkkiRestApplicaton extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> s = new HashSet<Class<?>>();

	public AkkiRestApplicaton() {
		super();
		// singletons.add(new CustomerResource());
		singletons.add(new CustomResolver());
	}

	@Override
	public Set<Class<?>> getClasses() {
		s.add(CustomerResource.class);
		return s;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}