/**
 * 
 */
package com.akkineni.rest.util;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import com.akkineni.rest.domain.Permission;

/**
 * @author vijay akkineni
 * 
 */
public class PermissionContextMapper implements ContextMapper {

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
		Permission perm = new Permission();

		// Must Attributes
		perm.setCuaPermissionName(context
				.getStringAttribute("cuaPermissionName"));
		perm.setDescription(context.getStringAttribute("description"));
		perm.setCuaEntityIsAdmin(new Boolean(context
				.getStringAttribute("cuaEntityIsAdmin")));

		// May Attributes
		perm.setCuaIsPortSwapPermission(new Boolean(context
				.getStringAttribute("cuaisportswappermission")));
		perm.setCuaPermissionCategory(context
				.getStringAttributes("cuaPermissionCategory"));
		return perm;
	}
}
