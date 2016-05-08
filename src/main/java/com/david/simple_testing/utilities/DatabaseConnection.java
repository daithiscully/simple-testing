package com.david.simple_testing.utilities;

import java.sql.*;
import java.util.ArrayList;

import com.david.simple_testing.models.InisTest;

public class DatabaseConnection {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	Connection conn = null;
	Statement stmt = null;

	public void connectAndDisconnect(String ip, String database, String username, String password) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, username, password);

			System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end connectAndDisconnect

	public void connect(String ip, String database, String username, String password) {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, username, password);

			System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}// end connect

	public void disconnect() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end finally try
			// end try
		System.out.println("Disconnected!");
	}// end disconnect

	public void read(String sql) throws SQLException {
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		// STEP 5: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String testName = rs.getString("test_name");
			String testDescription = rs.getString("test_description");

			// Display values
			System.out.println("ID: " + id);
			System.out.println(", test_name: " + testName);
			System.out.println(", test_description: " + testDescription);
		}
		rs.close();
	}
	
	public ArrayList<String> readAllInisTests(String sql) throws SQLException {
		ArrayList<String> results = new ArrayList<>();
		InisTest t1;
		
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		// STEP 5: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String testName = rs.getString("test_name");
			String testDescription = rs.getString("test_description");
			t1 = new InisTest(id, testName, testDescription);

		}
		rs.close();
		
		return results;
	}
}// end DatabaseConnection
