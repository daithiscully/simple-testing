package com.david.simple_testing.utilitiesTests;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.david.simple_testing.models.InisTest;
import com.david.simple_testing.models.Project;
import com.david.simple_testing.models.Suite;
import com.david.simple_testing.utilities.DatabaseConnection2;

public class ReadAllDataFromDatabaseTest {

	private final String IP = "localhost";
	private final String DATABASE = "simpletesting";
	private final String USERNAME = "testuser";
	private final String PASSWORD = "password";

	DatabaseConnection2 dbc;
	Project returnedProject = null;
	ArrayList<Suite> returnedSuites;

	@BeforeClass
	public void testSetup() {
		System.out.println("Entered the test setup...");

		dbc = new DatabaseConnection2();
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
	public void testDatabaseReadProjectById() throws SQLException {
		System.out.println("Entered the test testDatabaseReadProjectById...");
		int projectId = 1;

		returnedProject = dbc.readProjectById(projectId);
		System.out.println("Returned Project: " + returnedProject);

		Assert.assertTrue(returnedProject != null);
	}

	@Test(dependsOnMethods = { "testDatabaseReadProjectById" })
	public void testDatabaseReadSuitesByProject() throws SQLException {
		System.out.println("Entered the test testDatabaseReadProjectById...");
		returnedSuites = new ArrayList<>();

		returnedSuites = dbc.readAllSuitesByProject(returnedProject);
		System.out.println("Returned Suites: " + returnedSuites);

		Assert.assertTrue(!returnedSuites.isEmpty());
	}

	@Test(dependsOnMethods = { "testDatabaseReadSuitesByProject" })
	public void testDatabaseReadTestsBySuite() throws SQLException {
		System.out.println("Entered the test testDatabaseReadTestsBySuite...");
		ArrayList<InisTest> returnedTests = new ArrayList<>();

		for (Suite s : returnedSuites) {
			returnedTests = dbc.readAllTestsBySuite(s);
			System.out.println("Suite " + s.getName() + " has these tests:\n" + returnedTests);
		}

		Assert.assertTrue(!returnedTests.isEmpty());
	}

	@Test(dependsOnMethods = { "testDatabaseReadSuitesByProject" })
	public void testDatabaseDisconnect() {
		System.out.println("Entered the test testDatabaseDisconnect...");
		dbc.disconnect();
	}
}
