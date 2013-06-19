package com.akkineni.rest.dao;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.stereotype.Service;

import com.akkineni.rest.domain.User;
import com.akkineni.rest.util.UserContextMapper;

@Service
public class UserContextDaoImpl implements UserContextDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=users,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@Override
	public void create(User user) {
		Name dn = buildDn(user.getUid());
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(user, context);
		ldapTemplate.bind(context);
	}

	@Override
	public void updateWorkGroup(String uid, String workgroup) {
		Name dn = buildDn(uid);
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		context.setAttributeValue("cuaWorkGroupName", workgroup);
		ldapTemplate.modifyAttributes(context);
	}

	@Override
	public void update(User user) {
		Name dn = buildDn(user.getUid());
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		mapToContext(user, context);
		ldapTemplate.modifyAttributes(context);
	}

	@Override
	public void delete(String uid) {
		Name dn = buildDn(uid);
		ldapTemplate.unbind(dn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		return ldapTemplate.search(BASE_DN, "(uid=*)", getContextMapper());
	}

	@Override
	public User findUserByPrimaryKey(String uid) throws Exception {
		Name dn = buildDn(uid);
		User user = (User) ldapTemplate.lookup(dn, getContextMapper());
		if (user != null)
			return user;
		else
			throw new Exception("User not found in ldap");
	}

	@Override
	public List<User> findUsersWithWorkgroup(String workgroup) throws Exception {
		Filter filter = new EqualsFilter("cuaWorkGroupName", workgroup.trim());
		@SuppressWarnings("unchecked")
		List<User> users = ldapTemplate.search(BASE_DN, filter.encode(),
				getContextMapper());
		if (users != null && users.size() > 0)
			return users;
		else
			throw new Exception("Profiles not found in ldap");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String name) {
		Filter filter = new WhitespaceWildcardsFilter("cn", name);
		return ldapTemplate
				.search(BASE_DN, filter.encode(), getContextMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		EqualsFilter filter = new EqualsFilter("objectclass", "cuaUser");
		return ldapTemplate.search(DistinguishedName.EMPTY_PATH,
				filter.encode(), getContextMapper());
	}

	protected void mapToContext(User user, DirContextOperations context) {

		context.setAttributeValues("objectclass", new String[] { "top",
				"person", "cuaUser", "inetOrgPerson", "organizationalPerson" });
		context.setAttributeValue("cn", user.getCn());
		context.setAttributeValue("cuaCreationDate",
				Long.toString(user.getCuaCreationDate().getTime()));
		context.setAttributeValue("cuaCreatorUid", user.getCuaCreatorUid());
		context.setAttributeValue("cuaEntityIsAdmin",
				Boolean.toString(user.isCuaEntityIsAdmin()));
		context.setAttributeValue("cuaUserIsLocked",
				Boolean.toString(user.isCuaUserIsLocked()));
		context.setAttributeValue("cuaUserLastActivityTime",
				Long.toString(user.getCuaUserLastActivityTime().getTime()));
		context.setAttributeValue("employeeType", user.getEmployeeType());
		context.setAttributeValue("givenName", user.getGivenName());
		context.setAttributeValue("l", user.getL());
		context.setAttributeValue("mail", user.getMail());
		context.setAttributeValue("manager", user.getManager());
		context.setAttributeValue("o", user.getO());
		context.setAttributeValue("postalAddress", user.getPostalAddress());
		context.setAttributeValue("sn", user.getSn());
		context.setAttributeValue("telephoneNumber", user.getTelephoneNumber());
		context.setAttributeValue("title", user.getTitle());
		context.setAttributeValue("uid", user.getUid());
		context.setAttributeValue("cuaWorkGroupName",
				user.getCuaWorkGroupName());
	}

	private ContextMapper getContextMapper() {
		return new UserContextMapper();
	}

	private Name buildDn(String user) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("uid", user);
		return dn;
	}

}
