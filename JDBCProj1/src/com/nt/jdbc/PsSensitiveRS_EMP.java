//PsSensitiveRS_EMP.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSensitiveRS_EMP {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement("SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP",
						                                             ResultSet.TYPE_SCROLL_SENSITIVE,
						                                             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
		
	
			if (rs != null) {
				System.out.println("RECORDS DISPLAY TOP TO BOTTOM::");
				int count=0;
				while (rs.next()) {
					
					if(count==0) {
						System.out.println("IN NEXT [30SEC] MODIFY THE RECORDS EMP DB TABLE::");
						Thread.sleep(30000);
					}
					
					rs.refreshRow();
					count++;
					
					 System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getDate(5) + " "+ rs.getInt(6)  + " " + rs.getInt(7) +" " + rs.getInt(8));
					
				} //while
				
			} //if
			
		} //try

		catch (SQLException se) {
			se.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
