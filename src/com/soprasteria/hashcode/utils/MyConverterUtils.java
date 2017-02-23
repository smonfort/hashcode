package com.soprasteria.hashcode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConverterUtils {

	public static Date stringToDate(String dateAsString, String format) {
		Date result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		 
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Date stringToDate(String dateAsString) {
		return stringToDate(dateAsString, "dd/MM/yyyy");
	}
	
	public static Integer stringToInteger(String integerAsString) {
		Integer result = null;
		
		try {
			result = Integer.valueOf(integerAsString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(stringToDate("25/12/1986"));
		System.out.println(stringToDate("25/13/1986"));
		System.out.println(stringToInteger("25"));
		System.out.println(stringToInteger("25s"));
	}
}
