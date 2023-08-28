//BatchProcessingTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				Statement st = con.createStatement();) {

			if (st != null) {
				// Add Queries To The Batch(Only Non-Select SQL Query)::
				st.addBatch("INSERT INTO STUDENT VALUES(122,'mana','hyd',70.11)");
				st.addBatch("UPDATE STUDENT SET AVG=AVG-10 WHERE SNO>=10 AND SNO<=200");
				st.addBatch("DELETE FROM STUDENT WHERE SADD='hyd'");

				// Send And Execute Batch Of SQL Queries In DB s/w::
				int result[] = st.executeBatch();

				// process The Results::
				int sum = 0;
				for (int i = 0; i < result.length; ++i)
					sum = sum + result[i];

				System.out.println("NO.OF RECORDS THAT ARE EFFECTED::" + sum);
			} // if
		} // try

		catch (SQLException se) {
			se.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
