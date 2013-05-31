package com.akkineni.rest.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author va2839
 * 
 */
@XmlRootElement(name = "Workgroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class Workgroup {

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
	private String cuaWorkGroupName;
	@XmlElement
	private String cuaWorkGroupPermName;

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

	public String getCuaWorkGroupName() {
		return cuaWorkGroupName;
	}

	public void setCuaWorkGroupName(String cuaWorkGroupName) {
		this.cuaWorkGroupName = cuaWorkGroupName;
	}

	public String getCuaWorkGroupPermName() {
		return cuaWorkGroupPermName;
	}

	public void setCuaWorkGroupPermName(String cuaWorkGroupPermName) {
		this.cuaWorkGroupPermName = cuaWorkGroupPermName;
	}

	public void setCuaEntityIsAdmin(Boolean cuaEntityIsAdmin) {
		this.cuaEntityIsAdmin = cuaEntityIsAdmin;
	}

	@Override
	public String toString() {
		return "Workgroup [cuaCreationDate=" + cuaCreationDate
				+ ", cuaCreatorUid=" + cuaCreatorUid + ", description="
				+ description + ", cuaProfileName=" + cuaProfileName
				+ ", cuaEntityIsAdmin=" + cuaEntityIsAdmin
				+ ", cuaWorkGroupName=" + cuaWorkGroupName
				+ ", cuaWorkGroupPermName=" + cuaWorkGroupPermName + "]";
	}

}
