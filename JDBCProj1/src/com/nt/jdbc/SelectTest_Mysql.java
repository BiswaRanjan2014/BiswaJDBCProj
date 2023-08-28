package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_Mysql {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB", "root","pintu");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(" SELECT * FROM PRODUCT");
				){
			
			//Process the ResultSet obj
			if(rs!=null) {
				boolean rsEmpty=true;
				while(rs.next()) {
					rsEmpty=false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));		
				}//while
				if(rsEmpty)
					System.out.println("No Record Found");
				else
					System.out.println("Record Found And Displayed");
			}//if
			
		} // try
			catch (SQLException se) { // for known Execption
				se.printStackTrace();
			} catch (Exception e) { // for Unknown Execption
				e.printStackTrace();
			}
			
		}// main

}// class
