//InsertTestUsingTWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestUsingTWR {

	public static void main(String[] args) {

		// try with resource
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");
				Statement st = con.createStatement();) {
			int no = 0;
			String name = null, addrs = null;
			float avg = 0.0F;
			if (sc != null) {
				System.out.println("ENTER STUDENT NUMBER::");
				no = sc.nextInt();// gives 116
				System.out.println("ENTER STUDENT NAME::");
				name = sc.next();// gives lala
				System.out.println("ENTER STUDENT ADDRESS::");
				addrs = sc.next();// gives njersy
				System.out.println("ENTER STUDENT AVERAGE::");
				avg = sc.nextFloat();// gives 94.47
			}

			// Convert input values as required for the SQL Query
			name = "'" + name + "'";// gives'lala'
			addrs = "'" + addrs + "'";// gives'njersy'

			// Prepare SQL Query as required for the DB s/w
			// INSERT INTO STUDENT VALUES(116,'lala','njersy',94.47);
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

		} // try::All Stream objs/JDBC objs Creation in try with resource will be Closed
			// Automatically
		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		}

	}// main

}// class
