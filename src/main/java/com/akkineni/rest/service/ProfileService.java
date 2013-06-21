package com.akkineni.rest.service;

import java.util.List;

import com.akkineni.rest.domain.Profile;

public interface ProfileService {

	List<Profile> findProfilesByName(String perm);

	List<Profile> findAllProfiles();

	void deleteProfile(String perm);

	void create(Profile permission);

	List<Profile> findProfileWithPermission(String permission) throws Exception;

}
