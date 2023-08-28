//CachedRowSetDemo.java
package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		
		try(CachedRowSet crowset=new OracleCachedRowSet()){
			
			  //set properties::
			  crowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			  crowset.setUsername("system");
			  crowset.setPassword("tiger");
			  
			  //set query::
			  crowset.setCommand("SELECT * FROM STUDENT");
			  
			  //execute query::
			  crowset.execute();
			  
			  //process the rowset::
			  while(crowset.next()) {
				  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3)+" "+crowset.getFloat(4));
			  }//while

			  System.out.println("STOP THE DB s/w DURING THE SLEEP PERIOD OF THE APPLICATION");
			  Thread.sleep(90000);
			  crowset.absolute(3);
			  crowset.updateString(3,"telgna");
			  crowset.updateRow();
			  System.out.println("START THE DB s/w DURING THE SLEEP PERIOD OF THE APPLICATION");
			  Thread.sleep(90000);
			  crowset.acceptChanges();
			  
			  //process the results::
			  while(crowset.next()) {
				  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3)+" "+crowset.getFloat(4));
			  }//while
			  
	
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class