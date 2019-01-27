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

import com.dce.entity.Attendance;

@Component
public class AttendanceDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void save(String UID) {

		this.insert(UID);
	}

	private void insert(String UID) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("uid", UID);

		StringBuilder query = new StringBuilder();

		query.append("insert into tattendance ");
		query.append(
				"select a.uid,TRIM(CONCAT(a.first_name,' ',a.middle_name,' ',a.last_name)) as fullName,GROUP_CONCAT(b.course_name SEPARATOR ',') as course_name ");
		query.append("from tadmission a,tcourse_details b ");
		query.append("where a.uid=b.uid ");
		query.append("and a.uid=:uid");

		this.namedParameterJdbcTemplate.update(query.toString(), param);
	}

	public Attendance fetchDailyAttendance(Attendance studentAttendance) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", studentAttendance.getUid());
		studentAttendance.reset(studentAttendance);

		studentAttendance = this.studentDetails(param, studentAttendance);
		if (studentAttendance.getUid() != null) {
			try {
				StringBuffer query = this.studentDetailQuery();
				studentAttendance = this.namedParameterJdbcTemplate.queryForObject(query.toString(), param,
						new BeanPropertyRowMapper<Attendance>(Attendance.class));
			} catch (IncorrectResultSizeDataAccessException e) {
				this.insertDailyAttendance(param);
			}
		}
		return studentAttendance;
	}

	public void updateDailyAttendance(Attendance studentAttendance) {
		StringBuffer query = new StringBuffer();
		query.append("update tattendance_daily ");
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

	public Attendance addStudentForAttendance(Attendance studentAttendance) {

		Map<String, Object> newparam = new HashMap<String, Object>();
		newparam.put("uid", studentAttendance.getUid());

		Attendance newstudentAttendance = this.studentDetails(newparam, studentAttendance);
		newparam.put("fullName", studentAttendance.getFullName());
		newparam.put("course", studentAttendance.getCourse());

		if (newstudentAttendance.getMessage() != null) {
			StringBuffer query = new StringBuffer();
			query.append("insert into tattendance ");
			query.append("values (:uid,:fullName,:course) ");

			this.namedParameterJdbcTemplate.update(query.toString(), newparam);
			newstudentAttendance.setMessage("ID inserted Successfully");
		} else {
			newstudentAttendance.setMessage("ID is already Exist,Kindly update the details");

		}
		return newstudentAttendance;
	}

	private Attendance studentDetails(Map<String, Object> param, Attendance studentAttendance) {

		StringBuffer query = new StringBuffer("select * from tattendance where uid=:uid");
		try {
			studentAttendance = this.namedParameterJdbcTemplate.queryForObject(query.toString(), param,
					new BeanPropertyRowMapper<Attendance>(Attendance.class));
		} catch (IncorrectResultSizeDataAccessException e) {
			studentAttendance.setMessage("This is not valid ID.");

		}
		return studentAttendance;
	}

	private void insertDailyAttendance(Map<String, Object> param) {
		param.put("attendece_date", new Date());
		StringBuffer newquery = new StringBuffer();
		newquery.append("INSERT INTO tattendance_daily ");
		newquery.append("(uid,attendece_date ) ");
		newquery.append(" values (:uid,:attendece_date) ");

		this.namedParameterJdbcTemplate.update(newquery.toString(), param);
	}

	private StringBuffer studentDetailQuery() {
		StringBuffer query = new StringBuffer();
		query.append("select a.*,b.check_in_datetime,b.check_out_datetime,b.attendece_date ");
		query.append("from tattendance a,tattendance_daily b ");
		query.append("where a.uid=b.uid ");
		query.append("and a.uid=:uid ");
		query.append("and DATE_FORMAT(attendece_date, '%d/%m/%Y')=DATE_FORMAT(SYSDATE(), '%d/%m/%Y') ");
		return query;
	}

	public List<Attendance> fetchAttendanceReport(Attendance studentAttendance) {
		StringBuffer query = new StringBuffer();
		HashMap<String, Object> param = new HashMap<String, Object>();
		query.append("select a.*,b.check_in_datetime,b.check_out_datetime,b.attendece_date ");
		query.append("from tattendance a,tattendance_daily b ");
		query.append("where a.uid=b.uid ");

		if (studentAttendance.getUid() != null) {
			query.append("and a.uid=:uid ");
			param.put("uid", studentAttendance.getUid());
		} else {
			query.append(
					"and DATE_FORMAT(attendece_date, '%d/%m/%Y')=DATE_FORMAT(SYSDATE(), '%d/%m/%Y') ORDER BY attendece_date DESC");
		}
		List<Attendance> StudentAttendanceReport = this.namedParameterJdbcTemplate.query(query.toString(), param,
				new BeanPropertyRowMapper<Attendance>(Attendance.class));

		return StudentAttendanceReport;

	}

	public void delete(String UID) {

		StringBuilder query = new StringBuilder("DELETE FROM tattendance where uid=?");
		this.jdbcTemplate.update(query.toString(), UID);

	}

}
