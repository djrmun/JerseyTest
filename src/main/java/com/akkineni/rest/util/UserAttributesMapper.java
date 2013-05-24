package com.akkineni.rest.util;

import java.util.Date;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.akkineni.rest.domain.User;

public class UserAttributesMapper implements AttributesMapper {
	public User mapFromAttributes(Attributes attrs) throws NamingException {
		User user = new User();
		user.setCn((String) attrs.get("cn").get());

		user.setCuaCreationDate(new Date(Long.parseLong((String) attrs.get(
				"cuaCreationDate").get())));
		user.setCuaCreatorUid((String) attrs.get("cuaCreatorUid").get());
		user.setCuaEntityIsAdmin(new Boolean((String) attrs.get(
				"cuaEntityIsAdmin").get()));
		user.setCuaUserIsLocked(new Boolean((String) attrs.get(
				"cuaUserIsLocked").get()));
		user.setCuaUserLastActivityTime(new Date(Long.parseLong((String) attrs
				.get("cuaUserLastActivityTime").get())));
		user.setEmployeeType((String) attrs.get("employeeType").get());
		user.setGivenName((String) attrs.get("givenName").get());
		user.setL((String) attrs.get("l").get());
		user.setMail((String) attrs.get("mail").get());
		user.setManager((String) attrs.get("manager").get());
		user.setO((String) attrs.get("o").get());
		user.setPostalAddress((String) attrs.get("postalAddress").get());
		user.setSn((String) attrs.get("sn").get());
		user.setTelephoneNumber((String) attrs.get("telephoneNumber").get());
		user.setTitle((String) attrs.get("title").get());
		user.setUid((String) attrs.get("uid").get());

		Attribute workgroup = attrs.get("cuaWorkGroupName");
		if (workgroup != null)
			user.setCuaWorkGroupName((String) workgroup.get());
		else
			System.out.println(user.getUid() + " doesn't have a workgroup");
		return user;
	}
}