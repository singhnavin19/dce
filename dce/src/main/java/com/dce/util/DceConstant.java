package com.dce.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class DceConstant {

	public static final String ENQUIRY_TAB = "enquiry";

	public static Map<String, String> PROFESSION_TYPE = new HashMap<String, String>();
	static {
		PROFESSION_TYPE.put("1", "Student");
		PROFESSION_TYPE.put("2", "Job");
		PROFESSION_TYPE.put("3", "Buisness");
		PROFESSION_TYPE.put("4", "Other");

	}

	public static Map<String, String> getPROFESSION_TYPE() {
		return PROFESSION_TYPE;
	}

	public static void setPROFESSION_TYPE(Map<String, String> pROFESSION_TYPE) {
		PROFESSION_TYPE = pROFESSION_TYPE;
	}

	public static void main(String[] args) {

		for (Entry<String, String> professionType : PROFESSION_TYPE.entrySet()) {
			System.out.println(professionType);
			System.out.println(professionType);

		}

	}
}
