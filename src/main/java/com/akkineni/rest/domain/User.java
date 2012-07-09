/**
 * 
 */
package com.akkineni.rest.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vijay
 * 
 */
@JsonAutoDetect
public class User {

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", age=" + age
				+ "]";
	}

	private String fname;
	private String lname;
	private int age;

	@JsonProperty
	public String getFname() {
		return fname;
	}

	@JsonProperty
	public void setFname(String fname) {
		this.fname = fname;
	}

	@JsonProperty
	public String getLname() {
		return lname;
	}

	@JsonProperty
	public void setLname(String lname) {
		this.lname = lname;
	}

	@JsonProperty
	public int getAge() {
		return age;
	}

	@JsonProperty
	public void setAge(int age) {
		this.age = age;
	}

}
