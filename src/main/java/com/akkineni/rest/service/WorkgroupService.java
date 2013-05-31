package com.akkineni.rest.service;

import java.util.List;

import com.akkineni.rest.domain.Workgroup;

public interface WorkgroupService {

	List<Workgroup> findWorkgroupsByName(String workgroup);

	List<Workgroup> findAllWorkgroups();

	void deleteWorkgroup(String workgroup);

	void create(Workgroup workgroup);

}
