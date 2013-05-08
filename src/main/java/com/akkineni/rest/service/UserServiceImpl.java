package com.akkineni.rest.service;

import com.akkineni.rest.domain.User;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vijay
 * Date: 5/7/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsers() {
        final List<User> users = new ArrayList<User>();

        for (int i = 0; i < 1000; i++) {
            User dto = new User();
            dto.setFname("Vijay");
            dto.setLname("Akkineni");
            dto.setAge(20);
            users.add(dto);
        }

        return users;
    }
}
