//DeleteTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// Read Inputs
			sc = new Scanner(System.in);

			String city = null;
			if (sc != null) {
				System.out.println("ENTER STUDENT ADDRESS::");
				city = sc.next();// Gives hyd
			} // if

			// Convert input values as required for the SQL Query
			city = "'" + city + "'";// gives'hyd'

			// Load JDBC Driver Class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			// Create Statement object
			if (con != null)
				st = con.createStatement();

			// Prepare SQL Query as required for the DB s/w
			// DELETE FROM STUDENT WHERE SADD='hyd';
			String query = " DELETE FROM STUDENT WHERE SADD=" + city;
			System.out.println(query);
			// Send and Execute the SQL Query in DB s/w
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);
			// Process the Result
			if (count == 0)
				System.out.println("No Records Found For Deletion::");
			else
				System.out.println("No.of Records that are Effected::" + count);

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
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // Finally

	}// Main

}// Class
