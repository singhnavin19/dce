package com.dce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.AttendanceDAO;
import com.dce.entity.Attendance;
import com.dce.util.DateFormatter;

@Controller
@RequestMapping("attendance")
public class AttendanceController extends BaseController {

	@Autowired
	AttendanceDAO studentAttendanceDao;
	@Autowired
	DateFormatter dateFormatter;
	List<Attendance> studentAttendanceReport;

	@RequestMapping("/show")
	public ModelAndView show() {
		ModelAndView result = this.getModelAndView("attendance/studentAttendanceSearch");
		result.addObject("attendance", "true");

		return result;
	}

	@RequestMapping("/status")
	public ModelAndView status(@ModelAttribute Attendance studentAttendance) {
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
	public ModelAndView markAttendance(@ModelAttribute Attendance studentAttendance) {
		ModelAndView result = this.getModelAndView("forward:show");
		result.addObject("attendance", "true");
		this.studentAttendanceDao.updateDailyAttendance(studentAttendance);
		result.addObject("message", "attendance marked successfully");
		return result;
	}

	@RequestMapping("/addStudentForAttendance")
	public ModelAndView studentAttendance(String uid) {
		ModelAndView result = null;
		if (uid != null) {
			result = this.getModelAndView("attendance/addStudentForAttendance");
			result.addObject("attendance", "true");
		} else {
			System.out.println(uid);
			result = this.getModelAndView("attendance/addStudentForAttendance");
		}
		return result;

	}

	@RequestMapping("/saveStudentForAttendance")
	public ModelAndView addStudentForAttendance(@ModelAttribute Attendance studentAttendance,
			HttpServletRequest request) {

		String action = request.getParameter("save");

		if (action != null && action.contains("update")) {
			this.studentAttendanceDao.delete(studentAttendance.getUid());

		}
		ModelAndView result = this.getModelAndView("attendance/addStudentForAttendance");
		result.addObject("status", this.studentAttendanceDao.addStudentForAttendance(studentAttendance));
		result.addObject("attendance", "true");
		return result;
	}

	@RequestMapping("/studentAttendanceReport")
	public ModelAndView studentAttendanceReport(@ModelAttribute Attendance studentAttendance) {
		ModelAndView result = this.getModelAndView("attendance/studentAttendanceReport");

		this.studentAttendanceReport = this.studentAttendanceDao.fetchAttendanceReport(studentAttendance);
		result.addObject("studentAttendanceReport", this.studentAttendanceReport);
		result.addObject("attendance", "true");
		return result;

	}

}
