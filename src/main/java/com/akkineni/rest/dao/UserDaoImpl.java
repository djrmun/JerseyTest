/**
 * 
 */
package com.akkineni.rest.dao;

import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Service;

import com.akkineni.rest.domain.User;
import com.akkineni.rest.util.UserAttributesMapper;

/**
 * @author va2839
 * 
 */
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=users,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return ldapTemplate.search(BASE_DN, "(uid=*)",
				new UserAttributesMapper());
	}

	@Override
	public User getUserByUid(String uid) throws Exception {
		Filter filter = new EqualsFilter("uid", uid);
		@SuppressWarnings("unchecked")
		List<User> users = ldapTemplate.search(BASE_DN, filter.encode(),
				new UserAttributesMapper());
		if (users != null && users.size() > 0)
			return users.get(0);
		else
			throw new Exception("User not found in ldap");
	}

	@Override
	public void create(User user) {
		Name dn = buildDn(user.getUid());
		ldapTemplate.bind(dn, null, buildAttributes(user));
	}

	@Override
	public void delete(String uid) {
		Name dn = buildDn(uid);
		ldapTemplate.unbind(dn);
	}

	@Override
	public void updateWorkGroup(String uid, String workgroup) {
		Name dn = buildDn(uid);
		Attribute attr = new BasicAttribute("cuaWorkGroupName", workgroup);
		ModificationItem item = new ModificationItem(
				DirContext.REPLACE_ATTRIBUTE, attr);
		ldapTemplate.modifyAttributes(dn, new ModificationItem[] { item });
	}

	private Attributes buildAttributes(User user) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectClass");
		ocattr.add("cuaUser");
		ocattr.add("inetOrgPerson");
		ocattr.add("organizationalPerson");
		ocattr.add("person");
		ocattr.add("top");
		attrs.put(ocattr);
		attrs.put("cn", user.getCn());
		attrs.put("cuaCreationDate",
				Long.toString(user.getCuaCreationDate().getTime()));
		attrs.put("cuaCreatorUid", user.getCuaCreatorUid());
		attrs.put("cuaEntityIsAdmin",
				Boolean.toString(user.isCuaEntityIsAdmin()));
		attrs.put("cuaUserIsLocked", Boolean.toString(user.isCuaUserIsLocked()));
		attrs.put("cuaUserLastActivityTime",
				Long.toString(user.getCuaUserLastActivityTime().getTime()));
		attrs.put("employeeType", user.getEmployeeType());
		attrs.put("givenName", user.getGivenName());
		attrs.put("l", user.getL());
		attrs.put("mail", user.getMail());
		attrs.put("manager", user.getManager());
		attrs.put("o", user.getO());
		attrs.put("postalAddress", user.getPostalAddress());
		attrs.put("sn", user.getSn());
		attrs.put("telephoneNumber", user.getTelephoneNumber());
		attrs.put("title", user.getTitle());
		attrs.put("uid", user.getUid());
		attrs.put("cuaWorkGroupName", user.getCuaWorkGroupName());
		return attrs;
	}

	protected Name buildDn(String user) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("uid", user);
		return dn;
	}
}
