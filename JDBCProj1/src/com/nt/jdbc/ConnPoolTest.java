//ConnPoolTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
	private static final String GET_STUDENTS_QUERY = "SELECT * FROM STUDENT";

	public static void main(String[] args) {

		OracleConnectionPoolDataSource ds = null;
		try {
			/// Create DataSource Object ::
			ds = new OracleConnectionPoolDataSource();
			// Sets JDBC Properties To It ::
			ds.setDriverType("thin");
			ds.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUser("system");
			ds.setPassword("tiger");
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Get Pooled JDBC con Object ::
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_STUDENTS_QUERY);
				ResultSet rs = ps.executeQuery()) {

			// Process The ResultSet Obj ::
			if (rs != null) {
				while (rs.next()) {
					System.out.println(
							rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
				} // while
			} // if

		} // try

		catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
