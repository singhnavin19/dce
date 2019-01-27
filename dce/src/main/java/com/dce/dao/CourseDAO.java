package com.dce.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.Admission;
import com.dce.entity.Course;

@Component
public class CourseDAO {
	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;
	@Autowired
	JdbcTemplate jdbctemplate;

	public void save(Admission admission) {

		for (Course course : admission.getCourseDetails()) {
			course.setUid(admission.getUID());
			this.insert(course);
		}

	}

	public void insert(Course course) {

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO tcourse_details ");
		query.append("(uid,course_name,fee_per_course,batch_time,duration ");
		query.append(") VALUES( ");
		query.append(":uid,:courseName,:feePerCcourse,:batchTime,:duration ) ");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(course));

	}

	private void delete() {

		StringBuilder query = new StringBuilder();
		query.append("delete from tstudent_course where uid=? ");

	}

	public List<Course> fetch(String UID) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("uid", UID);

		StringBuilder query = new StringBuilder();
		query.append("SELECT * ");
		query.append("FROM tcourse_details where uid =:uid ");

		return this.namedjdbctemplate.query(query.toString(), param, new BeanPropertyRowMapper<Course>(Course.class));

		// @Override
		// public FeeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		//
		// FeeDetails feedetails = new FeeDetails();
		// feedetails.setUid(rs.getString("uid"));
		// feedetails.setInstallment(rs.getInt("installment"));
		// feedetails.setDueDate(rs.getDate("Paid_date"));
		// feedetails.setPaidDate(rs.getDate("Paid_date"));
		// feedetails.set
		//
		// return null;
		// }
		//
		// } );
	}
}