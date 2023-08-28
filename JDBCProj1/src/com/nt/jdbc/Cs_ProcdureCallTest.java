//Cs_ProcdureCallTest.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*create or replace procedure p_sum_data(x in number,y in number,z out number)
as
begin
 z:=x+y;
end;
/
*/
public class Cs_ProcdureCallTest {
	private static final String CALL_PROCEDURE = "{call p_sum_data(?,?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// read inputs
			int val1 = 0, val2 = 0;
			if (sc != null) {
				System.out.println("ENTER VALUE-1::");
				val1 = sc.nextInt();
				System.out.println("ENTER VALUE-2::");
				val2 = sc.nextInt();
			}

			if (cs != null) {
				// Register Out param withn JDBC types(all out,return params must be
				// Registered)::
				cs.registerOutParameter(3, Types.INTEGER);

				// SET VALUES TO IN PARAM::
				cs.setInt(1, val1);
				cs.setInt(2, val2);

				// Call PL/SQL Procedure::
				cs.execute();

				// Gather Result From OUT params::
				int result = cs.getInt(3);
				System.out.println("RESULT::" + result);

			} // if

		} catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class