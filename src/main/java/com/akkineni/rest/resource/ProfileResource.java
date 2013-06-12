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

import com.akkineni.rest.domain.Profile;
import com.akkineni.rest.service.ProfileService;

@Named
@Path("/ldap/profile")
public class ProfileResource {

	private final Logger LOGGER = LoggerFactory.getLogger(ProfileResource.class
			.getName());

	@Context
	Providers providers;

	@Inject
	ProfileService profileService;

	public ProfileResource() {
		super();
		LOGGER.info("Constructed ProfileResource");
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello ProfileResource!!!!";
	}

	@POST
	@Path("/create")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createProfile(Profile profile) {

		try {
			profileService.create(profile);
		} catch (Exception e) {
			LOGGER.error("Exception creating profile: " + profile, e);
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("/{profile}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteProfile(@PathParam("profile") String profile) {

		try {
			profileService.deleteProfile(profile);
		} catch (Exception e) {
			LOGGER.error("Exception deleting profile: " + profile, e);
			throw new WebApplicationException(
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/{profile}")
	@GZIP
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Profile> getProfile(@PathParam("profile") String profile) {
		try {
			List<Profile> perms = profileService.findProfilesByName(profile);
			return perms;
		} catch (Exception e) {
			LOGGER.error("Exception fetching user ID: " + profile, e);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GZIP
	public List<Profile> findAllProfiles() {
		try {
			List<Profile> profiles = profileService.findAllProfiles();
			return profiles;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}