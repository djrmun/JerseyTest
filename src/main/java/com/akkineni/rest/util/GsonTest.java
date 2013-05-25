package com.akkineni.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.akkineni.rest.domain.User;
import com.google.gson.Gson;

public class GsonTest {

	public GsonTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final List<User> users = new ArrayList<User>();

		for (int i = 0; i < 10; i++) {
			User dto = new User();
			dto.setCn("Vijay");
			dto.setSn("Akkineni");
			dto.setCuaEntityIsAdmin(false);
			users.add(dto);
		}

		Gson gson = new Gson();
		String json = gson.toJson(users);
		System.out.println(json);

	}

}
