package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JobSeekers_Details_InsertionTest_Oracle {
	private final static String JOBSEEKER_INSERT_QUERY = "INSERT INTO JOBSEEKER_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?,?,?,?)";

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in);
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
		        PreparedStatement ps = con.prepareStatement(JOBSEEKER_INSERT_QUERY);) {

		      // Read Input Values From EndUser::
		      String name = null, jsdob=null, addrs=null, qualif=null, photoPath = null, resumePath = null;
		      if (sc != null) {
		        System.out.println("ENTER JOBSEEKER NAME::");
		        name = sc.next();
		        System.out.println("ENTER JOBSEEKER DOB(dd/MM/yyyy)::");
		        jsdob = sc.next();
		        System.out.println("ENTER JOBSEEKER ADDRESS::");
		        addrs = sc.next();
		        System.out.println("ENTER JOBSEEKER QUALIFICATION::");
		        qualif = sc.next();
		        System.out.println("ENTER JOBSEEKER PHOTO_PATH::");
		        photoPath = sc.next().trim().replace("?", "");
		        System.out.println("ENTER JOBSEEKER RESUME_PATH::");
		        resumePath = sc.next().trim().replace("?", "");
		      }
		       // convert String DOB to java.sql.Date class obj::
		          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Hplds The Date Pattern
		          java.util.Date udob = sdf.parse(jsdob); // String date to util date
		          java.sql.Date jsqdob = new java.sql.Date(udob.getTime()); // util date to sql Date

		      // Create Streams Pointing to the Files::
		      try (InputStream photo = new FileInputStream(photoPath); 
		          
		    		  Reader resume = new FileReader(resumePath)) {

		        if (ps != null) {
		          // Set Values To Query Params::
		          ps.setString(1, name);
		          ps.setDate(2, jsqdob);
		          ps.setString(3, addrs);
		          
		          ps.setString(4, qualif);
		          ps.setBinaryStream(5, photo);
		          ps.setCharacterStream(6, resume);
		          

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

