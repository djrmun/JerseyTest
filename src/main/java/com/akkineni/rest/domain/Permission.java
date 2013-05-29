package com.akkineni.rest.domain;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author va2839
 * 
 */
@XmlRootElement(name = "Permission")
@XmlAccessorType(XmlAccessType.FIELD)
public class Permission {

	@XmlElement
	private String cuaPermissionName;
	@XmlElement
	private Boolean cuaEntityIsAdmin = true;
	@XmlElement
	private String description = " ";
	@XmlElement
	private Boolean cuaIsPortSwapPermission = false;
	@XmlElement
	private String[] cuaPermissionCategory;

	@Override
	public String toString() {
		return "Permission [cuaPermissionName=" + cuaPermissionName
				+ ", cuaEntityIsAdmin=" + cuaEntityIsAdmin + ", description="
				+ description + ", cuaIsPortSwapPermission="
				+ cuaIsPortSwapPermission + ", cuaPermissionCategory="
				+ Arrays.toString(cuaPermissionCategory) + "]";
	}

	public Boolean getCuaEntityIsAdmin() {
		return cuaEntityIsAdmin;
	}

	public void setCuaEntityIsAdmin(Boolean cuaEntityIsAdmin) {
		this.cuaEntityIsAdmin = cuaEntityIsAdmin;
	}

	public String getCuaPermissionName() {
		return cuaPermissionName;
	}

	public void setCuaPermissionName(String cuaPermissionName) {
		this.cuaPermissionName = cuaPermissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCuaIsPortSwapPermission() {
		return cuaIsPortSwapPermission;
	}

	public void setCuaIsPortSwapPermission(Boolean cuaIsPortSwapPermission) {
		this.cuaIsPortSwapPermission = cuaIsPortSwapPermission;
	}

	public String[] getCuaPermissionCategory() {
		return cuaPermissionCategory;
	}

	public void setCuaPermissionCategory(String[] cuaPermissionCategory) {
		this.cuaPermissionCategory = cuaPermissionCategory;
	}
}
