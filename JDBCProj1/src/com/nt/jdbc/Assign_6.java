//Assign_6.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assign_6 {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// Read Inputs
			sc = new Scanner(System.in);

			String Job = null;
			if (sc != null) {
				System.out.println("ENTER EXISTING JOB NAME::");
				Job = sc.next().toUpperCase();// gives existing job name 'CLERK'

			} // if

			// Convert input values as required for the SQL Query
			Job = "'" + Job + "'";// gives 'CLERK'

			// Load JDBC Driver Class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			// Create Statement object
			if (con != null)
				st = con.createStatement();

			// Prepare SQL Query as required for the DB s/w
			// UPDATE EMP SET SAL = SAL+(SAL * 10/100) WHERE JOB = 'CLERK';
			String query = "UPDATE EMP SET SAL = SAL+(SAL * 10/100) WHERE JOB =" + Job;
			System.out.println(query);

			// Send and Execute the SQL Query in DB s/w
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);
			// Process the Result
			if (count == 0)
				System.out.println("No Record(s) Found For Updatation");
			else
				System.out.println("No.Of Records That Are Effected::" + count);

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
