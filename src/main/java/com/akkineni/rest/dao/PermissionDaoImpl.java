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

import com.akkineni.rest.domain.Permission;
import com.akkineni.rest.util.PermissionContextMapper;

@Service
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=permissions,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@Override
	public void create(Permission permission) {
		Name dn = buildDn(permission.getCuaPermissionName());
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
	public List<Permission> findAllPermissions() {
		return ldapTemplate.search(BASE_DN, "(cuaPermissionName=*)",
				getContextMapper());
	}

	@Override
	public Permission findPermissionByPrimaryKey(String permission)
			throws Exception {
		Name dn = buildDn(permission);
		Permission perm = (Permission) ldapTemplate.lookup(dn,
				getContextMapper());
		if (perm != null)
			return perm;
		else
			throw new Exception("permission not found in ldap");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findByName(String name) {
		Filter filter = new WhitespaceWildcardsFilter("cuaPermissionName", name);
		return ldapTemplate
				.search(BASE_DN, filter.encode(), getContextMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findAll() {
		EqualsFilter filter = new EqualsFilter("objectclass", "cuaPermission");
		return ldapTemplate.search(DistinguishedName.EMPTY_PATH,
				filter.encode(), getContextMapper());
	}

	protected void mapToContext(Permission permission,
			DirContextOperations context) {

		context.setAttributeValues("objectclass", new String[] { "top",
				"cuapermission" });
		context.setAttributeValue("cuaPermissionName",
				permission.getCuaPermissionName());
		context.setAttributeValue("description", permission.getDescription());
		context.setAttributeValue("cuaEntityIsAdmin",
				Boolean.toString(permission.getCuaEntityIsAdmin()));

		context.setAttributeValue("cuaisportswappermission",
				Boolean.toString(permission.getCuaIsPortSwapPermission()));

		if (permission.getCuaPermissionCategory() != null
				&& permission.getCuaPermissionCategory().length > 0)
			context.setAttributeValues("cuaPermissionCategory",
					permission.getCuaPermissionCategory());
	}

	private ContextMapper getContextMapper() {
		return new PermissionContextMapper();
	}

	private Name buildDn(String permission) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("cuaPermissionName", permission);
		return dn;
	}

}
