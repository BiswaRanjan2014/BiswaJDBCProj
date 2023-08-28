//ResultSetMetaDataTest1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest1 {

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
				
				//Display More Details About Each column Of DB Table::
				for(int i=1;i<=colCount;++i) {
					System.out.println("COLUMN INDEX            ::"+i);
					System.out.println("COLUMN NAME             ::"+rsmd.getColumnName(i));
					System.out.println("COLUMN DATA TYPE NAME   ::"+rsmd.getColumnTypeName(i));
					System.out.println("COLUMN SCALE            ::"+rsmd.getScale(i));
					System.out.println("COLMUN PRECISION        ::"+rsmd.getPrecision(i));
					System.out.println("IS COLUMN SIGNED        ::"+rsmd.isSigned(i));
					System.out.println("IS COLUMN AUTOINCREMENT ::"+rsmd.isAutoIncrement(i));
					System.out.println("IS COLUMN NULLABLE      ::"+rsmd.isNullable(i));
					System.out.println("IS COLUMN CURRENCY      ::"+rsmd.isCurrency(i));
					System.out.println("IS COLUMN SEARCHABLE    ::"+rsmd.isSearchable(i));
					System.out.println("COLUMN DISPLAY SIZE     ::"+rsmd.getColumnDisplaySize(i));
					System.out.println("IS COLUMN WRITEABLE     ::"+rsmd.isWritable(i));
					System.out.println("------------------------------------------------------------");
				}//for
				
			}//if	

		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
	