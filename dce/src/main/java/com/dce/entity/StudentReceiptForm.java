package com.dce.entity;

import java.util.LinkedList;

public class StudentReceiptForm {

	private LinkedList<StudentReceipt> studentReceiptForm = new LinkedList<StudentReceipt>();

	public LinkedList<StudentReceipt> getStudentReceiptForm() {
		return this.studentReceiptForm;
	}

	public void setStudentReceiptForm(LinkedList<StudentReceipt> studentReceiptForm) {
		this.studentReceiptForm = studentReceiptForm;
	}

}
