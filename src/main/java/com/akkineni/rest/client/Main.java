package com.akkineni.rest.client;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.servlet.GrizzlyWebContainerFactory;

public class Main {

	private static int getPort(int defaultPort) {
		String port = System.getProperty("jersey.test.port");
		if (null != port) {
			try {
				return Integer.parseInt(port);
			} catch (NumberFormatException e) {
			}
		}
		return defaultPort;
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(getPort(8080))
				.build();
	}

	public static final URI BASE_URI = UriBuilder.fromUri(
			getBaseURI().toString() + "JerseyTest/rest/").build();

	protected static HttpServer startServer() throws IOException {
		final Map<String, String> initParams = new HashMap<String, String>();
		initParams.put("com.sun.jersey.config.property.packages",
				"com.akkineni.rest.resource");
		System.out.println("Starting grizzly...");
		return GrizzlyWebContainerFactory.create(BASE_URI, initParams);
	}

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startServer();
		System.out
				.println(String
						.format("Jersey app started with WADL available at "
								+ "%sapplication.wadl\nTry out %scustomers/echo \nHit enter to stop it...",
								BASE_URI, BASE_URI));
		System.in.read();
		httpServer.stop();
	}
}