//FilteredRowSet.java
package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;

public class FilteredRowSet {
	 //nested class = static inner class
	 private static class MyFilter  implements Predicate{
		  private String condData;
		  
		  public MyFilter(String condData) {
			  this.condData=condData;
		  }

		@Override
		public boolean evaluate(RowSet rs)  {
			System.out.println("MyFilter :: evaluate(-)");
			try {
			if(rs.getString(2).startsWith(condData))
		     	return true;
			else
				return false;
			}
			catch(Exception e) {
				return false;
			}
		}

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			return false;
		}

		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			return false;
		}
		 
	 }
	
	public static void main(String[] args) {
		
		try(OracleFilteredRowSet frs=new OracleFilteredRowSet()){
			  //set properties
			  frs.setUrl("jdbc:oracle:thin:@localhost:1521:orcl ");
			  frs.setUsername("system");
			  frs.setPassword("tiger");
			  frs.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP ");
			  frs.setFilter(new MyFilter("A"));
			  frs.execute();
			  
			  //process the rowset
			  while(frs.next()) {
				  System.out.println(frs.getInt(1)+"  "+frs.getString(2)+"  "+frs.getString(3)+" "+frs.getFloat(4)+" "+frs.getInt(5));
			  }
		
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class