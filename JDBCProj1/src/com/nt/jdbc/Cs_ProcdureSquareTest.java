//Cs_ProcdureSquareTest.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/* 
    create or replace procedure p_square_data(x in number,z out number)
    as
    begin
     z:=x*x;
   end;
 */
public class Cs_ProcdureSquareTest {
	private static final String CALL_PROCEDURE = "{call p_square_data(?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// read inputs
			int val1 = 0;
			if (sc != null) {
				System.out.println("Enter Value you want to Square::");
				val1 = sc.nextInt();

			}

			if (cs != null) {
				// Register Out param withn JDBC types(all out,return params must be Registered)::
				cs.registerOutParameter(2, Types.INTEGER);
				
				// SET VALUeS TO IN PARAM
				cs.setInt(1, val1);

				// Call PL/SQL Procedure::
				cs.execute();
				
				// Gather Result From OUT params::
				int result = cs.getInt(2);
				System.out.println("Square is::" + result);
				
			} // if

		} catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class