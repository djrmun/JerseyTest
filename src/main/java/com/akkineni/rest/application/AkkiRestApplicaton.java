package com.akkineni.rest.application;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.core.Application;

public class AkkiRestApplicaton extends Application {

	private final Logger LOGGER = Logger.getLogger(AkkiRestApplicaton.class
			.getName());

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classSet = new HashSet<Class<?>>();

	public AkkiRestApplicaton() {
		super();
		LOGGER.info("created AKKIRESTAPPLICATION");
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classSet;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}