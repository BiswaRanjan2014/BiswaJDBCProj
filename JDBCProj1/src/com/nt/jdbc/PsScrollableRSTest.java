//PsScrollableRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsScrollableRSTest {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT",
						                                             ResultSet.TYPE_SCROLL_SENSITIVE,
						                                             ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = ps.executeQuery()) {
			
			
			if (rs != null) {
				System.out.println("RECORDS DISPLAY TOP TO BOTTOM::");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));	
				}
				
				System.out.println();
				
				rs.afterLast();

				System.out.println("RECORDS DISPLAY BOTTOM TO TOP::");
				while (rs.previous()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));				
				}
				
				System.out.println();
				
				// Displaying Records Randomly Or Directly::
				System.out.println("DISPLAYING RECORDS RANDOMLY OR DIRECTLY::");
				rs.first();
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
				rs.last();
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
				rs.absolute(3);
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
				rs.absolute(-5);
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
				rs.relative(3);
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
				rs.relative(-2);
				System.out.println(rs.getRow() + "------->"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4));
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
