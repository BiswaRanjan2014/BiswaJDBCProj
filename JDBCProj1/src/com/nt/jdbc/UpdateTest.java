//UpdateTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// Read Inputs
			sc = new Scanner(System.in);

			String newCity = null;
			Float newAvg = 0.0F;
			int no = 0;
			if (sc != null) {
				System.out.println("ENTER EXISTING STUDENT NUMBER::");
				no = sc.nextInt();// gives 111
				System.out.println("ENTER NEW CITY OF STUDENT::");
				newCity = sc.next();// gives delhi
				System.out.println("ENTER NEW AVG OF STUDENT::");
				newAvg = sc.nextFloat();// gives 94.83
			} // if

			// Convert input values as required for the SQL Query
			newCity = "'" + newCity + "'";// gives'delhi'

			// Load JDBC Driver Class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			// Create Statement object
			if (con != null)
				st = con.createStatement();

			// Prepare SQL Query as required for the DB s/w
			// UPDATE STUDENT SET SADD='hyd',AVG=67.99 WHERE SNO =113;
			String query = "UPDATE STUDENT SET SADD=" + newCity + ",AVG=" + newAvg + "WHERE SNO=" + no;
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
