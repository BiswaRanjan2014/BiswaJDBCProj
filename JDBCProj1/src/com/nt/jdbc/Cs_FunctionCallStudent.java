//Cs_FunctionCallStudent.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
CREATE OR REPLACE FUNCTION FX_GET_STUDENT_DETAILS_BY_SNO ( NO IN NUMBER , NAME OUT VARCHAR2 ) RETURN FLOAT
AS 
 SAVG FLOAT;
BEGIN
  SELECT SNAME,AVG INTO NAME,SAVG FROM STUDENT WHERE SNO=NO;
 
  RETURN SAVG;
END FX_GET_STUDENT_DETAILS_BY_SNO;
*/

public class Cs_FunctionCallStudent {
	private static final String CALL_FUNCTION = "{?=call FX_GET_STUDENT_DETAILS_BY_SNO(?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");
				CallableStatement cs = con.prepareCall(CALL_FUNCTION);) {

			// Read Inputs::
			int no = 0;
			if (sc != null) {
				System.out.println("ENTER STUDENT NO::");
				no = sc.nextInt();

			} // if

			if (cs != null) {
				// Register Out Param Withn JDBC types(All Out,Return Params Must Be
				// Registered)::
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);

				// Set Values To In Param::
				cs.setInt(2, no);

				// Call PL/SQL Procedure::
				cs.execute();

				// Gather Result From OUT Params::
				System.out.println("STUDENT AVG  ::" + cs.getFloat(1));
				System.out.println("STUDENT NAME ::" + cs.getString(3));

			} // if

		} // try
		catch (SQLException se) {
			// se.printStackTrace();
			if (se.getErrorCode() == 1403)
				System.out.println("EMP NUMBER NOT FOUND");
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
