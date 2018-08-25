package com.dce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.StudentCourse;

@Component
public class StudentCourseDao {
	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;
	@Autowired
	JdbcTemplate jdbctemplate;

	public void insert(StudentCourse studentCourse) {

		studentCourse.setUid(studentCourse.getUid().trim());
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO tstudent_course ");
		query.append("(uid,course_name,fee_per_course,batch_time,duration ");
		query.append(") VALUES( ");
		query.append(":uid,:courseName,:feePerCcourse,:batchTime,:duration ) ");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentCourse));

	}

	public void save() {

	}

	private void delete() {

		StringBuilder query = new StringBuilder();
		query.append("delete from tstudent_course where uid=? ");

	}

	public void fetch() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT mapping_id,course_name,fee_per_course,batch_time,duration ");
		query.append("FROM tstudent_course where mapping_id=?; ");

	}

}
