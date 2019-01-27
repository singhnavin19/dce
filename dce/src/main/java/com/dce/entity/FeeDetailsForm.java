package com.dce.entity;

import java.util.LinkedList;

public class FeeDetailsForm {

	private LinkedList<FeeDetails> studentReceiptForm = new LinkedList<FeeDetails>();

	public LinkedList<FeeDetails> getStudentReceiptForm() {
		return this.studentReceiptForm;
	}

	public void setStudentReceiptForm(LinkedList<FeeDetails> studentReceiptForm) {
		this.studentReceiptForm = studentReceiptForm;
	}

}
