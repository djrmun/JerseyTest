package com.akkineni.rest.dao;

import java.util.List;

public interface PermissionContextDao {

	void create(String permission);

	void delete(String permission);

	List<String> findAllPermissions();

	String findPermissionByPrimaryKey(String permission) throws Exception;

	List<String> findByName(String name);

	List<String> findAll();

}
