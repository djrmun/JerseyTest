package com.akkineni.rest.dao;

import com.akkineni.rest.domain.User;

public interface JdbcWebphoneDao {

	User fetchUserFromWebPhone(String uid);
}
