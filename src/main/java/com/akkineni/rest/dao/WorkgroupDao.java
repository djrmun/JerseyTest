package com.akkineni.rest.dao;

import java.util.List;

import com.akkineni.rest.domain.Workgroup;

public interface WorkgroupDao {

	void create(Workgroup workgroup);

	void delete(String workgroup);

	List<Workgroup> findAllWorkgroups();

	Workgroup findWorkgroupByPrimaryKey(String workgroup) throws Exception;

	List<Workgroup> findByName(String name);

}
