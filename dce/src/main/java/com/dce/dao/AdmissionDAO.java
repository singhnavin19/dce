package com.dce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

	public void save(Admission admission) {

		this.insert(admission);

	}

	private void insert(Admission admission) {
		String nextID = this.studentEnquiryDao.getNextIDno("tadmission");
		admission.setUID(admission.getFname().substring(0, 2).toUpperCase() + (nextID == null ? "1" : nextID));
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
		query.append(" :fname, ");
		query.append(" :mname, ");
		query.append(" :lname, ");
		query.append(" :gender, ");
		query.append(" :qualification, ");
		query.append(" :profession, ");
		query.append(" :address, ");
		query.append(" :mobileNo, ");
		query.append(" :emailID, ");
		query.append(" :tempID, ");
		query.append(" :UID,curdate()) ");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(admission));
	}

}
