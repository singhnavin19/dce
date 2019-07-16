package com.dce.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Enquiry {

	String firstName;
	String middleName;
	String lastName;
	String qualification;
	String profession;
	String address;
	String mobileNo;
	String emailId;
	String course;
	String fess;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	Date dateOfEnquiry;
	String gender;
	String tempID;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {

		return this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCourse() {
		return this.course;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getFess() {
		return this.fess;
	}

	public void setFess(String fess) {
		this.fess = fess;
	}

	public Date getDateOfEnquiry() {
		return this.dateOfEnquiry;
	}

	public void setDateOfEnquiry(Date dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTempID() {
		return this.tempID;
	}

	public void setTempID(String tempID) {
		this.tempID = tempID;
	}

}
