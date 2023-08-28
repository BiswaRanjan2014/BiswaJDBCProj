//LoginApp.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",	"tiger");
				Statement st = con.createStatement();
				){

			// Read Inputs
			String user = null,pwd = null;
			if (sc != null) {
				System.out.println("ENTER USERNAME::");
				user = sc.nextLine();// Gives RICKY
				System.out.println("ENTER PASSWORD::");
				pwd = sc.nextLine();// Gives WICKY
			}
			// Convert input values as required for the SQL Query
			user = "'" + user + "'";// gives'RICKY'
			pwd  = "'"+ pwd  + "'";// gives'WICKY'

			// Prepare SQL Query::
			// SELECT COUNT(*) FROM USER_INFO WHERE UNAME='RICKY' AND PWD='WICKY';
			String query = "SELECT COUNT(*) FROM USER_INFO WHERE UNAME="+user+"AND PWD="+pwd;
			System.out.println(query);

			// Send And Execute The SQL Query in DB s/w
			try (ResultSet rs = st.executeQuery(query)) {// Nested try with resource

				// Process the ResultSet obj
				if (rs != null) {
					rs.next();
					int count=rs.getInt(1);
					if (count==0)
						System.out.println("Invalid Credentials");
					else
						System.out.println("valid Credentials");
				} // if

			} // try-2

		} // try-1

		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		}

	}// main

}// class
