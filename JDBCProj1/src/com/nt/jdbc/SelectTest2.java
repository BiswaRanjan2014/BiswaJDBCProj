//SelectTest2.java

package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/*JDBC App to get Emp DB Table Records Based on the given 3 Desginations*/

public class SelectTest2 {

	public static void main(String[] args) {
		
            Scanner sc=null;
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
            try {
            	//Read Inputs
            	sc=new Scanner(System.in);
            	String desg1=null,desg2=null,desg3=null;
            	if(sc!=null) {
            		System.out.println("Enter Desgination1::");
            		desg1=sc.next().toUpperCase();//Gives CLERK
            		System.out.println("Enter Desgination2::");
            		desg2=sc.next().toUpperCase();//Gives MANAGER
            		System.out.println("Enter Desgination3::");
            		desg3=sc.next().toUpperCase();//Gives SALESMAN
            	}//if
            	
            	//conver the input values as requried for the SQL Query ('CLERK','MANAGER','SALESMAN')
            	String cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
            	System.out.println(cond);
            	
            	//Load JDBC Driver Class (Optional)
            	Class.forName("oracle.jdbc.driver.OracleDriver");

                //Establish the connection
                con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
                
                //Create Statement object
                if(con!=null)
                	st=con.createStatement();
                //Prepare SQL Query
                // SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN')ORDER BY JOB;
                String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond+"ORDER BY JOB";
                System.out.println(query);
                
                //Send Execute SQL Query in Db s/w
                if(st!=null)
                	rs=st.executeQuery(query);
                
                //Process the Resultset obj
                if(rs!=null) {
                	boolean rsEmpty=true;
                	while(rs.next()) {
                		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
                	}//while
                	if(rsEmpty==true)
                		System.out.println("No Record Found");
                	else
                		System.out.println("Records Are Found And Dislayed");
                }//if
            }//try
            
            catch(SQLException se){ //for known Execption
                se.printStackTrace();
           }
           catch(ClassNotFoundException cnf){ //for known Execption 
                cnf.printStackTrace();
           }
           catch(Exception e){ //for Unknown Execption
                e.printStackTrace();
           }
        finally{ 
        	//Close JDBC objs
           try{
              if(rs!=null)
                   rs.close();
            }
            catch(SQLException se){
               se.printStackTrace();
            }

            try{
              if(st!=null)
                   st.close();
            }
            catch(SQLException se){
               se.printStackTrace();
            }

            try{
              if(con!=null)
                   con.close();
            }
            catch(SQLException se){
               se.printStackTrace();
            }

            try{
              if(sc!=null)
                   sc.close();
            }
            catch(Exception e){
               e.printStackTrace();
            }
        }//Finally

	}// Main

}// Class
