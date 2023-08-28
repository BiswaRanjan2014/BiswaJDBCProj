package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSUpdateStudentAvg {
	private static final String STUDENT_UPDATE_QUERY="UPDATE STUDENT SET AVG=AVG+(AVG*0.1) WHERE SADD IN (?,?) AND AVG<?";
	
	public static void main(String[] args) {
			try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",	"tiger");
				PreparedStatement ps = con.prepareStatement(STUDENT_UPDATE_QUERY);
				){
				
				// Read Inputs
				String sadd1 = null,sadd2 = null;
				Float newAvg = 0.0F;
				if (sc != null) {
					
				    System.out.println("ENTER EXISTING STUDENT SADD-1::");
					sadd1 = sc.next();// Gives hyd
					System.out.println("ENTER EXISTING STUDENT SADD-2::");
					sadd2 = sc.next();// Gives rio
					System.out.println("ENTER AVG<::");
					newAvg = sc.nextFloat();// Gives rio
                 }
				
				//Set Values To Query Params
				if(ps!=null) {
					ps.setString(1, sadd1);
					ps.setString(2, sadd2);
					ps.setFloat(3, newAvg);
				}
				
				//Send And Execute The SQL Query In DB s/w
				int count = 0;
				if (ps!= null) 
			    count = ps.executeUpdate();//try with resource
				
		    	// Process the ResultSet obj
					if (count == 0) 
						System.out.println("No Record(s) Found For Updatation");
					else
						System.out.println("No.Of Records That Are Effected::" + count);
					
					
			} // try-1

			catch (SQLException se) { // for known Execption
				se.printStackTrace();
			} catch (Exception e) { // for Unknown Execption
				e.printStackTrace();
			}

		}// main

	}// class
