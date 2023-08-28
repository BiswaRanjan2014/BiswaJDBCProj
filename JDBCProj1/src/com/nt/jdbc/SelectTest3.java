//SelectTest3
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// Read Inputs
			sc = new Scanner(System.in);
			int deptno = 0;
			if (sc != null) {
				System.out.println("ENTER DEPT NUMBER::");
				deptno = sc.nextInt();
			}
			// Load JDBC Driver Class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			// Create Statement object
			if (con != null)
				st = con.createStatement();

			// Prepare SQL Query
			// SELECT * FROM DEPTNO WHERE DEPTNO=10;
			String query = "SELECT * FROM DEPT WHERE DEPTNO=" + deptno;
			System.out.println(query);

			// Send And Execute the SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(query);

			// Process the ResultSet
			if (rs != null)
				if (rs.next())
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				else
					System.out.println("Record Not Found");
		} // try

		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) { // for known Execption
			cnf.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		} finally {
			// Close JDBC objs
			try {
				if (rs != null)
					rs.close();
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
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // Finally

	}// Main

}// Class
