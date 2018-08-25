package com.dce.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.StudentAttendance;

@Component
public class StudentAttendanceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public StudentAttendance fetchDailyAttendance(StudentAttendance studentAttendance) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", studentAttendance.getUid());
		studentAttendance.reset(studentAttendance);

		studentAttendance = this.studentDetails(param, studentAttendance);
		if (studentAttendance.getUid() != null) {
			try {
				StringBuffer query = this.studentDetailQuery();
				studentAttendance = this.namedParameterJdbcTemplate.queryForObject(query.toString(), param,
						new BeanPropertyRowMapper<StudentAttendance>(StudentAttendance.class));
			} catch (IncorrectResultSizeDataAccessException e) {
				this.insertDailyAttendance(param);
			}
		}
		return studentAttendance;
	}

	public void updateDailyAttendance(StudentAttendance studentAttendance) {
		StringBuffer query = new StringBuffer();
		query.append("update tstudent_attendencereport ");
		if (studentAttendance.getCheck_in_datetime() == null)
			query.append("set check_in_datetime=SYSDATE() ");
		else
			query.append("set check_out_datetime=SYSDATE() ");

		query.append(" where uid=:uid ");
		query.append("and DATE_FORMAT(attendece_date, '%d/%m/%Y')=DATE_FORMAT(SYSDATE(), '%d/%m/%Y') ");
		Map<String, Object> inputparam = new HashMap<String, Object>();
		inputparam.put("uid", studentAttendance.getUid());

		this.namedParameterJdbcTemplate.update(query.toString(), inputparam);
	}

	public boolean addStudentForAttendance(StudentAttendance studentAttendance) {

		boolean result = false;
		Map<String, Object> newparam = new HashMap<String, Object>();
		newparam.put("uid", studentAttendance.getUid());

		StudentAttendance newstudentAttendance = this.studentDetails(newparam, studentAttendance);
		newparam.put("fullName", studentAttendance.getFullName());
		newparam.put("course", studentAttendance.getCourse());

		if (newstudentAttendance.getUid() == null) {
			StringBuffer query = new StringBuffer();
			query.append("insert into tstudent_attendence ");
			query.append("values (:uid,:fullName,:course) ");

			this.namedParameterJdbcTemplate.update(query.toString(), newparam);
			result = true;
		}
		return result;
	}

	private StudentAttendance studentDetails(Map<String, Object> param, StudentAttendance studentAttendance) {

		StringBuffer query = new StringBuffer("select * from tstudent_attendence where uid=:uid");
		try {
			studentAttendance = this.namedParameterJdbcTemplate.queryForObject(query.toString(), param,
					new BeanPropertyRowMapper<StudentAttendance>(StudentAttendance.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			studentAttendance.setMessage("This is not valid ID.");

		}
		return studentAttendance;
	}

	private void insertDailyAttendance(Map<String, Object> param) {
		param.put("attendece_date", new Date());
		StringBuffer newquery = new StringBuffer();
		newquery.append("INSERT INTO tstudent_attendencereport ");
		newquery.append("(uid,attendece_date ) ");
		newquery.append(" values (:uid,:attendece_date) ");

		this.namedParameterJdbcTemplate.update(newquery.toString(), param);
	}

	private StringBuffer studentDetailQuery() {
		StringBuffer query = new StringBuffer();
		query.append("select a.*,b.check_in_datetime,b.check_out_datetime,b.attendece_date ");
		query.append("from tstudent_attendence a,tstudent_attendencereport b ");
		query.append("where a.uid=b.uid ");
		query.append("and a.uid=:uid ");
		query.append("and DATE_FORMAT(attendece_date, '%d/%m/%Y')=DATE_FORMAT(SYSDATE(), '%d/%m/%Y') ");
		return query;
	}

	public List<StudentAttendance> fetchAttendanceReport(StudentAttendance studentAttendance) {
		StringBuffer query = new StringBuffer();
		HashMap<String, Object> param = new HashMap<String, Object>();

		query.append("select a.*,b.check_in_datetime,b.check_out_datetime,b.attendece_date ");
		query.append("from tstudent_attendence a,tstudent_attendencereport b ");
		query.append("where a.uid=b.uid ");
		if (studentAttendance.getUid() != null) {
			query.append("and a.uid=:uid ");
			param.put("uid", studentAttendance.getUid());
		} else {
			query.append("and DATE_FORMAT(attendece_date, '%d/%m/%Y')=DATE_FORMAT(SYSDATE(), '%d/%m/%Y') ");
		}
		List<StudentAttendance> StudentAttendanceReport = this.namedParameterJdbcTemplate.query(query.toString(), param,
				new BeanPropertyRowMapper<StudentAttendance>(StudentAttendance.class));

		return StudentAttendanceReport;

	}

}
