package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PstInsertTest {
	private final static String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				// Establish the Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");
				// Create PreparedStatement having pre-complied SQL Query
				PreparedStatement ps = con.prepareStatement(STUDENT_INSERT_QUERY);) {

			int count = 0;
			if (sc != null) {
				System.out.println("ENTER STUDENT'S COUNT::");
				count = sc.nextInt();
			}

			if (sc != null && ps != null) {
				for (int i = 1; i <= count; ++i) {
					// Read Each Students Details
					System.out.println("ENTER" + i + "STUDENT'S DETAILS::");
					System.out.println("ENTER STUDENT'S NUMBER::");
					int no = sc.nextInt();

					System.out.println("ENTER STUDENT'S NAME::");
					String name = sc.next();

					System.out.println("ENTER STUDENT'S ADDRESS::");
					String addrs = sc.next();

					System.out.println("ENTER STUDENT'S AVG::");
					float avg = sc.nextFloat();

					// Set Each Student's Details to pre-compiled SQL Query as query param values(?)
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);

					// Execute the Query
					int result = ps.executeUpdate();

					// Process the Result
					if (result == 0)
						System.out.println(i + "Student Record Is Not Inserted");
					else
						System.out.println(i + "Student Record Is Inserted");
				} // for
			} // if
		} // try

		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main

}// class
