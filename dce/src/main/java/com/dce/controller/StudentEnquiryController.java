package com.dce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.entity.StudentEnquiry;
import com.dce.service.StudentEnquiryService;

@Controller
@RequestMapping(value = "/enquiry")
@Scope(value = "session")
public class StudentEnquiryController extends BaseController {

	@Autowired
	StudentEnquiryService studentEnquiryService;

	@RequestMapping(value = "/show")
	public ModelAndView show() {
		ModelAndView result = this.getModelAndView("dceEnquiry");
		result.addObject("enquiry", "true");
		return result;
	}

	@RequestMapping(value = "/saveEnquiry")
	public ModelAndView saveEnquiry(@ModelAttribute StudentEnquiry studentenquiry) {

		boolean isReadonly = true;
		ModelAndView result = this.getModelAndView("dceEnquiry");
		String tempID = this.studentEnquiryService.saveStudentEnquiryInfo(studentenquiry);
		// result.addObject("studentenquiry", studentenquiry);
		result.addObject("isReadonly", isReadonly);
		result.addObject("message",
				"Enquiry details saved successfully,Kindly note the Temporary ID for future refrence:- <mark style='color:green'>"
						+ tempID + "</mark>");
		result.addObject("enquiry", "true");
		return result;

	}

}
