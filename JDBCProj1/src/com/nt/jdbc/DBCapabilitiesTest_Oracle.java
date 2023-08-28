//DBCapabilitiesTest_Oracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCapabilitiesTest_Oracle {

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				){
			
			//Create Database MetaData object::
			DatabaseMetaData dbmd=null;
			if(con!=null) {
				dbmd=con.getMetaData();
			}
			
			//Get More Info About DB s/w::
			if(dbmd!=null) {
				System.out.println("DB s/w PRODUCT NAME            ::" +dbmd.getDatabaseProductName());
				System.out.println("DB s/w PRODUCT VERSION         ::" +dbmd.getDatabaseProductVersion());
				System.out.println("DB s/w MAJOR VERSION           ::" +dbmd.getDatabaseMajorVersion());
				System.out.println("JDBC DRIVER NAME               ::" +dbmd.getDriverName());
			    System.out.println("JDBC MAJOR AND MINOR VERSION   ::" +dbmd.getJDBCMajorVersion()+"."+dbmd.getJDBCMinorVersion());
				System.out.println("MAX CONNECTIONS COUNT          ::" +dbmd.getMaxConnections());
				System.out.println("MAX DB TABLE NAME LENGTH       ::" +dbmd.getMaxTablesInSelect());
				System.out.println("MAX USERNAME LENGTH            ::" +dbmd.getMaxUserNameLength());
				System.out.println("MAX COLUMNS COUNT IN DB TABLE  ::" +dbmd.getMaxColumnsInTable());
				System.out.println("MAX ROW SIZE                   ::" +dbmd.getMaxRowSize());
				System.out.println("SUPPORTS STORED PROCEDURES ??  ::" +dbmd.supportsStoredProcedures());
				System.out.println("ALL NUMERIC FUNCTIONS          ::" +dbmd.getNumericFunctions());
				System.out.println("ALL SYSTEM FUNCTIONS           ::" +dbmd.getSystemFunctions());
				System.out.println("ALL SQL KEYWORDS               ::" +dbmd.getSQLKeywords());
			}//if
			
		}//try
		 catch(SQLException se) {
			 se.printStackTrace();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }

	}// main

}// class