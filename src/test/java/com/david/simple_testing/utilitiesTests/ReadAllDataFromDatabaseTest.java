package com.david.simple_testing.utilitiesTests;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.david.simple_testing.models.InisTest;
import com.david.simple_testing.models.Project;
import com.david.simple_testing.models.Step;
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
	ArrayList<InisTest> returnedTests;
	
	@BeforeClass
	public void testSetup() {
		System.out.println("\n\nEntered the test setup...");

		dbc = new DatabaseConnection2();
	}

	@AfterClass
	public void testCleanup() {
		System.out.println("\n\nEntered the test clean up...");
	}

	@Test
	public void testDatabaseConnection() {
		System.out.println("\n\nEntered the test testDatabaseConnection...");

		dbc.connectAndDisconnect(IP, DATABASE, USERNAME, PASSWORD);
	}

	@Test(dependsOnMethods = { "testDatabaseConnection" })
	public void testDatabaseKeepConnectionOpen() {
		System.out.println("\n\nEntered the test testDatabaseKeepConnectionOpen...");

		dbc.connect(IP, DATABASE, USERNAME, PASSWORD);
	}

	@Test(dependsOnMethods = { "testDatabaseKeepConnectionOpen" })
	public void testDatabaseReadProjectById() throws SQLException {
		System.out.println("\n\nEntered the test testDatabaseReadProjectById...");
		int projectId = 1;

		returnedProject = dbc.readProjectById(projectId);
		System.out.println("Returned Project: " + returnedProject);

		Assert.assertTrue(returnedProject != null);
	}

	@Test(dependsOnMethods = { "testDatabaseReadProjectById" })
	public void testDatabaseReadSuitesByProject() throws SQLException {
		System.out.println("\n\nEntered the test testDatabaseReadProjectById...");
		returnedSuites = new ArrayList<>();

		returnedSuites = dbc.readAllSuitesByProject(returnedProject);
		System.out.println("Returned Suites: " + returnedSuites);

		Assert.assertTrue(!returnedSuites.isEmpty());
		for (Suite s : returnedSuites){
			returnedProject.addSuite(s);
		}
	}

	@Test(dependsOnMethods = { "testDatabaseReadSuitesByProject" })
	public void testDatabaseReadTestsBySuite() throws SQLException {
		System.out.println("\n\nEntered the test testDatabaseReadTestsBySuite...");
		returnedTests = new ArrayList<>();

		for (Suite s : returnedSuites) {
			returnedTests = dbc.readAllTestsBySuite(s);
			System.out.println("There are " + returnedTests.size() + " tests in suite " + s.getName());
			
			// Test that there is one test in each Suite
			Assert.assertTrue(returnedTests.size() == 1);
			s.setInisTests(returnedTests);
			
		}
	}
	
	@Test(dependsOnMethods = { "testDatabaseReadTestsBySuite" })
	public void testDatabaseReadStepsByTest() throws SQLException {
		System.out.println("\n\nEntered the test testDatabaseReadTestsBySuite...");
		ArrayList<Step> returnedSteps = new ArrayList<>();

		for (InisTest t : returnedTests) {
			returnedSteps = dbc.readAllStepsByTest(t);
			System.out.println("There are " + returnedSteps.size() + " steps in test" + t.getName());

			// Test that there is 4 steps in each InisTest
			Assert.assertTrue(returnedSteps.size() == 4);
			
			for(Step s : returnedSteps){
				System.out.println(s.toString());
			}
		}
	}

	@Test(dependsOnMethods = { "testDatabaseReadStepsByTest" })
	public void testDatabaseDisconnect() {
		System.out.println("\n\nEntered the test testDatabaseDisconnect...");
		dbc.disconnect();
		System.out.println("The whole lot of info for project:\n");
		System.out.println(returnedProject);
	}
}
