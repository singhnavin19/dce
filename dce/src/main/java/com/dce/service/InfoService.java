package com.dce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dce.dao.AdmissionDAO;
import com.dce.dao.CourseDAO;
import com.dce.dao.FeeDetailDAO;
import com.dce.dao.InfoDAO;
import com.dce.entity.Admission;

@Service
public class InfoService {

	@Autowired
	AdmissionDAO admissionDAO;
	@Autowired
	FeeDetailDAO feeDetailDAO;
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	InfoDAO infoDAO;

	public Admission getDetailsById(String uid) {

		List<String> uids = new ArrayList<>();
		uids.add(uid);
		List<Admission> useDetails = this.infoDAO.getDetailsById(uid);
		if (!useDetails.isEmpty()) {
			Admission AdmissionDetail = useDetails.get(0);
			AdmissionDetail.setFeeDetails(this.feeDetailDAO.fetch(uid));
			AdmissionDetail.setCourseDetails(this.courseDAO.fetch(uid));
			return AdmissionDetail;
		}
		return null;

	}

}
