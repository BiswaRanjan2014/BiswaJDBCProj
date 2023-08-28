//PsUpdatableRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsUpdatableRSTest {

	public static void main(String[] args) {
		
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM STUDENT",
						                                             ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
			
			if (rs != null) {
				System.out.println("SELECT OPERATION::");
				System.out.println();
				while (rs.next()) {
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
						
				} //while
				
				/*
				//Insert Operation::
				rs.moveToInsertRow();
				rs.updateInt(1, 118);
				rs.updateString(2, "subu");
				rs.updateString(3, "delhi");
				rs.updateFloat(4, 73);
				rs.insertRow();
				System.out.println("RECORD INSERTED::");
				*/
				
				/*
				//Update Operation::
				rs.absolute(4);
				rs.updateString(3, "delhi");
				rs.updateRow();
				System.out.println("RECORD UPDATED::");
				*/
				
				/*
				//Delete Operation::
				rs.absolute(9);
				rs.deleteRow();
				System.out.println("RECORD DELETED::");
				*/
				
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


	