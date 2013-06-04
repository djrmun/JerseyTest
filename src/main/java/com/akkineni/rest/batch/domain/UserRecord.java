package com.akkineni.rest.batch.domain;

public class UserRecord {

	private String uid;
	private String workgroup;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}

	@Override
	public String toString() {
		return "UserRecord [uid=" + uid + ", workgroup=" + workgroup + "]";
	}

}
