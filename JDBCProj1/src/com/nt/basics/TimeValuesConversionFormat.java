//TimeValuesConversionFormat.java
package com.nt.basics;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeValuesConversionFormat {

	public static void main(String[] args) throws Exception {
		//converting String time value to java.sql.Time obj
		String t1="10:25:45"; //hh.mm.ss
		java.sql.Time sqtime=java.sql.Time.valueOf(t1);
		System.out.println("sql Time::"+sqtime);

		//converting String date,time value to java.sql.Timestamp obj
		String dt1="20-10-1990 10:25:45"; //dd-MM-yyyy hh:mm:ss
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date udt1=sdf.parse(dt1);
		//long ms=udt1.getTime(;)
		java.sql.Timestamp sqdt1=new Timestamp(udt1.getTime());
		System.out.println("sql Timestamp::"+sqdt1);
		
		//if String date,time values is in yyyy-MM-dd hh:mm:ss pattern then
		//it can be converted directly to java.sql.Timestamp obj without converting into java.util.Date class obj 
		String dt2="1990-10-23 10:25:45"; 
		java.sql.Timestamp sqdt2=java.sql.Timestamp.valueOf(dt2);
		                                  //static method
		System.out.println("Timestamp::"+sqdt2);      
		
		//converting java.sql.Time object obj data as the String value
		long ms=sqdt1.getTime(); //Timestamp obj data in the from of Milli_Seconds
        java.util.Date ud1=new java.util.Date(ms); //convert MilliSeconds into java.util.Date class obj
        SimpleDateFormat sdf1=new SimpleDateFormat("hh:mm:ss MMM/yyyy/dd");
        String sd1=sdf1.format(ud1); //convert java.util.Date class obj to String date value
        System.out.println("String date value::"+sd1);
		
        
	}//main

}//class
