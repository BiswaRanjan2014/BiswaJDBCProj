//PersonAgeCalculater_Oracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculater_Oracle {
	private static final String AGE_CALCULATOR_QUERY = "SELECT(SYSDATE-DOB)/365.25 FROM CUSTOMER_INFO WHERE CNO=?";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				PreparedStatement ps = con.prepareStatement(AGE_CALCULATOR_QUERY);
				Scanner sc=new Scanner(System.in); ){

			// Read inputs values::
			int no = 0;
			if (sc!= null) {
				System.out.println("ENTER CUSTOMER ID::");
				no = sc.nextInt();
			}
			
			//Set values to Query params
			if(ps!=null) {
				ps.setInt(1, no);
			}

			// Sets values to Query and get the ResultSet obj
			try (ResultSet rs = ps.executeQuery()) {
				if (rs != null) {
					if (rs.next()) {
						System.out.println("CUSTOMER AGE::" +rs.getFloat(1));
					} 
					else
						System.out.println("CUSTOMER NOT FOUND");
				} // if
			} // try2
		} // try1

		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
