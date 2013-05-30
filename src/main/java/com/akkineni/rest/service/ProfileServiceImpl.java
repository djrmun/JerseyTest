package com.akkineni.rest.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.akkineni.rest.dao.ProfileDao;
import com.akkineni.rest.domain.Profile;

/**
 * Created with IntelliJ IDEA. User: vijay Date: 5/7/13 Time: 11:02 PM To change
 * this template use File | Settings | File Templates.
 */
@Named
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileDao profileDao;

	@Override
	public List<Profile> findAllProfiles() {
		return profileDao.findAllProfiles();
	}

	@Override
	public List<Profile> findProfilesByName(String perm) {
		return profileDao.findByName(perm);
	}

	@Override
	public void deleteProfile(String profile) {
		profileDao.delete(profile);
	}

	@Override
	public void create(Profile profile) {
		profileDao.create(profile);
	}

}
