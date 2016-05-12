package com.david.simple_testing.utilitiesTests;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
	public void testDatabaseReadProjectById() {
		System.out.println("Entered the test testDatabaseReadProjectById...");
		int projectId = 1;
		
		try {
			returnedProject = dbc.readProjectById(projectId);
			System.out.println("Returned Project: " + returnedProject);
		} catch (SQLException e) {
			System.out.println("Got an SQL Exception\n ");
			e.printStackTrace();
		}

		Assert.assertTrue(returnedProject != null);
	}
	
	@Test(dependsOnMethods = { "testDatabaseReadProjectById" })
	public void testDatabaseReadSuitesByProject() {
		System.out.println("Entered the test testDatabaseReadProjectById...");
		ArrayList<Suite> returnedSuites = new ArrayList<>();
		
		try {
			returnedSuites = dbc.readAllSuitesByProject(returnedProject);
			System.out.println("Returned Suites: " + returnedSuites);
		} catch (SQLException e) {
			System.out.println("Got an SQL Exception\n ");
			e.printStackTrace();
		}

		Assert.assertTrue(!returnedSuites.isEmpty());
	}

	@Test(dependsOnMethods = { "testDatabaseReadSuitesByProject" })
	public void testDatabaseDisconnect() {
		System.out.println("Entered the test testDatabaseDisconnect...");
		dbc.disconnect();
	}
}
