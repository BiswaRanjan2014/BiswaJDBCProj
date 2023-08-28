//DateRetriveTest_MYSQL.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetriveTest_MYSQL {
	private final static String INSERT_CUSTOMER_QUERY = "SELECT * FROM CUSTOMER_INFO";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_QUERY);
				ResultSet rs = ps.executeQuery();) {

			// Process The ResultSet::
			if (rs != null) {
				while (rs.next()) {
					int cno = rs.getInt(1);
					String cname = rs.getString(2);
					float billAmt = rs.getFloat(3);
					java.sql.Date sqdob = rs.getDate(4);
					java.sql.Time sqtop = rs.getTime(5);
					java.sql.Timestamp sqorderdatetime = rs.getTimestamp(6);

					// convert java.sql.Date class obj to String date values in the required
					// pattern::
					SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy-dd");
					String sdob = sdf.format(sqdob);

					// convert java.sql.Time class obj to String Time values in the Required pattern
					long ms1 = sqtop.getTime(); // Timestamp obj data in the from of Milli_Seconds
					java.util.Date utop = new java.util.Date(ms1); // convert MilliSeconds into java.util.Date class obj
					SimpleDateFormat sdf1 = new SimpleDateFormat("mm:hh:ss");
					String stop = sdf1.format(utop); // convert java.util.Date class obj to String date value

					// convert java.sql.Timestamp class obj to String date,time value in the
					// required pattern
					long ms2 = sqorderdatetime.getTime(); // Timestamp obj data in the from of Milli_Seconds
					java.util.Date uorderdatetime = new java.util.Date(ms2); // convert MilliSeconds into java.util.Date
																				// class obj
					SimpleDateFormat sdf2 = new SimpleDateFormat("mm:hh:ss yyyy-dd-MMM");
					String sorderdatetime = sdf2.format(uorderdatetime); // convert java.util.Date class obj to String
																			// date value
					System.out.println("CNO::" + cno + "CNAME::" + cname + "BILL AMOUNT::" + billAmt + "DOB::" + sdob
							+ "TOP::" + stop + "ORDER DATE TIME::" + sorderdatetime);

				} // while
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
