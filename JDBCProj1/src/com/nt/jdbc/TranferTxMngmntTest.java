package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TranferTxMngmntTest {
	private static final String GET_BALANCE_BY_ACCNO = "SELECT BALANCE FROM JDBC_ACCOUNT_INFO WHERE ACCNO=?";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
				Statement st = con.createStatement();
				PreparedStatement ps = con.prepareStatement(GET_BALANCE_BY_ACCNO);
				Scanner sc = new Scanner(System.in);) {

			long srcNo = 0, destNo = 0;
			float amount = 0.0f;
			if (sc != null) {
				System.out.println("ENTER SOURCE ACCOUNT NUMBER  ::");
				srcNo = sc.nextLong();
				System.out.println("ENTER DESTINATION ACCOUNT NO ::");
				destNo = sc.nextLong();
				System.out.println("ENTER AMOUNT TO BE TRANSFER  ::");
				amount = sc.nextFloat();
			} // if

			// SET ALUES TO QUERY PARAM::
			if (ps != null) {
				ps.setLong(1, srcNo);
			} // if

			try (ResultSet rs = ps.executeQuery()) {
				float balance = 0.0f;
				if (rs.next()) {
					balance = rs.getFloat(1);
					if (amount > balance) {
						System.out.println("***INSUFFICIENT FUNDS IN THE SOURCE ACCOUNT(Tx ABORTED)***");
						return;
					} // if-2

				} // if-2
				else {
					System.out.println("***SOURCE ACCOUNT NOT FOUND***");
				} // else

			} // try-2

			// BEGIN Tx::
			if (con != null)
				con.setAutoCommit(false);

			if (st != null) {
				// ADD QUERIES TO THE BATCH::

				// FOR WITHDRAW OPERATION::
				st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE-" + amount + "WHERE ACCNO=" + srcNo);
				// FOR DEPOSITE OPERATION::
				st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE+" + amount + "WHERE ACCNO=" + destNo);

				// EXECUTE THE BATCH::
				int result[] = st.executeBatch();

				// PROCESS THE RESULTS BY APPLYING TxMngmnt::
				if (result != null) {
					boolean taskFlag = true;

					for (int i = 0; i < result.length; ++i) {
						if (result[i] == 0) {
							taskFlag = false;
							break;
						} // if-2
					} // for

					if (taskFlag) {
						con.commit();
						System.out.println("TRANSCATION COMMITTED(MONEY TRANFFERED)");
					} // if
					else {
						con.rollback();
						System.out.println("TRANSCATION NOT COMMITTED(ROLLBACK)(MONEY NOT TRANSFERED)");
					} // else

				} // if-2

			} // if-1

		} // try-1

		catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// main

}// class
