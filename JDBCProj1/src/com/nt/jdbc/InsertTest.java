//InsertTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// Read Inputs
			sc = new Scanner(System.in);
			int no = 0;
			String name = null, addrs = null;
			float avg = 0.0F;
			if (sc != null) {
				System.out.println("ENTER STUDENT NUMBER::");
				no = sc.nextInt();// gives 114
				System.out.println("ENTER STUDENT NAME::");
				name = sc.next();// gives kaka
				System.out.println("ENTER STUDENT ADDRESS::");
				addrs = sc.next();// gives nyk
				System.out.println("ENTER STUDENT AVERAGE::");
				avg = sc.nextFloat();// gives 94.83
			} // if

			// Convert input values as required for the SQL Query
			name = "'" + name + "'";// gives'kaka'
			addrs = "'" + addrs + "'";// gives'nyk'

			// Load JDBC Driver Class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			// Create Statement object
			if (con != null)
				st = con.createStatement();

			// Prepare SQL Query as required for the DB s/w
			// INSERT INTO STUDENT VALUES(114,'kaka','nyk',93.83);
			String query = "INSERT INTO STUDENT VALUES(" + no + "," + name + "," + addrs + "," + avg + ")";
			System.out.println(query);

			// Send and Execute the SQL Query in DB s/w
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);

			// Process the Result
			if (count == 0)
				System.out.println("Record Not Inserted");
			else
				System.out.println("Record Inserted");

		} // try

		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		} finally {
			// Close JDBC objs
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // Finally

	}// Main

}// Class
