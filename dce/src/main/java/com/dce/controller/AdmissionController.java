package com.dce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.entity.Admission;
import com.dce.service.AdmissionService;
import com.dce.service.EnquiryService;
import com.dce.util.DateFormatter;

@Controller
@RequestMapping(value = "/admission")
@Scope(value = "session")
public class AdmissionController extends BaseController {

	private static final Boolean TRUE = true;
	private static final String ADMISSION_FORM = "admissionForm";
	private static final String ADMISSION_ADMISSION_FROM = "admission/admissionFrom";
	@Autowired
	EnquiryService enquiryService;
	@Autowired
	AdmissionService admissionService;
	@Autowired
	DateFormatter dateFormatter;

	@RequestMapping("/show")
	public ModelAndView show() {

		ModelAndView result = this.getModel();
		result.setViewName("studentSearch");
		result.addObject("admission", TRUE);
		return result;
	}

	@RequestMapping("/admissionForm")
	public ModelAndView admissionForm(@ModelAttribute Admission admission) {

		this.admissionService.addRowInCourseAndFeeTable(admission);
		ModelAndView result = this.getModelAndView(ADMISSION_ADMISSION_FROM);
		result.addObject("admission", TRUE);
		result.addObject(ADMISSION_FORM, admission);

		return result;
	}

	@RequestMapping("saveStudentDetails")
	public ModelAndView saveStudentDetails(@ModelAttribute Admission admission) {

		ModelAndView result = this.getModelAndView(ADMISSION_ADMISSION_FROM);
		this.admissionService.save(admission);
		String message = "Admission Process Completed Successfully : Your UID is:- " + admission.getUID();
		result.addObject("message", message);
		result.addObject("admission", TRUE);

		return result;
	}

	@RequestMapping("/addRowInCourseTable")
	public ModelAndView addRowInCourseTable(@ModelAttribute Admission admissionForm) {

		this.admissionService.addRowInCourseTable(admissionForm);
		ModelAndView result = this.getModelAndView(ADMISSION_ADMISSION_FROM);
		result.addObject(ADMISSION_FORM, admissionForm);
		result.addObject("admission", TRUE);
		return result;

	}

	@RequestMapping("/addRowInFeeDetailsTable")
	public ModelAndView addRowInFeeDetailsTable(@ModelAttribute Admission admissionForm) {

		this.admissionService.addRowInFeeDetailsTable(admissionForm);
		ModelAndView result = this.getModelAndView(ADMISSION_ADMISSION_FROM);
		result.addObject(ADMISSION_FORM, admissionForm);
		result.addObject("admission", TRUE);
		return result;

	}

	@RequestMapping(value = "/searchStudentByTempID")
	public ModelAndView searchStudentByTempID(@ModelAttribute Admission admission) {

		ModelAndView result = this.getModel();
		result.addObject("admission", TRUE);

		if (admission.getFirstName() != null) {
			result.setViewName("studentSearch");
			result.addObject("listStudents", this.enquiryService.getStudentdetails(admission));
		} else {
			admission = this.enquiryService.getStudentdetails(admission).get(0);
			this.admissionService.addRowInCourseAndFeeTable(admission);
			result.addObject(ADMISSION_FORM, admission);
			result.setViewName(ADMISSION_ADMISSION_FROM);

		}
		return result;
	}

}
