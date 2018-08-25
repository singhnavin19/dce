package com.dce.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentEnquiry {

	String fname, mname, lname, qualification, profession, address, mobileNo, emailID, course, fess;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	Date dateOfEnquiry;
	String gender, tempID;

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getEmailID() {
		return this.emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getCourse() {
		return this.course;
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

	@Override
	public String toString() {
		return "StudentEnquiry [fname=" + this.fname + ", mname=" + this.mname + ", lname=" + this.lname
				+ ", qualification=" + this.qualification + ", profession=" + this.profession + ", address="
				+ this.address + ", mobileNo=" + this.mobileNo + ", emailID=" + this.emailID + ", course=" + this.course
				+ ", fess=" + this.fess + ", dateOfEnquiry=" + this.dateOfEnquiry + ", gender=" + this.gender + "]";
	}

}
