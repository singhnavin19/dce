package com.dce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dce.dao.AdmissionDAO;
import com.dce.dao.AttendanceDAO;
import com.dce.dao.CourseDAO;
import com.dce.dao.FeeDetailDAO;
import com.dce.entity.Admission;
import com.dce.entity.Course;
import com.dce.entity.FeeDetails;

@Service("admissionService")
public class AdmissionService {

	@Autowired
	CourseDAO courseDAO;
	@Autowired
	AdmissionDAO admissionDAO;
	@Autowired
	FeeDetailDAO feeDetailDAO;
	@Autowired
	AttendanceDAO attendanceDAO;

	public void addRowInCourseAndFeeTable(Admission admission) {

		this.addRowInCourseTable(admission);
		this.addRowInFeeDetailsTable(admission);

	}

	public void addRowInCourseTable(Admission admissionForm) {

		List<Course> listCourse = admissionForm.getCourseDetails();
		listCourse.add(new Course());
		admissionForm.setCourseDetails(listCourse);

	}

	public void addRowInFeeDetailsTable(Admission admissionForm) {
		List<FeeDetails> feeDetailsList = admissionForm.getFeeDetails();
		feeDetailsList.add(new FeeDetails());
		admissionForm.setFeeDetails(feeDetailsList);
	}

	public void save(Admission admission) {

		this.admissionDAO.save(admission);
		this.feeDetailDAO.save(admission);
		this.courseDAO.save(admission);
		this.attendanceDAO.save(admission.getUID());

	}
}
