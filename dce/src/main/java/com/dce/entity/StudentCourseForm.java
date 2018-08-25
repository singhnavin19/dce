package com.dce.entity;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

@Component
public class StudentCourseForm {

	private LinkedList<StudentCourse> studentCourses = new LinkedList<StudentCourse>();

	public LinkedList<StudentCourse> getStudentCourses() {
		return this.studentCourses;
	}

	public void setStudentCourses(LinkedList<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

}
