package com.dce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dce.dao.StudentEnquiryDao;
import com.dce.entity.StudentEnquiry;

@Component
public class StudentEnquiryService {

	@Autowired
	StudentEnquiryDao studentEnquiryDao;

	public String saveStudentEnquiryInfo(StudentEnquiry studentenquiry) {

		return this.studentEnquiryDao.insert(studentenquiry);
	}

	public void insertStudentEnquiryInfo(StudentEnquiry studentenquiry) {

	}

	public List<StudentEnquiry> getStudentdetails(StudentEnquiry studentenquiry) {
		return this.studentEnquiryDao.fetch(studentenquiry);

	}

}
