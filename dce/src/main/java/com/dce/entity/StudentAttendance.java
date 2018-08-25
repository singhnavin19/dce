package com.dce.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentAttendance implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uid, fullName, course;
	@DateTimeFormat(pattern = "dd/MM/YYYY hh:mm a")
	private Date attendece_date, check_in_datetime, check_out_datetime;
	private String message;

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getAttendece_date() {
		return this.attendece_date;
	}

	public void setAttendece_date(Date attendece_date) {
		this.attendece_date = attendece_date;
	}

	public Date getCheck_in_datetime() {
		return this.check_in_datetime;
	}

	public void setCheck_in_datetime(Date check_in_datetime) {
		this.check_in_datetime = check_in_datetime;
	}

	public Date getCheck_out_datetime() {
		return this.check_out_datetime;
	}

	public void setCheck_out_datetime(Date check_out_datetime) {
		this.check_out_datetime = check_out_datetime;
	}

	public void reset(StudentAttendance studentAttendance) {
		studentAttendance.setCourse(null);
		studentAttendance.setUid(null);

		studentAttendance.setFullName(null);
		studentAttendance.setAttendece_date(null);
		studentAttendance.setCheck_in_datetime(null);
		studentAttendance.setCheck_out_datetime(null);
		studentAttendance.setMessage(null);

	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
