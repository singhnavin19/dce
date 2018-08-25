package com.dce.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentReceipt {

	String uid;
	Integer installment;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date dueDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date paidDate;
	Integer amount;
	String receiptNo;

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getInstallment() {
		return this.installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

}
