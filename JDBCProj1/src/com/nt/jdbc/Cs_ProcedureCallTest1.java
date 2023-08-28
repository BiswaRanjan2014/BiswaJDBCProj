//Cs_ProcedureCallTest1.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_EMPNO 
(
		  NO IN NUMBER 
		, NAME OUT VARCHAR2 
		, SALARY OUT FLOAT 
		) AS 
		BEGIN
		  SELECT ENAME,SAL INTO NAME,SALARY FROM EMP WHERE EMPNO=NO;
		END P_GET_EMP_DETAILS_BY_EMPNO;
		*/

public class Cs_ProcedureCallTest1 {
	private static final String CALL_PROCEDURE = "{call P_GET_EMP_DETAILS_BY_EMPNO(?,?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// Read Inputs::
			int no = 0;
			if (sc != null) {
				System.out.println("ENTER EMPLOYEE NO::");
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
				System.out.println("EMP NAME   ::" + cs.getString(2));
				System.out.println("EMP SALARY ::" + cs.getFloat(3));

			} // if
		} // try
		catch (SQLException se) {
			//se.printStackTrace();
			if (se.getErrorCode()==1403)
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
