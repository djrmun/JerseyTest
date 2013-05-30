/**
 * 
 */
package com.akkineni.rest.util;

import java.util.Date;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import com.akkineni.rest.domain.Profile;

/**
 * @author vijay akkineni
 * 
 */
public class ProfileContextMapper implements ContextMapper {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.ldap.core.ContextMapper#mapFromContext(java.lang.
	 * Object)
	 */
	@Override
	public Object mapFromContext(Object ctx) {
		DirContextAdapter context = (DirContextAdapter) ctx;
		Profile profile = new Profile();

		// Must Attributes
		profile.setDescription(context.getStringAttribute("description"));
		profile.setCuaEntityIsAdmin(new Boolean(context
				.getStringAttribute("cuaEntityIsAdmin")));
		profile.setCuaProfileName(context
				.getStringAttribute("cuaProfileName"));
		profile.setCuaCreationDate(new Date(Long.parseLong(context
				.getStringAttribute("cuaCreationDate"))));
		profile.setCuaCreatorUid(context.getStringAttribute("cuaCreatorUid"));
		// May Attributes
		profile.setCuaPermissionName(context
				.getStringAttributes("cuaPermissionName"));
		
		return profile;
	}
}
