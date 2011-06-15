/**
 * 
 */
package com.akkineni.atmo.listener;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;

/**
 * @author lokesh
 * 
 */
public class PubSubResourceListener implements AtmosphereResourceEventListener {

	private final Logger LOGGER = Logger.getLogger(PubSubResourceListener.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.atmosphere.cpr.AtmosphereResourceEventListener#onSuspend(org.atmosphere
	 * .cpr.AtmosphereResourceEvent)
	 */
	@Override
	public void onSuspend(
			AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
		LOGGER.info("onSuspend:: " + event.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.atmosphere.cpr.AtmosphereResourceEventListener#onResume(org.atmosphere
	 * .cpr.AtmosphereResourceEvent)
	 */
	@Override
	public void onResume(
			AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
		LOGGER.info("onResume:: " + event.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.atmosphere.cpr.AtmosphereResourceEventListener#onDisconnect(org.
	 * atmosphere.cpr.AtmosphereResourceEvent)
	 */
	@Override
	public void onDisconnect(
			AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
		LOGGER.info("onDisconnect:: " + event.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.atmosphere.cpr.AtmosphereResourceEventListener#onBroadcast(org.atmosphere
	 * .cpr.AtmosphereResourceEvent)
	 */
	@Override
	public void onBroadcast(
			AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
		LOGGER.info("onBroadcast:: " + event.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.atmosphere.cpr.AtmosphereResourceEventListener#onThrowable(org.atmosphere
	 * .cpr.AtmosphereResourceEvent)
	 */
	@Override
	public void onThrowable(
			AtmosphereResourceEvent<HttpServletRequest, HttpServletResponse> event) {
		LOGGER.info("onThrowable:: " + event.toString());
	}

}
