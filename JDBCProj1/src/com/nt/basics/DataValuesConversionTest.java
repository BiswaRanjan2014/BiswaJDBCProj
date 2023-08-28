package com.nt.basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataValuesConversionTest {

	public static void main(String[] args) throws ParseException {
		
		//Converting String Date Value To java.util.Date Class obj::
		String d1="45-20-1990"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy"); //Holds The Date Pattern
		java.util.Date ud1=sdf.parse(d1); //parse(-) takes gives d1 string date value in dd-MM-yyyy
		                                  //As specified in SimpleDateFormat class obj
		System.out.println("util Date::"+ud1);
		System.out.println();
		
		//Converting java.util.Date class obj to java.sql.Date class obj::
		long ms=ud1.getTime(); //gives ud1 obj date-time value in the form of miliseconds
		                      //as elapsed between jan 1st 1970 00:00 hours to java.util.Date class obj date,time
		java.sql.Date sqd=new java.sql.Date(ms);
		System.out.println("sql Date::"+sqd);
		System.out.println();
		
		//Converting String Date Value of yyyy-MM-dd pattern Directly To java.sql.Date Class obj
		String d2="1990-11-25"; //yyyy-MM-dd
		java.sql.Date sqd1=java.sql.Date.valueOf(d2);
		System.out.println("sql Date::"+sqd1);
		System.out.println();
		
		//converting sql/util date to String date value having our choice
		SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
		String sd2=sdf2.format(sqd1);
		System.out.println("String Date::"+sd2);
		System.out.println();
		
	}

}
