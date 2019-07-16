package com.dce.entity;

public class User {

	private String uid;
	private String FirstName;
	private String lastName;

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return this.FirstName;
	}

	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		return this.FirstName + " " + this.lastName;
	}

}
