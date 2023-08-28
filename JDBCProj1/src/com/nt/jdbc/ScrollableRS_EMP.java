//ScrollableRS_EMP.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRS_EMP {

	public static void main(String[] args) {
		

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT * FROM EMP")) {

			if (rs != null) {
				System.out.println("RECORDS DISPLAY TOP TO BOTTOM::");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));	
				}
				
				System.out.println();
				
				rs.afterLast();

				System.out.println("RECORDS DISPLAY BOTTOM TO TOP::");
				while (rs.previous()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));				
				}
				
				System.out.println();
				
				// Displaying Records Randomly Or Directly::
				System.out.println("DISPLAYING RECORDS RANDOMLY OR DIRECTLY::");
				rs.first();
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
				rs.last();
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
				rs.absolute(8);
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
				rs.absolute(-5);
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
				rs.relative(4);
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
				rs.relative(-5);
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " + rs.getInt(8));
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
