/**
 * 
 */
package com.akkineni.rest.util;

import java.util.Date;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import com.akkineni.rest.domain.Workgroup;

/**
 * @author vijay akkineni
 * 
 */
public class WorkgroupContextMapper implements ContextMapper {

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
		Workgroup workgroup = new Workgroup();

		// Must Attributes
		workgroup.setDescription(context.getStringAttribute("description"));
		workgroup.setCuaEntityIsAdmin(new Boolean(context
				.getStringAttribute("cuaEntityIsAdmin")));
		workgroup.setCuaProfileName(context
				.getStringAttribute("cuaProfileName"));
		workgroup.setCuaCreationDate(new Date(Long.parseLong(context
				.getStringAttribute("cuaCreationDate"))));
		workgroup.setCuaCreatorUid(context.getStringAttribute("cuaCreatorUid"));
		// May Attributes
		workgroup.setCuaWorkGroupName(context
				.getStringAttribute("cuaWorkGroupName"));
		workgroup.setCuaWorkGroupPermName(context
				.getStringAttribute("cuaWorkGroupPermName"));

		return workgroup;
	}
}
