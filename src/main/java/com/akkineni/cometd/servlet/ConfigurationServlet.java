/**
 * 
 */
package com.akkineni.cometd.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.cometd.bayeux.server.BayeuxServer;

import com.akkineni.cometd.service.EchoService;

/**
 * @author lokesh
 * 
 */
public class ConfigurationServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555501146148057247L;

	@Override
	public void init() throws ServletException {

		BayeuxServer bayeux = (BayeuxServer) getServletContext().getAttribute(
				BayeuxServer.ATTRIBUTE);
		new EchoService(bayeux);

		// This is also the place where you can configure the Bayeux object
		// by adding extensions or specifying a SecurityPolicy
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		throw new ServletException();

	}

}
