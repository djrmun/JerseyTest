package com.akkineni.rest.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.akkineni.rest.domain.Workgroup;
import com.akkineni.rest.service.WorkgroupService;

@Named
@Path("/ldap/workgroup")
public class WorkgroupResource {

	private final Logger LOGGER = LoggerFactory
			.getLogger(WorkgroupResource.class.getName());

	@Context
	Providers providers;

	@Inject
	WorkgroupService workgroupService;

	public WorkgroupResource() {
		super();
		LOGGER.info("Constructed WorkgroupResource");
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello WorkgroupResource!!!!";
	}

	@POST
	@Path("/create")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createProfile(Workgroup workgroup) {

		try {
			workgroupService.create(workgroup);
		} catch (Exception e) {
			LOGGER.error("Exception creating workgroup: " + workgroup, e);
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("/{workgroup}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteWorkgroup(@PathParam("workgroup") String workgroup) {

		try {
			workgroupService.deleteWorkgroup(workgroup);
		} catch (Exception e) {
			LOGGER.error("Exception deleting workgroup: " + workgroup, e);
			throw new WebApplicationException(
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/{workgroup}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Workgroup> getWorkgroup(@PathParam("workgroup") String workgroup) {
		try {
			List<Workgroup> workgroups = workgroupService
					.findWorkgroupsByName(workgroup);
			return workgroups;
		} catch (Exception e) {
			LOGGER.error("Exception fetching user ID: " + workgroup, e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GZIP
	public List<Workgroup> findAllWorkgroups() {
		try {
			List<Workgroup> workgroups = workgroupService.findAllWorkgroups();
			return workgroups;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}