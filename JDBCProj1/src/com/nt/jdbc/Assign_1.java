//Assign_1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assign_1 {

	public static void main(String[] args) {
		
				    Scanner sc = null;
				    Connection con = null;
				    Statement st = null;
				    ResultSet rs = null;
				    try {
				      // read inputs
				      sc = new Scanner(System.in);
				      String Sadress = null;
				      if (sc != null) {
				        System.out.println("Enter Student Address::");
				        Sadress = sc.next().toLowerCase();

				      } // if
				        // convert the input values as required for the sql
				        // query('Sadress')
				      String cond = "'" + Sadress + "'";
				      System.out.println(cond);
				      // Load jdbc driver class(optional-because of autoloading jdbc driverb  
				      // class-jdbc8 feature)

				      Class.forName("oracle.jdbc.driver.OracleDriver");

				      // Estalish the connection with oracle Db s/w
				      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

				      // creat jdbc statement object
				      if (con != null)
				        st = con.createStatement();
				      // Prepare SQL Query in DB S/W
				      // SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SADD='jsp';
				      String query = " SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SADD=" + cond;
				      System.out.println(query);

				      // Send Execute SQL Query inDB s/w
				      if (st != null)
				        rs = st.executeQuery(query);
				      // process the resultset obj
				      if (rs != null) {
				        boolean rsEmpty = true;
				        while (rs.next()) {
				          rsEmpty = false;
				          System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));

				        } // while
				        if (rsEmpty == true)// or if(rsEmpty)
				          System.out.println("NO RECORDS FOUND");
				        else
				          System.out.println("RECORD FOUND AND DISPLAY");
				      } // if

				    } // try

				    catch (SQLException se) { // for known exception
				      se.printStackTrace();
				    } catch (ClassNotFoundException cnf) { // for known exception
				      cnf.printStackTrace();
				    } catch (Exception e) { // for known exception
				      e.printStackTrace();
				    } finally { // good finally block

				      try {
				        if (rs != null)
				          rs.close();
				      } catch (SQLException se) {
				        se.printStackTrace();
				      }

				      try {
				        if (st != null)
				          st.close();
				      } catch (SQLException se) {
				        se.printStackTrace();
				      }

				      try {
				        if (con != null)
				          con.close();
				      }

				      catch (SQLException se) {
				        se.printStackTrace();
				      }

				      try {
				        if (sc != null)
				          sc.close();
				      }

				      catch (Exception e) {
				        e.printStackTrace();
				      }

				    } // finally

				  }// main

				}// class


