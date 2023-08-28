//InsertTestUsingTWR1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestUsingTWR1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	try(sc){  //try with resource-1
		
		int no=0;
		String name=null,addrs=null;
		float avg=0.0F;
		if(sc!=null) {
			System.out.println("ENTER STUDENT NUMBER::");
			no = sc.nextInt();// gives 111
			System.out.println("ENTER STUDENT NAME::");
			name = sc.next();// gives pintu
			System.out.println("ENTER STUDENT ADDRESS::");
			addrs = sc.next();// gives wakanda
			System.out.println("ENTER STUDENT AVERAGE::");
			avg = sc.nextFloat();// gives 85.77
		}
		
		// Convert input values as required for the SQL Query
		name  = "'" + name + "'";// gives'pintu'
		addrs = "'" + addrs + "'";// gives'wankanda'
		
		// Establish the connection and Create Statement object
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
		Statement st   = con.createStatement();
		
		//try with resource-2(nested try with resource)
		try(con;st){
			
			// Prepare SQL Query as required for the DB s/w
			// INSERT INTO STUDENT VALUES(111,'pintu','wankanda',85.77);
			String query = "INSERT INTO STUDENT VALUES(" + no + "," + name + "," + addrs + "," + avg + ")";
			System.out.println(query);
			
			// Send and Execute the SQL Query in DB s/w
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);

			// Process the Result
			if (count == 0)
				System.out.println("Record Not Inserted");
			else
				System.out.println("Record Inserted");
		}//try-2
		
	} // try-1
		catch (SQLException se) { // for known Execption
			se.printStackTrace();
		} catch (Exception e) { // for Unknown Execption
			e.printStackTrace();
		}
		
	}//main
	
}// class
