package com.dce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.StudentAttendanceDao;
import com.dce.entity.StudentAttendance;
import com.dce.util.DateFormatter;

@Controller
@RequestMapping("attendance")
public class StudentAttendanceController extends BaseController {

	@Autowired
	StudentAttendanceDao studentAttendanceDao;
	@Autowired
	DateFormatter dateFormatter;
	List<StudentAttendance> studentAttendanceReport;

	@RequestMapping("/show")
	public ModelAndView show() {
		ModelAndView result = this.getModelAndView("attendance/studentAttendanceSearch");
		result.addObject("attendance", "true");

		return result;
	}

	@RequestMapping("/status")
	public ModelAndView status(@ModelAttribute StudentAttendance studentAttendance) {
		ModelAndView result = this.getModelAndView("attendance/studentAttendanceSearch");
		studentAttendance = this.studentAttendanceDao.fetchDailyAttendance(studentAttendance);
		result.addObject("studentAttendanceReport", studentAttendance);
		result.addObject("attendance", "true");
		if (studentAttendance.getMessage() == null) {
			result.addObject("status", "true");
		}

		return result;
	}

	@RequestMapping("/markAttendance")
	public ModelAndView markAttendance(@ModelAttribute StudentAttendance studentAttendance) {
		ModelAndView result = this.getModelAndView("forward:show");
		result.addObject("attendance", "true");
		this.studentAttendanceDao.updateDailyAttendance(studentAttendance);
		result.addObject("message", "attendance marked successfully");
		return result;
	}

	@RequestMapping("/addStudentForAttendance")
	public ModelAndView studentAttendance() {
		ModelAndView result = this.getModelAndView("attendance/addStudentForAttendance");
		result.addObject("attendance", "true");
		return result;
	}

	@RequestMapping("/saveStudentForAttendance")
	public ModelAndView addStudentForAttendance(@ModelAttribute StudentAttendance studentAttendance) {
		ModelAndView result = this.getModelAndView("attendance/addStudentForAttendance");
		result.addObject("status", this.studentAttendanceDao.addStudentForAttendance(studentAttendance));
		result.addObject("attendance", "true");
		return result;
	}

	@RequestMapping("/studentAttendanceReport")
	public ModelAndView studentAttendanceReport(@ModelAttribute StudentAttendance studentAttendance) {
		ModelAndView result = this.getModelAndView("attendance/studentAttendanceReport");

		this.studentAttendanceReport = this.studentAttendanceDao.fetchAttendanceReport(studentAttendance);
		result.addObject("studentAttendanceReport", this.studentAttendanceReport);
		result.addObject("attendance", "true");
		return result;

	}

}
