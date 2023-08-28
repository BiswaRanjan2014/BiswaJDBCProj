package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp {
	private static final String AUTH_QUERY = " SELECT COUNT(*) FROM USER_INFO WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");
				PreparedStatement ps = con.prepareStatement(AUTH_QUERY);) {

			// Read Inputs
			String user = null, pwd = null;
			if (sc != null) {
				System.out.println("ENTER USERNAME::");
				user = sc.nextLine();// Gives RICKY
				System.out.println("ENTER PASSWORD::");
				pwd = sc.nextLine();// Gives WICKY
			}

			// Set Values To Query Params
			if (ps != null) {
				ps.setString(1, user);
				ps.setString(2, pwd);
			}

			// Send And Execute The SQL Query In DB s/w
			try (ResultSet rs = ps.executeQuery()) {// Nested try with resource

				// Process the ResultSet obj
				if (rs != null) {
					rs.next();
					int count = rs.getInt(1);
					if (count == 0)
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
