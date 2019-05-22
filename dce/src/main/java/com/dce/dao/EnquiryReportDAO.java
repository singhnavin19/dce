package com.dce.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.Admission;

@Component
public class EnquiryReportDAO {
	@Autowired
	JdbcTemplate jdbctemplate;
	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;

	public List<Admission> fetch() {

		StringBuffer query = new StringBuffer();

		query.append("SELECT * FROM tenquiry ");
		query.append("ORDER BY dateOfEnquiry DESC ");

		List<Admission> liststudentEnquiry = this.jdbctemplate.query(query.toString(), new RowMapper<Admission>() {

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

}
