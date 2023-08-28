//JobSeekerDetails_RetriveTest_MySQL.java
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
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class JobSeekerDetails_RetriveTest_MySQL {
	private final static String GET_ACTOR_INFO_BY_ID ="SELECT * FROM JOBSEEKER_INFO WHERE JS_ID=?";

	
	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in);
		          Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1","root","pintu");
		          PreparedStatement ps = con.prepareStatement(GET_ACTOR_INFO_BY_ID);) {

		        int aid = 0;
		        if (sc != null) {
		          System.out.println("ENTER JOB SEEKERS ID::");
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
		              java.sql.Date sqdob = rs.getDate(3);
		              SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy-dd");
		              String sdob = sdf.format(sqdob);
		              String addrs=rs.getString(4);
		              String qualif=rs.getString(5);
		              
		            try (// Read LOBs From ResulSet As Streams::
		                  InputStream is = rs.getBinaryStream(6);
		                  Reader reader = rs.getCharacterStream(7);
		            		
		                  // Create Empty Destination Files Using Streams::
		                  OutputStream os = new FileOutputStream("Retrive_Photo.jpg");
		                  Writer writer = new FileWriter("Retrive_Resume.txt");) {
		            	
		                // Copy LOBs Collected From DB Table To Destination Files::
		                IOUtils.copy(is,os);
		                IOUtils.copy(reader,writer);
		                System.out.println(" JOB SEEKER INFO         :: "  + id   + 
		                                   " \n JOB SEEKER NAME         :: "  + name + 
		                                   " \n JOB SEEKER DOB          :: "  + sdob +
		                                   " \n JOB SEEKER ADDRESS      :: "  + addrs +
		                                   " \n JOB SEEKER QALIFICATION :: "  + qualif );
		                System.out.println("\n JOB SEEKERS ARE RETRIVED");

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

	