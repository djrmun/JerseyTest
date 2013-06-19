package com.akkineni.rest.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Providers;

import org.jboss.resteasy.annotations.GZIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akkineni.rest.domain.User;
import com.akkineni.rest.service.UserService;

@Named
@Path("/ldap")
public class UserResource {

	private final Logger LOGGER = LoggerFactory.getLogger(UserResource.class
			.getName());

	@Context
	Providers providers;

	@Inject
	UserService userService;

	public UserResource() {
		super();
		LOGGER.info("Constructed UserResource");
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello UserResource!!!!";
	}

	@GET
	@Path("/user/{uid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUser(@PathParam("uid") String uid) {

		try {
			User user = userService.getUser(uid);
			return user;
		} catch (Exception e) {
			LOGGER.error("Exception fetching user ID: " + uid, e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Path("/user/findUsersWithWorkgroup/{workgroup}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> findUsersWithWorkgroup(
			@PathParam("workgroup") String workgroup) {

		try {
			List<User> users = userService.findUsersWithWorkgroup(workgroup);
			return users;
		} catch (Exception e) {
			LOGGER.error("Exception fetching user ID: " + workgroup, e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Path("/user/all")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@GZIP
	public List<User> getUserList() {
		try {
			List<User> users = userService.getUsers();
			LOGGER.debug("Successfully retrieved users: " + users.size());
			return users;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@DELETE
	@Path("/user/{uid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteUser(@PathParam("uid") String uid) {

		try {
			userService.deleteUser(uid);
			return true;
		} catch (Exception e) {
			LOGGER.error("Exception deleting user ID: " + uid, e);
			throw new WebApplicationException(
					Response.Status.EXPECTATION_FAILED);
		}
	}

	@POST
	@Path("/user/{uid}/createWithWorkgroup/{workgroup}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createUser(@PathParam("uid") String uid,
			@PathParam("workgroup") String workgroup) {

		try {
			userService.createUserFromWebPhone(uid, workgroup);
			return true;
		} catch (Exception e) {
			LOGGER.error("Exception creating user ID: " + uid, e);
			throw new WebApplicationException("Error Creating User: " + uid, e);
		}
	}

	@PUT
	@Path("/user/{uid}/updateWithWorkgroup/{workgroup}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User updateUserWithWorkGroup(@PathParam("uid") String uid,
			@PathParam("workgroup") String workgroup) {

		try {
			return userService.updateWorkGroupForUser(uid, workgroup);
		} catch (Exception e) {
			LOGGER.error("Exception creating user ID: " + uid, e);
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Path("/updateWorkGroupInBulk")
	public void updateUsersWorkGroupInBulk(InputStream input) {
		Map<String, String> userWorkGroupMap = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLine);
				while (st.hasMoreTokens()) {
					String uid = st.nextToken().trim().toLowerCase();
					String workgroup = st.nextToken().trim();
					userWorkGroupMap.put(uid, workgroup);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String key : userWorkGroupMap.keySet()) {
			try {
				userService.createUserFromWebPhone(key,
						userWorkGroupMap.get(key));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// @POST
	// @Path("/create")
	// @Consumes("application/xml")
	// public Response createCustomer(Customer customer) {
	// // Customer customer = readDOMParser(is);
	// Integer id = idCounter.incrementAndGet();
	// customer.setId(id);
	// customerDB.put(id, customer);
	// System.out.println("Created customer " + customer.getId() + " " + id);
	// LOGGER.info("Create Size fo the customer DB : " + customerDB.size());
	// return Response.created(URI.create("/customers/" + customer.getId()))
	// .build();
	// }

}