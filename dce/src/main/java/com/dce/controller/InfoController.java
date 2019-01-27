package com.dce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.CourseDAO;
import com.dce.dao.FeeDetailDAO;
import com.dce.dao.InfoDAO;
import com.dce.entity.Admission;
import com.dce.entity.Attendance;
import com.dce.entity.Course;
import com.dce.entity.FeeDetails;

@Controller
@RequestMapping("infromation")
public class InfoController extends BaseController {

	@Autowired
	InfoDAO infoDAO;

	@Autowired
	CourseDAO courseDAO;
	@Autowired
	FeeDetailDAO feeDetailDAO;

	@RequestMapping("show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView result = this.getModelAndView("information/infromation");
		result.addObject("information", true);
		return result;
	}

	@RequestMapping("getDetails")
	public ModelAndView getDetailsById(@ModelAttribute Admission admission) {

		Admission admissionFrom = new Admission();
		ModelAndView result = this.getModelAndView("information/infromation");
		result.addObject("information", true);
		List<Course> CourseList = this.courseDAO.fetch(admission.getUID());
		List<FeeDetails> FeeDetaiList = this.feeDetailDAO.fetch(admission.getUID());
		List<Admission> admissionDetail = this.infoDAO.getDetailsById(admission.getUID());
		admissionFrom = admissionDetail.get(0);
		admissionFrom.setFeeDetails(FeeDetaiList);
		admissionFrom.setCourseDetails(CourseList);
		result.addObject("admissionForm", admissionFrom);
		return result;
	}

	@RequestMapping("saveDetails")
	public ModelAndView saveDetailsById(@ModelAttribute Attendance studentAttendance) {
		ModelAndView result = this.getModelAndView("information/infromation");
		result.addObject("information", true);
		result.addObject("studentDetails", this.infoDAO.getDetailsById(studentAttendance.getUid()));
		return result;
	}

}
