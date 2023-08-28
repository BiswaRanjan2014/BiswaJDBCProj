//PS_BatchProcessng_GroupTicketReservation.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PS_BatchProcessng_GroupTicketReservation {
	private static final String GROUP_TICKET_RESERVATION = "INSERT INTO JDBC_TRAIN_JOURNEY VALUES(TICKET_ID_SEQ.NEXTVAL,?,?,?,?,?,?)";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				PreparedStatement ps = con.prepareStatement(GROUP_TICKET_RESERVATION);
				Scanner sc = new Scanner(System.in);) {

			int groupSize = 0;
			String srcPlace = null, destnPlace = null;
			int trainNo = 0;
			boolean isItGroupResevation = false;
			String dtoj = null;
			java.sql.Timestamp sqdtoj = null;
			if (sc != null) {
				System.out.println("ENTER GROUP SIZE ::");
				groupSize = sc.nextInt();
				System.out.println("ENTER SOURCE PLACE ::");
				srcPlace = sc.next();
				System.out.println("ENTER DESTINATION PLACE ::");
				destnPlace = sc.next();
				System.out.println("ENTER TRAIN NO ::");
				trainNo = sc.nextInt();
				System.out.println("ENTER DATE AND TIME OF JOURNEY (dd/MM/yyyy hh:mm:ss) ::");
				sc.nextLine();
				dtoj=sc.nextLine();
				System.out.println("IS IT GROUP RESERVATION-? ::");
				isItGroupResevation = sc.nextBoolean();

				// CONVERT STRING DATE AND TIME OF JOURNEY TO java.sql.Timestamp obj ::
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				java.util.Date udtoj = sdf1.parse(dtoj); // String Date To Util Date
				sqdtoj = new java.sql.Timestamp(udtoj.getTime()); // util Date to SQL Timestamp obj
			}

			// READ GROUP OF PASSENGERS INFO AND ADD THEM TO BATCH OF QUERY PARAMS ::
			if (ps != null && sc != null) {
				for (int i = 1; i <= groupSize; ++i) {
					System.out.println("ENTER " + i +" PASSENGER NAME ::");
					String name = sc.next();
					// ADD EACH SET OF QUERY PARAM VALUES (PASSENGER,JOURNY DETAILS) TO BATCH OF
					// BATCH PROCESSSING ::
					ps.setString(1, name);
					ps.setString(2, srcPlace);
					ps.setString(3, destnPlace);
					ps.setBoolean(4, isItGroupResevation);
					ps.setInt(5, trainNo);
					ps.setTimestamp(6, sqdtoj);
					ps.addBatch();
				} // for

				// EXECUTE THE QUERY WITH BATCH QUERY PARAM VALUES ::
				int result[] = ps.executeBatch();

				// PROCESS THE RESULT[] ::
				if (result != null)
					System.out.println(" GROUP TICKET BOOKING IS DONE FOR " + groupSize + " PASSENGER ");
				else
					System.out.println(" GROUP TICKET BOOKING IS NOT DONE ");
				
			} // if

		} // try
		catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
