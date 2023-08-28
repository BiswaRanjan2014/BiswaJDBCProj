//DateTimeInsertTest_Oracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest_Oracle {
	private final static String INSERT_CUSTOMER_QUERY = "INSERT INTO CUSTOMER_INFO VALUES(CNO_SEQ.NEXTVAL,?,?,?,?,?)";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc = new Scanner(System.in);) {

			// Read Inputs Values::
			String name = null, sdob = null, stop = null, sorderdt = null;
			float billamt = 0.0f;
			if (sc != null) {
				System.out.println("ENTER CUSTOMER NAME::");
				name = sc.next();
				System.out.println("ENTER CUSTOMER BILL AMOUNT::");
				billamt = sc.nextFloat();
				System.out.println("ENTER DOB(dd-MM-yyyy)::");
				sdob = sc.next();
				System.out.println("ENTER TOP(hh:mm:ss)::");
				stop = sc.next();
				System.out.println("ENTER ORDER_DATE_TIME(dd-MM-yyyy hh:mm:ss)::");
				sc.nextLine();
				sorderdt = sc.nextLine();
			}
			// convert String DOB to java.sql.Date class obj::
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Holds The Date Pattern
			java.util.Date udob = sdf.parse(sdob); // String date to util date
			java.sql.Date sqdob = new java.sql.Date(udob.getTime()); // util date to sql Date

			// convert String Top(Time of Purchase)to java.sql.Time obj::
			java.sql.Time sqtop = java.sql.Time.valueOf(stop);

			// convert String ORDER DATE TIME to java.sql.Timestamp class obj::
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); // Hplds The Date Pattern
			java.util.Date uorderdt = sdf1.parse(sorderdt); // String date to util date
			java.sql.Timestamp sqorderdt = new java.sql.Timestamp(uorderdt.getTime()); // util date to sql Date

			// set values SQL Query param::
			if (ps != null) {
				ps.setString(1, name);
				ps.setFloat(2, billamt);
				ps.setDate(3, sqdob);
				ps.setTime(4, sqtop);
				ps.setTimestamp(5, sqorderdt);

				// Execute the SQL Query::
				int count = ps.executeUpdate();

				if (count == 0)
					System.out.println("RECORD NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
			} // if

		} // try
		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
