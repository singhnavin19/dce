package com.dce.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.AdmissionDAO;
import com.dce.dao.AttendanceDAO;
import com.dce.dao.CourseDAO;
import com.dce.dao.FeeDetailDAO;
import com.dce.entity.Admission;
import com.dce.entity.Course;
import com.dce.entity.FeeDetails;
import com.dce.service.EnquiryService;
import com.dce.util.DateFormatter;

@Controller
@RequestMapping(value = "/admission")
@Scope(value = "session")
public class AdmissionController extends BaseController {

	@Autowired
	EnquiryService enquiryService;
	@Autowired
	FeeDetailDAO feeDetailDAO;
	@Autowired
	AdmissionDAO admissionDAO;
	@Autowired
	CourseDAO CourseDAO;
	@Autowired
	AttendanceDAO attendanceDAO;
	@Autowired
	DateFormatter dateFormatter;

	@RequestMapping("/show")
	public ModelAndView show() {

		ModelAndView result = this.getModel();
		result.setViewName("studentSearch");
		result.addObject("admission", "true");
		return result;
	}

	@RequestMapping("/admissionForm")
	public ModelAndView admissionForm(@ModelAttribute Admission admission) {

		this.addRowInCourseAndFeeTable(admission);
		ModelAndView result = this.getModelAndView("admission/admissionFrom");
		result.addObject("admission", "true");
		result.addObject("admissionForm", admission);

		return result;
	}

	private void addRowInCourseAndFeeTable(Admission admission) {
		LinkedList<Course> courseList = new LinkedList<Course>();
		courseList.add(new Course());
		LinkedList<FeeDetails> feeDetaiList = new LinkedList<FeeDetails>();
		feeDetaiList.add(new FeeDetails());

		admission.setCourseDetails(courseList);
		admission.setFeeDetails(feeDetaiList);
	}

	@RequestMapping("saveStudentDetails")
	public ModelAndView saveStudentDetails(@ModelAttribute Admission admission) {

		this.admissionDAO.save(admission);
		this.feeDetailDAO.save(admission);
		this.CourseDAO.save(admission);
		this.attendanceDAO.save(admission.getUID());

		ModelAndView result = this.getModelAndView("admission/admissionFrom");
		String message = "Admission Process Completed Successfully : Your UID is:- " + admission.getUID();

		result.addObject("message", message);
		result.addObject("admission", "true");

		return result;
	}

	@RequestMapping("/addBlankRowStudentCourse")
	public ModelAndView addBlankRowStudentCourse(@ModelAttribute Admission admissionForm) {

		ModelAndView result = this.getModelAndView("admission/admissionFrom");
		List<Course> listCourse = admissionForm.getCourseDetails();
		listCourse.add(new Course());
		admissionForm.setCourseDetails(listCourse);
		result.addObject("admissionForm", admissionForm);
		result.addObject("admission", "true");
		return result;

	}

	@RequestMapping("/addBlankRowStudentReceipts")
	public ModelAndView addBlankRowStudentReceipts(@ModelAttribute Admission admissionForm) {

		ModelAndView result = this.getModelAndView("admission/admissionFrom");
		List<FeeDetails> listFeeDetail = admissionForm.getFeeDetails();
		listFeeDetail.add(new FeeDetails());
		admissionForm.setFeeDetails(listFeeDetail);
		result.addObject("admissionForm", admissionForm);
		result.addObject("admission", "true");
		return result;

	}

	@RequestMapping(value = "/searchStudentByTempID")
	public ModelAndView searchStudentByTempID(@ModelAttribute Admission admission) {

		ModelAndView result = this.getModel();
		result.addObject("admission", "true");
		if (admission.getFname() != null) {
			result.setViewName("studentSearch");
			result.addObject("listStudents", this.enquiryService.getStudentdetails(admission));
		} else {
			result.setViewName("admission/admissionFrom");
			admission = this.enquiryService.getStudentdetails(admission).get(0);
			this.addRowInCourseAndFeeTable(admission);
			result.addObject("admissionForm", admission);

		}
		return result;
	}

}
