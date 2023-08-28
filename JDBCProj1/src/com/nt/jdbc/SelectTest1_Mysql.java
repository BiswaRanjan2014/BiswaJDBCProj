package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest1_Mysql {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB", "root", "pintu");
				Statement st = con.createStatement();) {

			// Read Inputs
			float start = 0.0F, end = 0.0F;
			if (sc != null) {
				System.out.println("Enter start price Range::");
				start = sc.nextFloat();// Gives 500
				System.out.println("Enter End price Range");
				end = sc.nextFloat();// Gives 1000
			}

			// Prepare SQL Query
			// SELECT * FROM PRODUCT WHERE PRICE>=500 AND PRICE<=1000;
			String query="SELECT * FROM PRODUCT WHERE PRICE>="+start+" AND PRICE<="+end;
			System.out.println(query);
			try (ResultSet rs = st.executeQuery(query);) {// Nested try with Resource
				// Process the ResultSet obj
				if(rs!= null) {
					boolean rsEmpty = true;
					while(rs.next()) {
						rsEmpty = false;
						System.out.println(rs.getInt(1)+ " " +rs.getString(2)+ " " +rs.getFloat(3)+ " " +rs.getFloat(4));
					}// while
					if(rsEmpty)
						System.out.println("No Record Found");
					else
						System.out.println("Record Found And Displayed");
				}// if

			}// try-2
			
		}// try-1
		
		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		}

	}// main

}// class
