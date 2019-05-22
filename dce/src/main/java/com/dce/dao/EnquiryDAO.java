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

import com.dce.entity.Admission;
import com.dce.entity.Enquiry;

@Component
public class EnquiryDAO {

	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;
	@Autowired
	JdbcTemplate jdbctemplate;

	public String insert(Enquiry studentenquiry) {

		String nextID = this.getNextIDno("tenquiry");
		studentenquiry.setTempID(
				studentenquiry.getFirstName().substring(0, 2).toUpperCase() + (nextID == null ? "1" : nextID));

		StringBuffer query = new StringBuffer();
		this.enquiryInsertQuery(query);

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentenquiry));
		return studentenquiry.getTempID();

	}

	private void enquiryInsertQuery(StringBuffer query) {
		query.append("INSERT INTO tenquiry (");
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
	}

	public void update(Enquiry studentEnquiry) {

		StringBuilder query = new StringBuilder();

		query.append("UPDATE tenquiry ");
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

	public List<Admission> fetch(Admission admission) {

		StringBuffer query = new StringBuffer();

		query.append("SELECT * FROM tenquiry ");
		query.append("WHERE temp_id like :tempID");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tempID", admission.getTempID() + "%");

		List<Admission> liststudentEnquiry = this.namedjdbctemplate.query(query.toString(), param,
				new RowMapper<Admission>() {

					@Override
					public Admission mapRow(ResultSet rs, int rowNum) throws SQLException {
						Admission admission = new Admission();
						admission.setFirstName(rs.getString(1));
						admission.setMiddleName(rs.getString(2));
						admission.setLastName(rs.getString(3));
						admission.setGender(rs.getString(4));
						admission.setQualification(rs.getString(5));
						admission.setProfession(rs.getString(6));
						admission.setAddress(rs.getString(7));
						admission.setMobileNo(rs.getString(8));
						admission.setEmailId(rs.getString(9));
						admission.setCourse(rs.getString(10));
						admission.setFess(rs.getString(11));
						admission.setDateOfEnquiry(rs.getDate(12));
						admission.setTempID(rs.getString(13));

						return admission;
					}

				});

		return liststudentEnquiry;

	}

	public void delete(String id) {

		StringBuffer query = new StringBuffer();

		query.append("DELETE FROM tenquiry ");
		query.append("WHERE first_name=?");

	}

	public String getNextIDno(String tableName) {

		StringBuilder query = new StringBuilder();

		query.append("select max(srno)+1 from ");
		query.append(tableName);
		query.append(" where srno>? ");

		Object[] inputs = new Object[] { 0 };
		return this.jdbctemplate.queryForObject(query.toString(), inputs, String.class);

	}

}
