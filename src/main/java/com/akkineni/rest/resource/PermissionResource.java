package com.akkineni.rest.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
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

import com.akkineni.rest.domain.Permission;
import com.akkineni.rest.service.PermissionService;

@Named
@Path("/ldap/permission")
public class PermissionResource {

	private final Logger LOGGER = LoggerFactory
			.getLogger(PermissionResource.class.getName());

	@Context
	Providers providers;

	@Inject
	PermissionService permissionService;

	public PermissionResource() {
		super();
		LOGGER.info("Constructed PermissionResource");
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello PermissionResource!!!!";
	}

	@GET
	@Path("/{perm}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Permission> getPermission(@PathParam("perm") String perm) {
		try {
			List<Permission> perms = permissionService
					.findPermissionsByName(perm);
			return perms;
		} catch (Exception e) {
			LOGGER.error("Exception fetching user ID: " + perm, e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GZIP
	public List<Permission> findAllPermissions() {
		try {
			List<Permission> perms = permissionService.findAllPermissions();
			return perms;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}