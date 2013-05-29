package com.akkineni.rest.dao;

import java.util.List;

import com.akkineni.rest.domain.Permission;

public interface PermissionContextDao {

	void create(Permission permission);

	void delete(String permission);

	List<Permission> findAllPermissions();

	Permission findPermissionByPrimaryKey(String permission) throws Exception;

	List<Permission> findByName(String name);

	List<Permission> findAll();

}
