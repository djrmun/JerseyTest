package com.akkineni.rest.service;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.akkineni.rest.dao.JdbcWebphoneDao;
import com.akkineni.rest.dao.UserContextDao;
import com.akkineni.rest.domain.User;

/**
 * Created with IntelliJ IDEA. User: vijay Date: 5/7/13 Time: 11:02 PM To change
 * this template use File | Settings | File Templates.
 */
@Named
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class
			.getName());

	@Autowired
	UserContextDao userDao;

	@Autowired
	JdbcWebphoneDao jdbcWebphoneDao;

	@Override
	public List<User> getUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public User getUser(String uid) throws Exception {
		return userDao.findUserByPrimaryKey(uid);
	}

	@Override
	public void deleteUser(String uid) {
		userDao.delete(uid);
	}

	@Override
	public void createUserFromWebPhone(String uid, String workgroup) {
		User userFromWebPhone = jdbcWebphoneDao.fetchUserFromWebPhone(uid);
		LOGGER.debug("User retrieved from webphone: " + userFromWebPhone);
		userFromWebPhone.setCuaWorkGroupName(workgroup);
		userDao.create(userFromWebPhone);
	}

	@Override
	public User updateWorkGroupForUser(String uid, String workgroup)
			throws Exception {
		userDao.updateWorkGroup(uid, workgroup);
		return userDao.findUserByPrimaryKey(uid);

	}

}
