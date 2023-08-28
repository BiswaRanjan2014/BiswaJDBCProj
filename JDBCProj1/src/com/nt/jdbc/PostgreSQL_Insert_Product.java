//PostgreSQL_Insert_Product.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgreSQL_Insert_Product {
    private static final String INSERT_PRODUCT_QUERY ="INSERT INTO PRODUCT VALUES(NEXTVAL('pid_seq'),?,?,?)";
    
	public static void main(String[] args) {
		 
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pintudb1","postgres","tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT_QUERY);
				Scanner sc=new Scanner(System.in); ){

			//Read Values From EndUser::
			String name=null;
			double price=0.0,qty=0.0;
			if(sc!=null) {
				System.out.println("ENTER PRODUCT NAME::");
				name=sc.next();
				System.out.println("ENTER PRODUCT PRICE::");
				price=sc.nextDouble();
				System.out.println("ENTER PRODUCT QTY::");
				qty=sc.nextDouble();
			}
			
			//Set Values To Query Params::
			if(ps!=null) {
				ps.setString(1, name);
				ps.setDouble(2, price);
				ps.setDouble(3, qty);
				
				//Execute The Query::
				int count=ps.executeUpdate();
				
				//Process The Results::
				if(count==0)
					System.out.println("RECORD NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
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


	
	