package com.dce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dce.dao.EnquiryReportDAO;
import com.dce.entity.Enquiry;
import com.dce.service.EnquiryService;
import com.dce.service.UserService;
import com.dce.util.DceConstant;

@Controller
@RequestMapping(value = "/enquiry")
@Scope(value = "session")
public class EnquiryController extends BaseController {

	@Autowired
	EnquiryService studentEnquiryService;
	@Autowired
	EnquiryReportDAO enquiryReportDAO;
	@Autowired
	UserService userService;

	public boolean TRUE = true;

	@ModelAttribute
	public ModelAndView enableEnquiryTabForAllRequest(ModelAndView result) {
		result.addObject(DceConstant.ENQUIRY_TAB, this.TRUE);
		return result;
	}

	@RequestMapping(value = "/show")
	public ModelAndView show(String UID) {

		ModelAndView result = this.getModelAndView("enquiry/listEnquiry");
		result.addObject("listEnquiry", this.enquiryReportDAO.fetch());
		System.out.println(this.userService.getCurrentUser());

		return result;

	}

	@RequestMapping("enquiryForm")
	public ModelAndView enquiryForm() {
		ModelAndView result = this.getModelAndView("enquiry/enquiryForm");
		result.addObject(DceConstant.ENQUIRY_TAB, this.TRUE);
		return result;
	}

	@RequestMapping(value = "/saveEnquiry")
	public ModelAndView saveEnquiry(@ModelAttribute Enquiry enquiry) {

		ModelAndView result = this.getModelAndView("enquiry/enquiryForm");
		String tempID = this.studentEnquiryService.saveStudentEnquiryInfo(enquiry);
		result.addObject("message",
				"Enquiry details saved successfully,Kindly note the Temporary ID for future refrence:- <mark style='color:green'>"
						+ tempID + "</mark>");
		result.addObject(DceConstant.ENQUIRY_TAB, this.TRUE);
		return result;

	}

	@RequestMapping(value = "/showChaseForm")
	public ModelAndView showChaseForm(String id) {

		return null;
	}

}
