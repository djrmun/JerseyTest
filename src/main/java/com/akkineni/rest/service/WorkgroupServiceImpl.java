package com.akkineni.rest.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.akkineni.rest.dao.WorkgroupDao;
import com.akkineni.rest.domain.Workgroup;

/**
 * Created with IntelliJ IDEA. User: vijay Date: 5/7/13 Time: 11:02 PM To change
 * this template use File | Settings | File Templates.
 */
@Named
public class WorkgroupServiceImpl implements WorkgroupService {

	@Autowired
	WorkgroupDao workgroupDao;

	@Override
	public List<Workgroup> findAllWorkgroups() {
		return workgroupDao.findAllWorkgroups();
	}

	@Override
	public List<Workgroup> findWorkgroupsByName(String workgroup) {
		return workgroupDao.findByName(workgroup);
	}

	@Override
	public void deleteWorkgroup(String workgroup) {
		workgroupDao.delete(workgroup);
	}

	@Override
	public void create(Workgroup workgroup) {
		workgroupDao.create(workgroup);
	}

}
