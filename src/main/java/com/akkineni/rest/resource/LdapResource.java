package com.akkineni.rest.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

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
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.Providers;

import org.jboss.resteasy.annotations.GZIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akkineni.rest.domain.User;
import com.akkineni.rest.service.UserService;
import com.google.gson.Gson;

@Named
@Path("/ldap")
public class LdapResource {

	private final Logger LOGGER = LoggerFactory.getLogger(LdapResource.class
			.getName());

	@Context
	Providers providers;

	@Inject
	UserService userService;

	public LdapResource() {
		super();
		LOGGER.info("Constructed LdapResource");
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello Ldap!!!!";
	}

	@GET
	@Path("/user/{uid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@Path("/user/all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GZIP
	public List<User> getUserList() {
		try {
			List<User> users = userService.getUsers();
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
			throw new WebApplicationException(
					Response.Status.EXPECTATION_FAILED);
		}
	}

	@PUT
	@Path("/user/{uid}/updateWithWorkgroup/{workgroup}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateUserWithWorkGroup(@PathParam("uid") String uid,
			@PathParam("workgroup") String workgroup) {

		try {
			userService.updateWorkGroupForUser(uid, workgroup);
			return true;
		} catch (Exception e) {
			LOGGER.error("Exception creating user ID: " + uid, e);
			throw new WebApplicationException(
					Response.Status.EXPECTATION_FAILED);
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

	// @POST
	// @Path("/create/list")
	// @Consumes("application/xml")
	// public Response createCustomer(InputStream is) {
	// List<Customer> customerList = StaxParserHelper.createCustomerList(is);
	// for (Customer cust : customerList) {
	// cust.setId(idCounter.incrementAndGet());
	// customerDB.put(cust.getId(), cust);
	// }
	// return Response.created(URI.create("/customers/list")).build();
	// }

	// @PUT
	// @Path("{id}")
	// @Consumes("application/xml")
	// public void updateCustomer(@PathParam("id") int id, InputStream is) {
	// Customer update = StaxParserHelper.createCustomerFromInputStream(is);
	// Customer current = customerDB.get(id);
	// if (current == null)
	// throw new WebApplicationException(Response.Status.NOT_FOUND);
	// current.setFirstName(update.getFirstName());
	// current.setLastName(update.getLastName());
	// current.setStreet(update.getStreet());
	// current.setState(update.getState());
	// current.setZip(update.getZip());
	// current.setCountry(update.getCountry());
	// }

	@GET
	@Path("/GsonUserSearch")
	@Produces({ MediaType.APPLICATION_JSON })
	@GZIP
	public StreamingOutput GsonUserSearch() {

		final List<User> users = userService.getUsers();

		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					Gson gson = new Gson();
					String json = gson.toJson(users);
					OutputStreamWriter osw = new OutputStreamWriter(output,
							"UTF-8");
					osw.write(json);
					osw.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}

}