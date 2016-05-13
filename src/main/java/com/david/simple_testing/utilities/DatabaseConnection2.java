package com.david.simple_testing.utilities;

import java.sql.*;
import java.util.ArrayList;

import com.david.simple_testing.models.InisTest;
import com.david.simple_testing.models.Project;
import com.david.simple_testing.models.Suite;

public class DatabaseConnection2 {

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
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("Disconnected!");
	}// end disconnect

	public Project readProjectById(int projectId) throws SQLException {
		String sql = String.format("SELECT * FROM Projects WHERE id=%d", projectId);
		Project p = null;

		System.out.println("Creating readAllProjects statement...");
		System.out.println(sql);

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String projectName = rs.getString("name");
			String projectCreatedOn = rs.getString("created_on");
			p = new Project(id, projectName, projectCreatedOn);
		}
		rs.close();

		return p;
	}

	public ArrayList<Suite> readAllSuitesByProject(Project project) throws SQLException {
		String sql = String.format("SELECT * FROM Suites WHERE project=%d", project.getId());
		ArrayList<Suite> results = new ArrayList<>();
		Suite s;

		System.out.println("Creating readAllSuitesByProject statement...");
		System.out.println(sql);

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			// int project_id = rs.getInt("project");
			String suiteName = rs.getString("name");
			String suiteDescription = rs.getString("description");
			s = new Suite(id, project, suiteName, suiteDescription);
			results.add(s);
		}
		rs.close();

		return results;
	}

	public ArrayList<InisTest> readAllTestsBySuite(Suite suite) throws SQLException {
		String sql = String.format("SELECT * FROM InisTests WHERE suite=%d", suite.getId());
		ArrayList<InisTest> results = new ArrayList<>();
		InisTest t;

		System.out.println("Creating readAllSuitesByProject statement...");
		System.out.println(sql);

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			// int project_id = rs.getInt("project");
			String suiteName = rs.getString("name");
			String suiteDescription = rs.getString("description");
			t = new InisTest(id, suite, suiteName, suiteDescription);
			results.add(t);
		}
		rs.close();

		return results;
	}
}// end DatabaseConnection
