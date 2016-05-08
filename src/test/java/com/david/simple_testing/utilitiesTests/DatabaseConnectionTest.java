package com.david.simple_testing.utilitiesTests;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.david.simple_testing.models.InisTest;
import com.david.simple_testing.utilities.DatabaseConnection;

public class DatabaseConnectionTest {

	private final String IP = "localhost";
	private final String DATABASE = "testuser_db";
	private final String USERNAME = "testuser";
	private final String PASSWORD = "password";

	DatabaseConnection dbc;

	@BeforeClass
	public void testSetup() {
		System.out.println("Entered the test setup...");

		dbc = new DatabaseConnection();
	}

	@AfterClass
	public void testCleanup() {
		System.out.println("Entered the test clean up...");
	}

	@Test
	public void testDatabaseConnection() {
		System.out.println("Entered the test testDatabaseConnection...");

		dbc.connectAndDisconnect(IP, DATABASE, USERNAME, PASSWORD);
	}

	@Test(dependsOnMethods = { "testDatabaseConnection" })
	public void testDatabaseKeepConnectionOpen() {
		System.out.println("Entered the test testDatabaseKeepConnectionOpen...");

		dbc.connect(IP, DATABASE, USERNAME, PASSWORD);
	}

	@Test(dependsOnMethods = { "testDatabaseKeepConnectionOpen" })
	public void testDatabaseRead() {
		System.out.println("Entered the test testDatabaseRead...");

		ArrayList<InisTest> returnedTests = new ArrayList<>();
		try {
			returnedTests = dbc.readAllInisTests("SELECT * FROM inis_tests");
			System.out.println("Returned Tests: " + returnedTests);
		} catch (SQLException e) {
			System.out.println("Got an SQL Exception\n ");
			e.printStackTrace();
		}
		
		Assert.assertFalse(returnedTests.isEmpty());
	}

	@Test(dependsOnMethods = { "testDatabaseRead" })
	public void testDatabaseDisconnect() {
		System.out.println("Entered the test testDatabaseDisconnect...");
		dbc.disconnect();
	}
}
