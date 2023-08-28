//Assign_4.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Assign_4 {

	public static void main(String[] args) {
	
		
				System.out.println("AVERAGE SALARY OF ALL EMPLOYEES IN EMP TABLE::");

				Connection con = null;
				Statement st = null;
				ResultSet rs = null;

				try {
					// Load JDBC Driver Class (Optional)
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// Establish the connection
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

					// Create Statement object
					if (con != null)
						st = con.createStatement();

					// Prepare SQL Query
					String query = "SELECT AVG(SAL) FROM EMP";
					System.out.println(query);

					// Send And Execute the SQL Query in DB s/w
					if (st != null)
						rs = st.executeQuery(query);
		            
					// Process the ResultSet
					if (rs != null) {
						if(rs.next());
						// System.out.println("Records count::"+rs.getInt(1));
						   System.out.println(rs.getFloat("AVG(SAL)") );
					}//if
					
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

				} // Finally

			}// Main

		}// Class
