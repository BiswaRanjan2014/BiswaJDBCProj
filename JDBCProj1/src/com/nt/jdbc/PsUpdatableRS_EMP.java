//PsUpdatableRS_EMP.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsUpdatableRS_EMP {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				PreparedStatement ps = con.prepareStatement("SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP",
						                                             ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
			
			if (rs != null) {
				System.out.println("SELECT EMP OPERATION::");
				System.out.println();
				while (rs.next()) {
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getDate(5) + " "+ rs.getInt(6)  + " " + rs.getInt(7) +" " + rs.getInt(8));
						
				} //while
				
				/*
				//Insert Operation::
				rs.moveToInsertRow();
				rs.updateInt(1, 7468);
				rs.updateString(2, "RICK");
				rs.updateString(3, "ANALYST");
				rs.updateInt(4, 7689);
				rs.updateDate(5, null);
				rs.updateInt(6, 3500);
				rs.updateInt(7, 0 );
				rs.updateInt(8, 20);
				rs.insertRow();
				System.out.println("RECORD INSERTED::");
				*/
				
				/*
				//Update Operation::
				rs.absolute(2);
				rs.updateString(2, "JACOB");
				rs.updateString(3, "ANALYST");
				rs.updateRow();
				System.out.println("RECORD UPDATE::");
		        */
				
				
				//Delete Operation::
				rs.absolute(15);
				rs.deleteRow();
				System.out.println("RECORD DELECTED::");
				
				
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

