package com.akkineni.rest.service;

import com.akkineni.rest.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vijay
 * Date: 5/7/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    List<User> getUsers();
}
