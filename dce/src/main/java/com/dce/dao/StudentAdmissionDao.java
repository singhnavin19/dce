package com.dce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.StudentEnquiry;

@Component
public class StudentAdmissionDao {

	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;

	@Autowired
	JdbcTemplate jdbctemplate;

	public void insert(StudentEnquiry studentenquiry) {

		studentenquiry.setTempID(studentenquiry.getFname().substring(0, 2).toUpperCase() + this.getNextTempIDno());
		StringBuffer query = new StringBuffer();

		query.append("INSERT INTO tstudent_admission (");
		query.append("first_name, ");
		query.append("middle_name, ");
		query.append("last_name, ");
		query.append("gender, ");
		query.append("qualification, ");
		query.append("profession, ");
		query.append("address, ");
		query.append("mobile_no, ");
		query.append("email_id, ");
		query.append("dateOfEnquiry,uid,dateOfAdmission");
		query.append(" ) VALUES ( ");
		query.append(" :fname, ");
		query.append(" :mname, ");
		query.append(" :lname, ");
		query.append(" :gender, ");
		query.append(" :qualification, ");
		query.append(" :profession, ");
		query.append(" :address, ");
		query.append(" :mobileNo, ");
		query.append(" :emailID, ");
		query.append(":dateOfEnquiry, :tempID,curdate())");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentenquiry));

	}

	public String getNextTempIDno() {

		StringBuilder query = new StringBuilder();

		query.append("select SUBSTRING( CONCAT(YEAR(CURDATE()),max(srno)+1,\"00000000\") ,1,8) ");
		query.append("from tstudent_admission where srno>? ");

		Object[] inputs = new Object[] { 0 };
		return this.jdbctemplate.queryForObject(query.toString(), inputs, String.class);

	}

}
