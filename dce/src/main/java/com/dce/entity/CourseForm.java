package com.dce.entity;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

@Component
public class CourseForm {

	public LinkedList<Course> studentCourses = new LinkedList<Course>();

	public LinkedList<Course> getStudentCourses() {
		return this.studentCourses;
	}

	public void setStudentCourses(LinkedList<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}

}
