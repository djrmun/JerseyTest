package com.akkineni.rest.dao;

import java.util.List;

import com.akkineni.rest.domain.User;

public interface UserContextDao {

	void create(User user);

	void updateWorkGroup(String uid, String workgroup);

	void update(User user);

	void delete(String uid);

	List<User> findAllUsers();

	User findUserByPrimaryKey(String uid) throws Exception;

	List<User> findByName(String name);

	List<User> findAll();

	List<User> findUsersWithWorkgroup(String workgroup) throws Exception;

}
