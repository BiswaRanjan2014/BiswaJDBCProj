//Cs_ProcedureCallStudent.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
create or replace NONEDITIONABLE PROCEDURE P_GET_STUDENT_DETAILS_BY_SNO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR
, AVG OUT FLOAT 
) AS 
BEGIN
  SELECT SNAME,AVG INTO NAME,AVG FROM STUDENT WHERE SNO=NO;
END P_GET_STUDENT_DETAILS_BY_SNO;
*/
public class Cs_ProcedureCallStudent {
	private static final String CALL_PROCEDURE = "{call P_GET_STUDENT_DETAILS_BY_SNO(?,?,?)}";

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// Read Inputs::
			int no = 0;
			if (sc != null) {
				System.out.println("ENTER STUDENT NO::");
				no = sc.nextInt();

			} //if

			if (cs != null) {
				// Register Out Param Withn JDBC types(All Out,Return Params Must Be Registered)::
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);

				// Set Values To In Param::
				cs.setInt(1, no);

				// Call PL/SQL Procedure::
				cs.execute();

				// Gather Result From OUT Params::
				System.out.println("STUDENT NAME ::" + cs.getString(2));
				System.out.println("STUDENT AVG  ::" + cs.getFloat(3));

			} // if
		} // try
		catch (SQLException se) {
			//se.printStackTrace();
			if (se.getErrorCode()==1403)
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

