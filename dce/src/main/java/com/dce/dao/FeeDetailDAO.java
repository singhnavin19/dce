package com.dce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.Admission;
import com.dce.entity.FeeDetails;

@Component
public class FeeDetailDAO {
	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;

	@Autowired
	JdbcTemplate jdbctemplate;

	public void save(Admission admission) {

		for (FeeDetails feeDetails : admission.getFeeDetails()) {
			feeDetails.setUid(admission.getUID());
			this.insert(feeDetails);
		}

	}

	public List<FeeDetails> fetch(String Uid) {

		StringBuffer query = new StringBuffer();
		query.append("select * from tfees_details ");
		query.append(" where uid=?");

		List<FeeDetails> feeDetailsList = this.jdbctemplate.query(query.toString(), new Object[] { Uid },
				new BeanPropertyRowMapper<FeeDetails>(FeeDetails.class));

		return feeDetailsList;
	}

	private void insert(FeeDetails feeDetails) {

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO  tfees_details (uid,installment,Due_date,Paid_date,Amount,Receipt_No");
		query.append(") VALUES (:uid,:installment,:dueDate,:paidDate,:amount,:receiptNo)");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(feeDetails));
	}

	private void delete(String Uid) {
		StringBuffer query = new StringBuffer();
		query.append("delete from tstudent_receipt ");
		query.append(" where uid=?");

		this.jdbctemplate.update(query.toString(), Uid);

	}

}
