package com.akkineni.rest.service;

import java.util.List;

import com.akkineni.rest.domain.Permission;

public interface PermissionService {

	List<Permission> findPermissionsByName(String perm);

	List<Permission> findAllPermissions();

}
