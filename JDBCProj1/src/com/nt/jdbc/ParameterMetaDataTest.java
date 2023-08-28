//ParameterMetaDataTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ParameterMetaDataTest {
     private static final String INSERT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
     
	public static void main(String[] args) {
		
		try (//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				Connection con = DriverManager.getConnection("jdbc:mysql:///PINTUDB1", "root", "pintu");
				PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
			){
			
			ParameterMetaData pmd=null;
			if(ps!=null) {
				pmd=ps.getParameterMetaData();
			}//if
			
			if(pmd!=null) {
				//Get Parameters Count::
				int paramsCount=pmd.getParameterCount();
				for(int i=1;i<=paramsCount;++i) {
					System.out.println("PARAMETER INDEX    ::" + i);
					System.out.println("PARAM MODE         ::" + pmd.getParameterMode(i));
					System.out.println("IS PARAM SIGNED?   ::" + pmd.isSigned(i));
					System.out.println("IS PARAM NULLABLE? ::" + pmd.isNullable(i));
					System.out.println("PARAM SCALE        ::" + pmd.getScale(i));
					System.out.println("PARAM PRECISION    ::" + pmd.getPrecision(i));
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
	