package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromOracleToMySQLTest {
	private final static String MYSQL_INSERT_PRODUCT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private final static String ORACLE_SELECT_QUERY = "SELECT * FROM PRODUCT";

	public static void main(String[] args) {

		try (Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",	"tiger");
				Connection mysqlCon = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				Statement st = oraCon.createStatement();
				PreparedStatement ps = mysqlCon.prepareStatement(MYSQL_INSERT_PRODUCT_QUERY);) {

			// Execute the SELECT SQL Query in Oracle DB s/w
			try (ResultSet rs = st.executeQuery(ORACLE_SELECT_QUERY)) {

				// Process the ResultSet and also insert records to MySQL DB s/w
				int count = 0;
				if (rs != null && ps != null) {
					while (rs.next()) {
						// Get Each Record From Oracle DB Table
						int pid      = rs.getInt(1);
						String pname = rs.getString(2);
						float price  = rs.getFloat(3);
						float qty    = rs.getFloat(4);

						// Set The Above Values INSERT SQL Query Param values To Insert The Record To
						// MySQL DB Table
						ps.setInt    (1, pid);
						ps.setString (2, pname);
						ps.setFloat  (3, price);
						ps.setFloat  (4, qty);

						// Execute The Query::
						int result = ps.executeUpdate();
						// Process The Result::
						if (result == 0)
							System.out.println("Product Record Is Not Inserted");
						else
							System.out.println("Product Record Is Inserted");
						count++;
					} // while
					
					System.out.println(count + "::NO. OF RECORDS ARE COPIED FROM ORACLE DB TABLE TO MYSQL DB TABLE ");
				} // if

			} // try2

		} // try1
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main

}// class
