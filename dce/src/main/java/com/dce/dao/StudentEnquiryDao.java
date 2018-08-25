package com.dce.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.StudentEnquiry;

@Component
public class StudentEnquiryDao {

	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;
	@Autowired
	JdbcTemplate jdbctemplate;

	public String insert(StudentEnquiry studentenquiry) {

		studentenquiry.setTempID(studentenquiry.getFname().substring(0, 2).toUpperCase() + this.getNextTempIDno());
		StringBuffer query = new StringBuffer();

		query.append("INSERT INTO tstudent_enquiry (");
		query.append("first_name, ");
		query.append("middle_name, ");
		query.append("last_name, ");
		query.append("gender, ");
		query.append("qualification, ");
		query.append("profession, ");
		query.append("address, ");
		query.append("mobile_no, ");
		query.append("email_id, ");
		query.append("course, ");
		query.append("fess, ");
		query.append("dateOfEnquiry,temp_id");
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
		query.append(" :course, ");
		query.append(" :fess, ");
		query.append("curdate(), :tempID )");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentenquiry));
		return studentenquiry.getTempID();

	}

	public void update(StudentEnquiry studentEnquiry) {

		StringBuilder query = new StringBuilder();

		query.append("UPDATE tstudent_enquiry ");
		query.append("set first_name:fname, ");
		query.append("middle_name :middle_name, ");
		query.append("last_name :lname, ");
		query.append("gender :gender, ");
		query.append("qualification :qualification, ");
		query.append("profession :profession, ");
		query.append("address :address, ");
		query.append("mobile_no :mobileNo, ");
		query.append("email_id :emailID, ");
		query.append("course :course, ");
		query.append("fess :fess, ");
		query.append("dateOfEnquiry :dateOfEnquiry ");
		query.append("WHERE temp_id :tempID");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentEnquiry));

	}

	public List<StudentEnquiry> fetch(StudentEnquiry studentEnquiry) {

		StringBuffer query = new StringBuffer();

		query.append("SELECT * FROM tstudent_enquiry ");
		query.append("WHERE temp_id like :tempID");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tempID", studentEnquiry.getTempID() + "%");

		List<StudentEnquiry> liststudentEnquiry = this.namedjdbctemplate.query(query.toString(), param,
				new RowMapper<StudentEnquiry>() {

					@Override
					public StudentEnquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
						StudentEnquiry studentEnquiry = new StudentEnquiry();
						studentEnquiry.setFname(rs.getString(1));
						studentEnquiry.setMname(rs.getString(2));
						studentEnquiry.setLname(rs.getString(3));
						studentEnquiry.setGender(rs.getString(4));
						studentEnquiry.setQualification(rs.getString(5));
						studentEnquiry.setProfession(rs.getString(6));
						studentEnquiry.setAddress(rs.getString(7));
						studentEnquiry.setMobileNo(rs.getString(8));
						studentEnquiry.setEmailID(rs.getString(9));
						studentEnquiry.setCourse(rs.getString(10));
						studentEnquiry.setFess(rs.getString(11));
						studentEnquiry.setDateOfEnquiry(rs.getDate(12));
						studentEnquiry.setTempID(rs.getString(13));

						return studentEnquiry;
					}

				});

		return liststudentEnquiry;

	}

	public void delete(String id) {

		StringBuffer query = new StringBuffer();

		query.append("DELETE FROM tstudent_enquiry ");
		query.append("WHERE first_name=?");

	}

	public String getNextTempIDno() {

		StringBuilder query = new StringBuilder();

		query.append("select max(srno)+1 ");
		query.append("from tstudent_enquiry where srno>? ");

		Object[] inputs = new Object[] { 1 };
		return this.jdbctemplate.queryForObject(query.toString(), inputs, String.class);

	}

}
