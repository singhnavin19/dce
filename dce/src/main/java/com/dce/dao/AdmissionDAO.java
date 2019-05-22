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

@Component
public class AdmissionDAO {

	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;
	@Autowired
	EnquiryDAO studentEnquiryDao;
	@Autowired
	JdbcTemplate jdbctemplate;

	public List<Admission> getDetailsByIds(List<String> uid) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM tadmission ");
		query.append(" WHERE uid in(:uid)");

		Map<String, Object> param = new HashMap<>();
		param.put("uid", uid);

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

	public void save(Admission admission) {

		this.insert(admission);

	}

	private void insert(Admission admission) {
		String nextID = this.studentEnquiryDao.getNextIDno("tadmission");
		admission.setUID(admission.getFirstName().substring(0, 2).toUpperCase() + (nextID == null ? "1" : nextID));
		StringBuffer query = new StringBuffer();

		query.append("INSERT INTO tadmission (");
		query.append("first_name, ");
		query.append("middle_name, ");
		query.append("last_name, ");
		query.append("gender, ");
		query.append("qualification, ");
		query.append("profession, ");
		query.append("address, ");
		query.append("mobile_no, ");
		query.append("email_id, ");
		query.append("temp_id ,uid,dateOfAdmission");
		query.append(" ) VALUES ( ");
		query.append(" :firstName, ");
		query.append(" :middleName, ");
		query.append(" :lastName, ");
		query.append(" :gender, ");
		query.append(" :qualification, ");
		query.append(" :profession, ");
		query.append(" :address, ");
		query.append(" :mobileNo, ");
		query.append(" :emailId, ");
		query.append(" :tempID, ");
		query.append(" :UID,curdate()) ");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(admission));
	}

}
