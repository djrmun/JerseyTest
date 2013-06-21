package com.akkineni.rest.dao;

import java.util.List;

import com.akkineni.rest.domain.Profile;

public interface ProfileDao {

	void create(Profile profile);

	void delete(String profile);

	List<Profile> findAllProfiles();

	Profile findProfileByPrimaryKey(String profile) throws Exception;

	List<Profile> findByName(String name);

	List<Profile> findProfileWithPermission(String permission) throws Exception;

}
