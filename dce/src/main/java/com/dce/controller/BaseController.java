package com.dce.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.dce.util.ApplicationContextHelper;
import com.dce.util.DateFormatter;
import com.dce.util.DceConstant;

public class BaseController {

	@Autowired
	DateFormatter dateFormatter;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ApplicationContextHelper applicationContextHelper;
	@Autowired
	DceConstant dceConstant;

	public final ModelAndView getModelAndView(String viewName) {

		ModelAndView model = new ModelAndView(viewName);
		model.addObject("dateUtils", this.dateFormatter);
		model.addObject("baeUrl", this.servletContext.getContextPath());
		model.addObject("applicationContextHelper", this.applicationContextHelper);
		model.addObject("dceConstant", this.dceConstant);
		return model;

	}

	public final ModelAndView getModel() {

		ModelAndView model = new ModelAndView();
		model.addObject("dateUtils", this.dateFormatter);
		model.addObject("baeUrl", this.servletContext.getContextPath());
		model.addObject("applicationContextHelper", this.applicationContextHelper);
		return model;

	}

}
