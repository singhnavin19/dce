package com.dce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dce.entity.Admission;
import com.dce.service.InfoService;

@Controller
@RequestMapping("infromation")
public class InfoController extends BaseController {

	@Autowired
	InfoService infoService;

	@RequestMapping("show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView result = this.getModelAndView("information/infromation");
		result.addObject("information", true);
		return result;
	}

	@RequestMapping("getDetails")
	public ModelAndView getDetailsById(@RequestParam String uid) {

		ModelAndView result = this.getModelAndView("information/infromation");
		result.addObject("information", true);
		Admission admissionDetails = this.infoService.getDetailsById(uid);
		result.addObject("admissionForm", admissionDetails);
		return result;
	}

}
