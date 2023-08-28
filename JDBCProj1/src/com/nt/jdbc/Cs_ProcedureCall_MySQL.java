//Cs_ProcedureCall_MySQL.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GET_PRODUCT_DETAILS_BY_PRICE_RANGE`(IN startPrice FLOAT,IN endPrice FLOAT)
BEGIN
 SELECT * FROM PRODUCT WHERE PRICE>=startPrice and PRICE<=endPrice;
END
*/
public class Cs_ProcedureCall_MySQL {
	private static final String CALL_PROCEDURE = "{call P_GET_PRODUCT_DETAILS_BY_PRICE_RANGE(?,?)}";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {

			// Read Inputs::
			float startPrice = 0.0f, endPrice = 0.0f;
			if (sc != null) {
				System.out.println("ENTER PRODUCT START PRICE ::");
				startPrice = sc.nextFloat();
				System.out.println("ENTER PRODUCT END PRICE   ::");
				endPrice = sc.nextFloat();

			} // if

			if (cs != null) {
				// Set Values To IN Param::
				cs.setFloat(1, startPrice);
				cs.setFloat(2, endPrice);

				// Call PL/SQL Procedure::
				cs.execute();

				// Gather Results From OUT Param::
				try (ResultSet rs = cs.getResultSet()) {

					// Display Records::
					while (rs.next()) {
						System.out.println(
								rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getFloat(4));
					} // while
				} // try-2

			} // if

		} // try-1
		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
