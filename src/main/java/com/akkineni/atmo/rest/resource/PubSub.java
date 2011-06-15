/**
 * 
 */
package com.akkineni.atmo.rest.resource;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Schedule;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.jersey.Broadcastable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.akkineni.atmo.listener.PubSubResourceListener;
import com.akkineni.rest.util.DateTimeUtil;
import com.akkineni.schema.so.ServiceOrderDTO;
import com.akkineni.schema.so.ServiceOrderSearch;

/**
 * @author lokesh
 * 
 */
@Path("/pubsub/{topic}")
public class PubSub {

	@PathParam("topic")
	Broadcaster topic;

	@Context
	HttpServletRequest servletRequest;

	@Context
	Providers providers;

	@Context
	ServletConfig servletConfig;

	@GET
	@Suspend(period = 600000, listeners = { PubSubResourceListener.class })
	public Broadcastable subscribe() {
		return new Broadcastable("OK Subscribed", topic);
	}

	@GET
	@Path("/ServiceOrderSearch")
	@Produces({ MediaType.APPLICATION_JSON })
	@Broadcast
	public Broadcastable ServiceOrderSearch() {
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

		ObjectMapper mapper = new ObjectMapper();

		Broadcastable bc = null;
		try {
			bc = new Broadcastable(mapper.writeValueAsBytes(sos), topic);
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
		return bc;
	}

	@Schedule(period = 30)
	@GET
	@Path("/ping")
	public String pingSuspendedClients() {
		return "Atmosphere__ping";
	}
}
