package com.akkineni.rest.dao;

import java.util.List;

import com.akkineni.rest.domain.User;

public interface UserDao {
	List<User> getAllUsers();

	User getUserByUid(String uid) throws Exception;

	void create(User user);

	void delete(String uid);

	void updateWorkGroup(String uid, String workgroup);
}
