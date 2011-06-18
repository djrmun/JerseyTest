/**
 * 
 */
package com.akkineni.cometd.service;

import java.util.HashMap;
import java.util.Map;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;

/**
 * @author lokesh
 * 
 */
public class EchoService extends AbstractService {

	/**
	 * @param bayeux
	 * @param name
	 */
	public EchoService(BayeuxServer bayeux) {
		super(bayeux, "echo");
		addService("/service/echo", "processEcho");
	}

	public void processEcho(ServerSession remote, Message message) {
		Map<String, Object> input = message.getDataAsMap();
		String name = (String) input.get("name");

		Map<String, Object> output = new HashMap<String, Object>();
		output.put("greeting", "Hello, " + name);
		remote.deliver(getServerSession(), "/echo", output, null);
		// getBayeux().getChannel(channel).publish(getServerSession(), message);
	}

}
