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

import com.akkineni.rest.util.UserContextMapper;

@Service
public class PermissionContextDaoImpl implements PermissionContextDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=permissions,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@Override
	public void create(String permission) {
		Name dn = buildDn(permission);
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(permission, context);
		ldapTemplate.bind(context);
	}

	@Override
	public void delete(String permission) {
		Name dn = buildDn(permission);
		ldapTemplate.unbind(dn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllPermissions() {
		return ldapTemplate.search(BASE_DN, "(permission=*)",
				getContextMapper());
	}

	@Override
	public String findPermissionByPrimaryKey(String permission)
			throws Exception {
		Name dn = buildDn(permission);
		String perm = (String) ldapTemplate.lookup(dn, getContextMapper());
		if (perm != null)
			return perm;
		else
			throw new Exception("permission not found in ldap");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findByName(String name) {
		Filter filter = new WhitespaceWildcardsFilter("permission", name);
		return ldapTemplate
				.search(BASE_DN, filter.encode(), getContextMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAll() {
		EqualsFilter filter = new EqualsFilter("objectclass", "cuaPermission");
		return ldapTemplate.search(DistinguishedName.EMPTY_PATH,
				filter.encode(), getContextMapper());
	}

	protected void mapToContext(String permission, DirContextOperations context) {

		context.setAttributeValues("objectclass", new String[] { "top",
				"person", "cuaPermission", "inetOrgPerson",
				"organizationalPerson" });
		context.setAttributeValue("permissions", permission);
	}

	private ContextMapper getContextMapper() {
		return new UserContextMapper();
	}

	private Name buildDn(String permission) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("permission", permission);
		return dn;
	}

}
