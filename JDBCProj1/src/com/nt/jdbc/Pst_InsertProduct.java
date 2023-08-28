package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Pst_InsertProduct {
	private final static String PRODUCT_INSERT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				// Establish the Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");
				// Create PreparedStatement having pre-complied SQL Query::
				PreparedStatement ps = con.prepareStatement(PRODUCT_INSERT_QUERY);) {

			int count = 0;
			if (sc != null) {
				System.out.println("ENTER PRODUCT'S COUNT::");
				count = sc.nextInt();
			}

			if (sc != null && ps != null) {
				for (int i = 1; i <= count; ++i) {
					// Read Each Product's Details::
					System.out.println("ENTER" + i + "PRODUCT'S DETAILS::");
					System.out.println("ENTER PRODUCT'S IDNUMBER::");
					int pidno = sc.nextInt();

					System.out.println("ENTER PRODUCT'S NAME::");
					String pname = sc.next();

					System.out.println("ENTER PRODUCT'S PRICE::");
					float price = sc.nextFloat();

					System.out.println("ENTER PRODUCT'S QTY::");
					float qty = sc.nextFloat();

					// Set Each Product's Details to pre-compiled SQL Query as query param values(?)
					ps.setInt(1, pidno);
					ps.setString(2, pname);
					ps.setFloat(3, price);
					ps.setFloat(4, qty);

					// Execute the Query::
					int result = ps.executeUpdate();

					// Process the Result::
					if (result == 0)
						System.out.println(i+ "PRODUCT RECORD IS NOT INSERTED");
					else
						System.out.println(i+ "PRODUCT RECORD IS INSERTED");
				} // for
			} // if
		} // try

		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main

}// class
