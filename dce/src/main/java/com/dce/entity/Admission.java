package com.dce.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Admission extends Enquiry {

	String UID;

	private List<FeeDetails> feeDetails = new ArrayList<FeeDetails>();
	public List<Course> courseDetails = new ArrayList<Course>();

	public List<FeeDetails> getFeeDetails() {
		return this.feeDetails;
	}

	public void setFeeDetails(List<FeeDetails> feeDetails) {
		this.feeDetails = feeDetails;
	}

	public List<Course> getCourseDetails() {
		return this.courseDetails;
	}

	public void setCourseDetails(List<Course> courseDetails) {
		this.courseDetails = courseDetails;
	}

	public void setCourseDetails(LinkedList<Course> courseDetails) {
		this.courseDetails = courseDetails;
	}

	public String getUID() {
		return this.UID;
	}

	public void setUID(String uID) {
		this.UID = uID;
	}

}
