//LobsInsertionTest_MySQL.java
package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LobsInsertionTest_MySQL {
	private final static String ACTOR_INSERT_QUERY = "INSERT INTO ACTOR_INFO(ANAME,PHOTO,PROFILE) VALUES(?,?,?)";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				PreparedStatement ps = con.prepareStatement(ACTOR_INSERT_QUERY);) {

			// Read Input Values From EndUser::
			String name = null, photoPath = null, profilePath = null;
			if (sc != null) {
				System.out.println("ENTER ACTOR NAME::");
				name = sc.next();
				System.out.println("ENTER ACTOR PHOTO_PATH::");
				photoPath = sc.next().trim().replace("?", "");
				System.out.println("ENTER ACTOR PROFILE_PATH::");
				profilePath = sc.next().trim().replace("?", "");
			}

			// Create Streams Pointing to the Files::
			try (InputStream is = new FileInputStream(photoPath); Reader reader = new FileReader(profilePath)) {

				if (ps != null) {
					// Set Values To Query Params::
					ps.setString(1, name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);

					// Execute The Query::
					int count = ps.executeUpdate();

					// Process the Result::
					if (count == 0)
						System.out.println("RECORD NOT INSERSTED");
					else
						System.out.println("RECORD INSERTED");

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
