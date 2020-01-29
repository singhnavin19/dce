package com.dce.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateFormatter {

	public static void main(String[] args) {

		// String dateString = dateToString(new Date());
		System.out.println(dateToString(new Date()));
		System.out.println(dateToString(new Date(), "dd/MM/YYYY hh:mm a"));

	}

	public static String dateToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			String dateString = sdf.format(date);
			return dateString;
		} else
			return null;
	}

	public static String dateToString(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String dateString = sdf.format(date);
			return dateString;
		}
		return null;

	}
}
