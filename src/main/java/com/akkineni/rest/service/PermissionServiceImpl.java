package com.akkineni.rest.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.akkineni.rest.dao.PermissionContextDao;
import com.akkineni.rest.domain.Permission;

/**
 * Created with IntelliJ IDEA. User: vijay Date: 5/7/13 Time: 11:02 PM To change
 * this template use File | Settings | File Templates.
 */
@Named
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionContextDao permissionContextDao;

	@Override
	public List<Permission> findAllPermissions() {
		return permissionContextDao.findAllPermissions();
	}

	@Override
	public List<Permission> findPermissionsByName(String perm) {
		return permissionContextDao.findByName(perm);
	}

	@Override
	public void deletePermission(String perm) {
		permissionContextDao.delete(perm);
	}

}
