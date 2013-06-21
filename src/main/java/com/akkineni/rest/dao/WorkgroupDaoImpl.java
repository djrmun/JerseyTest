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

import com.akkineni.rest.domain.Workgroup;
import com.akkineni.rest.util.WorkgroupContextMapper;

@Service
public class WorkgroupDaoImpl implements WorkgroupDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public static final String BASE_DN = "ou=workgroups,ou=LSBBNMS,ou=applications,dc=cua,dc=snt,dc=bst,dc=bls,dc=com";

	@Override
	public void create(Workgroup workgroup) {
		Name dn = buildDn(workgroup.getCuaWorkGroupName());
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(workgroup, context);
		ldapTemplate.bind(context);
	}

	@Override
	public void delete(String profile) {
		Name dn = buildDn(profile);
		ldapTemplate.unbind(dn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workgroup> findAllWorkgroups() {
		return ldapTemplate.search(BASE_DN, "(cuaWorkGroupName=*)",
				getContextMapper());
	}

	@Override
	public Workgroup findWorkgroupByPrimaryKey(String workgroup)
			throws Exception {
		Name dn = buildDn(workgroup);
		Workgroup prof = (Workgroup) ldapTemplate
				.lookup(dn, getContextMapper());
		if (prof != null)
			return prof;
		else
			throw new Exception("Workgroup not found in ldap");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workgroup> findByName(String name) {
		Filter filter = new WhitespaceWildcardsFilter("cuaWorkGroupName", name);
		return ldapTemplate
				.search(BASE_DN, filter.encode(), getContextMapper());
	}

	protected void mapToContext(Workgroup profile, DirContextOperations context) {

		context.setAttributeValues("objectclass", new String[] { "top",
				"cuaprofile" });
		context.setAttributeValue("cuaWorkGroupName",
				profile.getCuaWorkGroupName());
		context.setAttributeValue("cuaWorkGroupPermName",
				profile.getCuaWorkGroupPermName());
		context.setAttributeValue("description", profile.getDescription());
		context.setAttributeValue("cuaEntityIsAdmin",
				Boolean.toString(profile.getCuaEntityIsAdmin()));
		context.setAttributeValue("cuaCreatorUid", profile.getCuaCreatorUid());
		context.setAttributeValue("cuaCreationDate",
				Long.toString(profile.getCuaCreationDate().getTime()));
		context.setAttributeValue("cuaProfileName", profile.getCuaProfileName());
	}

	private ContextMapper getContextMapper() {
		return new WorkgroupContextMapper();
	}

	private Name buildDn(String profile) {
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		dn.add("cuaWorkGroupName", profile);
		return dn;
	}

}
