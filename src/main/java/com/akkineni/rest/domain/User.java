/**
 * 
 */
package com.akkineni.rest.domain;

/**
 * @author vijay akkineni
 * 
 */
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}