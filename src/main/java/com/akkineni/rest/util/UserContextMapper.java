/**
 * 
 */
package com.akkineni.rest.util;

import java.util.Date;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import com.akkineni.rest.domain.User;

/**
 * @author vijay akkineni
 * 
 */
public class UserContextMapper implements ContextMapper {

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
		User user = new User();

		user.setCn(context.getStringAttribute("cn"));

		user.setCuaCreationDate(new Date(Long.parseLong(context
				.getStringAttribute("cuaCreationDate"))));

		user.setCuaCreatorUid(context.getStringAttribute("cuaCreatorUid"));

		user.setCuaEntityIsAdmin(new Boolean(context
				.getStringAttribute("cuaEntityIsAdmin")));

		user.setCuaUserIsLocked(new Boolean(context
				.getStringAttribute("cuaUserIsLocked")));

		user.setCuaUserLastActivityTime(new Date(Long.parseLong(context
				.getStringAttribute("cuaUserLastActivityTime"))));

		user.setEmployeeType(context.getStringAttribute("employeeType"));

		user.setGivenName(context.getStringAttribute("givenName"));

		user.setL(context.getStringAttribute("l"));

		user.setMail(context.getStringAttribute("mail"));

		user.setManager(context.getStringAttribute("manager"));

		user.setO(context.getStringAttribute("o"));

		user.setPostalAddress(context.getStringAttribute("postalAddress"));

		user.setSn(context.getStringAttribute("sn"));

		user.setTelephoneNumber(context.getStringAttribute("telephoneNumber"));

		user.setTitle(context.getStringAttribute("title"));

		user.setUid(context.getStringAttribute("uid"));

		String workgroup = context.getStringAttribute("cuaWorkGroupName");
		if (workgroup != null)
			user.setCuaWorkGroupName(workgroup);
		else
			user.setCuaWorkGroupName("");

		return user;
	}

}
