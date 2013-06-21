package com.akkineni.rest.domain;

import java.util.Arrays;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author va2839
 * 
 */
@XmlRootElement(name = "Profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile {

	@XmlElement
	private Date cuaCreationDate;
	@XmlElement
	private String cuaCreatorUid = "cuasysadmin";
	@XmlElement
	private String description = " ";
	@XmlElement
	private String cuaProfileName;
	@XmlElement
	private Boolean cuaEntityIsAdmin = false;
	@XmlElement
	private String[] cuaPermissionName;

	public Date getCuaCreationDate() {
		return cuaCreationDate;
	}

	public void setCuaCreationDate(Date cuaCreationDate) {
		this.cuaCreationDate = cuaCreationDate;
	}

	public String getCuaCreatorUid() {
		return cuaCreatorUid;
	}

	public void setCuaCreatorUid(String cuaCreatorUid) {
		this.cuaCreatorUid = cuaCreatorUid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCuaProfileName() {
		return cuaProfileName;
	}

	public void setCuaProfileName(String cuaProfileName) {
		this.cuaProfileName = cuaProfileName;
	}

	public Boolean getCuaEntityIsAdmin() {
		return cuaEntityIsAdmin;
	}

	@Override
	public String toString() {
		return "Workgroup [cuaCreationDate=" + cuaCreationDate
				+ ", cuaCreatorUid=" + cuaCreatorUid + ", description="
				+ description + ", cuaProfileName=" + cuaProfileName
				+ ", cuaEntityIsAdmin=" + cuaEntityIsAdmin
				+ ", cuaPermissionName=" + Arrays.toString(cuaPermissionName)
				+ "]";
	}

	public void setCuaEntityIsAdmin(Boolean cuaEntityIsAdmin) {
		this.cuaEntityIsAdmin = cuaEntityIsAdmin;
	}

	public String[] getCuaPermissionName() {
		return cuaPermissionName;
	}

	public void setCuaPermissionName(String[] cuaPermissionName) {
		this.cuaPermissionName = cuaPermissionName;
	}

}
