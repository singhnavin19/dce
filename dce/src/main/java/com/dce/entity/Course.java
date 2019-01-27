package com.dce.entity;

public class Course {

	String uid;
	String courseName;
	Integer feePerCcourse;
	String batchTime;
	String duration;

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getFeePerCcourse() {
		return this.feePerCcourse;
	}

	public void setFeePerCcourse(Integer feePerCcourse) {
		this.feePerCcourse = feePerCcourse;
	}

	public String getBatchTime() {
		return this.batchTime;
	}

	public void setBatchTime(String batchTime) {
		this.batchTime = batchTime;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
