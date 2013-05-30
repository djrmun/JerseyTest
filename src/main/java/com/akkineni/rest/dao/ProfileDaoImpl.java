package com.akkineni.rest.dao;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.stereotype.Service;

import com.akkineni.rest.domain.Profile;
import com.akkineni.rest.util.ProfileContextMapper;

@Service
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=profiles,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@Override
	public void create(Profile profile) {
		Name dn = buildDn(profile.getCuaProfileName());
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(profile, context);
		ldapTemplate.bind(context);
	}

	@Override
	public void delete(String profile) {
		Name dn = buildDn(profile);
		ldapTemplate.unbind(dn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> findAllProfiles() {
		return ldapTemplate.search(BASE_DN, "(cuaProfileName=*)",
				getContextMapper());
	}

	@Override
	public Profile findProfileByPrimaryKey(String profile) throws Exception {
		Name dn = buildDn(profile);
		Profile prof = (Profile) ldapTemplate.lookup(dn, getContextMapper());
		if (prof != null)
			return prof;
		else
			throw new Exception("Profile not found in ldap");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> findByName(String name) {
		Filter filter = new WhitespaceWildcardsFilter("cuaProfileName", name);
		return ldapTemplate
				.search(BASE_DN, filter.encode(), getContextMapper());
	}

	protected void mapToContext(Profile profile, DirContextOperations context) {

		context.setAttributeValues("objectclass", new String[] { "top",
				"cuaprofile" });
		context.setAttributeValues("cuaPermissionName",
				profile.getCuaPermissionName());
		context.setAttributeValue("description", profile.getDescription());
		context.setAttributeValue("cuaEntityIsAdmin",
				Boolean.toString(profile.getCuaEntityIsAdmin()));
		context.setAttributeValue("cuaCreatorUid", profile.getCuaCreatorUid());
		context.setAttributeValue("cuaCreationDate",
				Long.toString(profile.getCuaCreationDate().getTime()));
		context.setAttributeValue("cuaProfileName", profile.getCuaProfileName());
	}

	private ContextMapper getContextMapper() {
		return new ProfileContextMapper();
	}

	private Name buildDn(String profile) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("cuaProfileName", profile);
		return dn;
	}

}
