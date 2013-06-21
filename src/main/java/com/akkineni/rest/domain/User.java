package com.akkineni.rest.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	@Override
	public String toString() {
		return "User [cn=" + cn + ", cuaCreationDate=" + cuaCreationDate
				+ ", cuaCreatorUid=" + cuaCreatorUid + ", cuaEntityIsAdmin="
				+ cuaEntityIsAdmin + ", cuaUserIsLocked=" + cuaUserIsLocked
				+ ", cuaUserLastActivityTime=" + cuaUserLastActivityTime
				+ ", employeeType=" + employeeType + ", givenName=" + givenName
				+ ", l=" + l + ", mail=" + mail + ", manager=" + manager
				+ ", o=" + o + ", postalAddress=" + postalAddress + ", sn="
				+ sn + ", telephoneNumber=" + telephoneNumber + ", title="
				+ title + ", uid=" + uid + ", cuaWorkGroupName="
				+ cuaWorkGroupName + "]";
	}

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

	public boolean isCuaEntityIsAdmin() {
		return cuaEntityIsAdmin;
	}

	public void setCuaEntityIsAdmin(boolean cuaEntityIsAdmin) {
		this.cuaEntityIsAdmin = cuaEntityIsAdmin;
	}

	public boolean isCuaUserIsLocked() {
		return cuaUserIsLocked;
	}

	public void setCuaUserIsLocked(boolean cuaUserIsLocked) {
		this.cuaUserIsLocked = cuaUserIsLocked;
	}

	public Date getCuaUserLastActivityTime() {
		return cuaUserLastActivityTime;
	}

	public void setCuaUserLastActivityTime(Date cuaUserLastActivityTime) {
		this.cuaUserLastActivityTime = cuaUserLastActivityTime;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
		this.mail = uid + "@asemail.att.com";
	}

	public String getCuaWorkGroupName() {
		return cuaWorkGroupName;
	}

	public void setCuaWorkGroupName(String cuaWorkGroupName) {
		this.cuaWorkGroupName = cuaWorkGroupName;
	}

	@XmlElement
	String cn;
	@XmlElement
	Date cuaCreationDate = new Date();
	@XmlElement
	String cuaCreatorUid = "cuasysadmin";
	@XmlElement
	boolean cuaEntityIsAdmin = true;
	@XmlElement
	boolean cuaUserIsLocked = false;
	@XmlElement
	Date cuaUserLastActivityTime = new Date();
	@XmlElement
	String employeeType = "Unknown";
	@XmlElement
	String givenName;
	@XmlElement
	String l = "Unknown";
	@XmlElement
	String mail;
	@XmlElement
	String manager;
	@XmlElement
	String o = "Unkown";
	@XmlElement
	String postalAddress = "Unknown";
	@XmlElement
	String sn;
	@XmlElement
	String telephoneNumber;
	@XmlElement
	String title = "Unkown";
	@XmlAttribute
	String uid;
	@XmlElement
	String cuaWorkGroupName;

}
