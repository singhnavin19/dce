package com.dce.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.StudentAdmissionDao;
import com.dce.dao.StudentCourseDao;
import com.dce.dao.StudentReceiptDao;
import com.dce.entity.StudentCourse;
import com.dce.entity.StudentCourseForm;
import com.dce.entity.StudentEnquiry;
import com.dce.entity.StudentReceipt;
import com.dce.entity.StudentReceiptForm;
import com.dce.service.StudentEnquiryService;
import com.dce.util.DateFormatter;

@Controller
@RequestMapping(value = "/admission")
@Scope(value = "session")
public class StudentAdmissionController extends BaseController {

	@Autowired
	StudentEnquiryService studentEnquiryService;
	@Autowired
	StudentReceiptDao studentReceiptDao;
	@Autowired
	StudentAdmissionDao studentAdmissionDao;
	@Autowired
	StudentCourseDao studentCourseDao;
	@Autowired
	DateFormatter dateFormatter;

	@RequestMapping("/show")
	public ModelAndView showSearch(@ModelAttribute StudentEnquiry studentenquiry, HttpServletRequest request) {
		ModelAndView result = this.getModelAndView("studentSearch");
		result.addObject("admission", "true");
		result.addObject("baseUrl", request.getContextPath());
		return result;
	}

	@RequestMapping(value = "/searchStudentByTempID")
	public ModelAndView searchStudentByTempID(@ModelAttribute StudentEnquiry studentenquiry) {

		ModelAndView result = this.getModel();
		result.addObject("admission", "true");
		if (studentenquiry.getFname() != null) {
			result.setViewName("studentSearch");
			result.addObject("listStudents", this.studentEnquiryService.getStudentdetails(studentenquiry));
		} else {
			result.setViewName("dceEnquiry");
			result.addObject("studentenquiry", this.studentEnquiryService.getStudentdetails(studentenquiry).get(0));
		}
		return result;
	}

	@RequestMapping("saveStudentCourse")
	public ModelAndView studentCourse(@ModelAttribute StudentCourseForm studentCourseForm) {
		ModelAndView result = this.getModelAndView("studentReceipt");
		for (StudentCourse studentCourse : studentCourseForm.getStudentCourses()) {
			this.studentCourseDao.insert(studentCourse);
		}
		StudentReceiptForm studentReceiptForm = new StudentReceiptForm();
		StudentReceipt studentReceipt = new StudentReceipt();
		studentReceiptForm.getStudentReceiptForm().add(studentReceipt);
		result.addObject("studentReceiptForm", studentReceiptForm);
		result.addObject("admission", "true");

		return result;
	}

	@RequestMapping("saveStudentReceipts")
	public ModelAndView saveStudentReceipts(@ModelAttribute StudentReceiptForm studentReceiptForm) {
		ModelAndView result = this.getModelAndView("redirect:show");
		result.addObject("admission", "true");
		this.studentReceiptDao.save(studentReceiptForm);
		result.addObject("listStudentReceipt", studentReceiptForm);
		result.addObject("dateUtils", this.dateFormatter);
		String message = "Admission Process Completed Successfully : Your UID is:- "
				+ studentReceiptForm.getStudentReceiptForm().get(0).getUid();
		result.addObject("message", message);
		return result;
	}

	@RequestMapping("/addBlankRowStudentCourse")
	public ModelAndView addBlankRowStudentCourse(@ModelAttribute StudentCourseForm studentCourseForm) {

		ModelAndView result = this.getModelAndView("studentCourse");
		StudentCourse s = new StudentCourse();
		result.addObject("uid", studentCourseForm.getStudentCourses().get(0).getUid());
		studentCourseForm.getStudentCourses().add(s);
		result.addObject("studentCoursesForm", studentCourseForm);
		result.addObject("admission", "true");
		return result;

	}

	@RequestMapping("/addBlankRowStudentReceipts")
	public ModelAndView addBlankRowStudentReceipts(@ModelAttribute StudentReceiptForm studentReceiptForm) {

		ModelAndView result = this.getModelAndView("studentReceipt");
		StudentReceipt studentReceipt = new StudentReceipt();
		studentReceiptForm.getStudentReceiptForm().add(studentReceipt);
		result.addObject("studentReceiptForm", studentReceiptForm);
		result.addObject("admission", "true");
		return result;

	}

	@RequestMapping("saveStudentDetails")
	public ModelAndView saveStudentsPersonalDetails(@ModelAttribute StudentEnquiry studentEnquiry) {

		this.studentAdmissionDao.insert(studentEnquiry);
		ModelAndView result = this.getModelAndView("studentCourse");
		result.addObject("uid", studentEnquiry.getTempID());
		StudentCourseForm studentCourseForm = new StudentCourseForm();
		LinkedList<StudentCourse> list = new LinkedList<StudentCourse>();
		list.add(new StudentCourse());
		studentCourseForm.setStudentCourses(list);
		result.addObject("studentCoursesForm", studentCourseForm);
		result.addObject("admission", "true");
		return result;
	}

	@RequestMapping("admissionForm")
	public ModelAndView admissionForm(@ModelAttribute StudentEnquiry studentEnquiry) {
		ModelAndView result = this.getModelAndView("dceEnquiry");
		result.addObject("admission", "true");
		return result;
	}

}
