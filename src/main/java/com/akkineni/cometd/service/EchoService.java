/**
 * 
 */
package com.akkineni.cometd.service;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
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
		System.out.println(bayeux);
		addService("/echo", "processEcho");
	}

	public void processEcho(ServerSession remote, ServerMessage.Mutable message) {
		String channel = message.getChannel();
		Object data = message.getData();
		remote.deliver(getServerSession(), channel, data, null);
	}

}
