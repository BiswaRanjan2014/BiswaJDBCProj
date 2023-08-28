//PsSensitiveRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSensitiveRSTest {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM STUDENT",
						                                             ResultSet.TYPE_SCROLL_SENSITIVE,
						                                             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
			
			if (rs != null) {
				System.out.println("RECORDS DISPLAY TOP TO BOTTOM::");
				int count=0;
				while (rs.next()) {
					
					if(count==0) {
						System.out.println("IN NEXT [20SEC] MODIFY THE RECORDS STUDENT DB TABLE::");
						Thread.sleep(20000);
					}
					
					rs.refreshRow();
					count++;
					
					 System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
					
				} //while
				
			} //if
			
		} //try

		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
