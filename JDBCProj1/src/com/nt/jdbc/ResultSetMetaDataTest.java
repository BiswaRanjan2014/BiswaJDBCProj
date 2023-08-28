package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
				){  
			
			ResultSetMetaData rsmd =null;
			if(rs!=null) {
				rsmd=rs.getMetaData();
			} 
			if(rs!=null && rsmd!=null) {
				//Get Cols Count::
				int colCount=rsmd.getColumnCount();
				
				//Display Col Types::
				for(int i=1;i<=colCount;++i) {
					System.out.println(rsmd.getColumnTypeName(i)+"\t");
				}//for
				System.out.println();
				
				//Display Col Names::
				for(int i=1;i<=colCount;++i) {
					System.out.println(rsmd.getColumnName(i)+"\t\t");
				}//for
				System.out.println();
				
				//Display Col Values
				while(rs.next()) {
					for(int i=1;i<=colCount;++i) {
						System.out.println(rs.getString(i)+"\t\t\t");
				    }//for
					System.out.println();
			     }//while
		    }//if

	    }//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
