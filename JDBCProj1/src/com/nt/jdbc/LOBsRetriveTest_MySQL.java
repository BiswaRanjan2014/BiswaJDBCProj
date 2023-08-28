//LOBsRetriveTest_MySQL.java
package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class LOBsRetriveTest_MySQL {
	private final static String GET_ACTOR_INFO_BY_ID = "SELECT * FROM ACTOR_INFO WHERE AID=?";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				PreparedStatement ps = con.prepareStatement(GET_ACTOR_INFO_BY_ID);) {

			int aid = 0;
			if (sc != null) {
				System.out.println("ENTER ACTOR ID::");
				aid = sc.nextInt();
			}
			// Set Value To Query Param::
			if (ps != null) {
				ps.setInt(1, aid);
			}
			// Execute The Query::
			try (ResultSet rs = ps.executeQuery()) {

				if (rs != null) {
					if (rs.next()) {
						int id = rs.getInt(1);
						String name = rs.getString(2);
						try (// Read LOBs From ResulSet As Streams::
								InputStream is = rs.getBinaryStream(3);
								Reader reader = rs.getCharacterStream(4);
								// Create Empty Destination Files Using Streams::
								OutputStream os = new FileOutputStream("retrive_photo.jpg");
								Writer writer = new FileWriter("retrive_profile.txt");) {
							// Copy LOBs Collected From DB Table To Destination Files::
							IOUtils.copy(is, os);
							IOUtils.copy(reader, writer);
							System.out.println("ACTOR_INFO::" + id + " " + name + " LOBs ARE RETRIVED");

						} // try3
					} // if
					else {
						System.out.println("ACTOR NOT FOUND");
					}
				} // if
			} // try2
		} // try1
		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
