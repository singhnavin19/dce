package com.dce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.StudentReceipt;
import com.dce.entity.StudentReceiptForm;

@Component
public class StudentReceiptDao {
	@Autowired
	NamedParameterJdbcTemplate namedjdbctemplate;

	@Autowired
	JdbcTemplate jdbctemplate;

	public void save(StudentReceiptForm studentReceiptForm) {
		/*
		 * if
		 * (this.fetch(studentReceiptForm.getListStudentReceipt().get(0).getUid()).size(
		 * ) > 0) {
		 * this.delete(studentReceiptForm.getListStudentReceipt().get(0).getUid()); }
		 */

		for (StudentReceipt studentReceipt : studentReceiptForm.getStudentReceiptForm()) {
			this.insert(studentReceipt);
		}

	}

	public List<StudentReceipt> fetch(String Uid) {

		StringBuffer query = new StringBuffer();
		query.append("select * from tstudent_receipt ");
		query.append(" where uid=?");

		List<StudentReceipt> listStudentReceipt = this.jdbctemplate.query(query.toString(), new Object[] { Uid },
				new BeanPropertyRowMapper<StudentReceipt>(StudentReceipt.class));

		return listStudentReceipt;
	}

	private void insert(StudentReceipt studentReceipt) {

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO  tstudent_receipt (uid,installment,Due_date,Paid_date,Amount,Receipt_No");
		query.append(") VALUES (:uid,:installment,:dueDate,:paidDate,:amount,:receiptNo)");

		this.namedjdbctemplate.update(query.toString(), new BeanPropertySqlParameterSource(studentReceipt));
	}

	private void delete(String Uid) {
		StringBuffer query = new StringBuffer();
		query.append("delete from tstudent_receipt ");
		query.append(" where uid=?");

		this.jdbctemplate.update(query.toString(), Uid);

	}

}
