/**
 * 
 */
package com.akkineni.cometd.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;

import com.akkineni.rest.util.DateTimeUtil;
import com.akkineni.schema.so.ServiceOrderDTO;
import com.akkineni.schema.so.ServiceOrderSearch;
import com.sun.research.ws.wadl.Response;

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

	public void processEcho(ServerSession remote, ServerMessage.Mutable message) {
		Map<String, Object> input = message.getDataAsMap();
		String name = (String) input.get("name");

		Map<String, Object> output = new HashMap<String, Object>();
		output.put("greeting", "Hello, " + name);

		System.out.println(output + " " + message);
		message.setData(output);

		DateTimeUtil util = new DateTimeUtil();

		ServiceOrderSearch sos = new ServiceOrderSearch();

		for (int i = 0; i < 10; i++) {
			ServiceOrderDTO dto = new ServiceOrderDTO();
			dto.setCreationDate(util.getDate());
			dto.setDescription("Service Order Description");
			dto.setFlowableID(i);
			dto.setOrderNumber("1234" + i);
			dto.setVersion(1);

			sos.getServiceOrderDTO().add(dto);
		}

		String response = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(sos);
			System.out.println(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// remote.deliver(getServerSession(), message);
		remote.deliver(getServerSession(), "/echo", response, null);
		// getBayeux().getChannel(message.getChannel()).publish(
		// getServerSession(), message);
	}

}
