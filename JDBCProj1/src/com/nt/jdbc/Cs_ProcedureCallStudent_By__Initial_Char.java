package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//Cs_ProcedureCallStudent_By__Initial_Char.java
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*
CREATE OR REPLACE PROCEDURE P_GET_STUDENT_DETAILS_BY_CHARS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
   OPEN DETAILS FOR
     SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SNAME LIKE NAMECHARS;
END P_GET_STUDENT_DETAILS_BY_CHARS;
*/
public class Cs_ProcedureCallStudent_By__Initial_Char {
	private static final String CALL_PROCEDURE = "{call P_GET_STUDENT_DETAILS_BY_CHARS(?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// Read Inputs::
			String initChars = null;
			if (sc != null) {
				System.out.println("ENTER STUDENT NAME INITIAL CHARS::");
				initChars = sc.next();

			} //if

			if (cs != null) {
				// Register Out Param Withn JDBC Types::
				cs.registerOutParameter(2, OracleTypes.CURSOR);

				// Set Values To In Param::
				cs.setString(1, initChars + "%"); // %indicates any Chars

				// Call PL/SQL Procedure::
				cs.execute();

				// Gather ResultSet From OUT Params::
				try (ResultSet rs = (ResultSet) cs.getObject(2)) {
					if (rs != null) {
						boolean isRSEmpty = false;
						while (rs.next()) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
									+ rs.getFloat(4));
							isRSEmpty = true;
						} // while

						if (!isRSEmpty)
							System.out.println("RECORD ARE NOT FOUND");
						else
							System.out.println();
						System.out.println("RECORD ARE FOUND AND DISPLAY");
					} // if
				} // try-2
			} // if
		} // try-1

		catch (SQLException se) {
			// se.printStackTrace();
			if (se.getErrorCode() == 1403)
				System.out.println("STUDENT NUMBER NOT FOUND");
			else if (se.getErrorCode() == 1017)
				System.out.println("INVALID CREDENTIALS");
			else
				System.out.println("SOME DB PROBLEM");

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
