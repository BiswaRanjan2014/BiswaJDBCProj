//SelectNonSelectTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				Statement st = con.createStatement();
				Scanner sc = new Scanner(System.in); ){

			String query = null;
			if (sc != null) {
				System.out.println("ENTER SQL QUERY::");
				query = sc.nextLine();
			} // if

			// Execute The Query::
			if (st != null) {
				boolean flag = st.execute(query);
				if (flag) {
					System.out.println("SELECT SQL QUERY IS EXECUTED");

					try (ResultSet rs = st.getResultSet()) {
						if (rs != null) {
							while (rs.next()) {
								System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "+ rs.getFloat(4));
							} // while

						} // if-3

					} // try-2

				} // if-2

				else {
					System.out.println("NON-SELECT SQL QUERY IS EXECUTED");
					int count = st.getUpdateCount();
					System.out.println("NO.OF RECORDS THAT ARE EFFECTED::" + count);
				} // else

			} // if-1

		} // try-1
		
		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
