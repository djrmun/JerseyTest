package com.akkineni.rest.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.akkineni.rest.dao.JdbcWebphoneDao;
import com.akkineni.rest.dao.UserDao;
import com.akkineni.rest.domain.User;

/**
 * Created with IntelliJ IDEA. User: vijay Date: 5/7/13 Time: 11:02 PM To change
 * this template use File | Settings | File Templates.
 */
@Named
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	JdbcWebphoneDao jdbcWebphoneDao;

	@Override
	public List<User> getUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUser(String uid) throws Exception {
		return userDao.getUserByUid(uid);
	}

	@Override
	public void deleteUser(String uid) {
		userDao.delete(uid);
	}

	@Override
	public void createUserFromWebPhone(String uid, String workgroup) {
		User userFromWebPhone = jdbcWebphoneDao.fetchUserFromWebPhone(uid);
		userFromWebPhone.setCuaWorkGroupName(workgroup);
		userDao.create(userFromWebPhone);
	}

	@Override
	public void updateWorkGroupForUser(String uid, String workgroup) {
		userDao.updateWorkGroup(uid, workgroup);

	}

}
