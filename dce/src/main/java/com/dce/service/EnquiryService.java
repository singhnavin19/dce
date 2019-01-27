package com.dce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dce.dao.EnquiryDAO;
import com.dce.entity.Admission;
import com.dce.entity.Enquiry;

@Component
public class EnquiryService {

	@Autowired
	EnquiryDAO studentEnquiryDao;

	public String saveStudentEnquiryInfo(Enquiry studentenquiry) {

		return this.studentEnquiryDao.insert(studentenquiry);
	}

	public void insertStudentEnquiryInfo(Enquiry studentenquiry) {

	}

	public List<Admission> getStudentdetails(Admission admission) {
		return this.studentEnquiryDao.fetch(admission);

	}

}
