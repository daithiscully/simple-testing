package com.david.simple_testing.utilitiesTests;

import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.david.simple_testing.utilities.DatabaseConnection;

@Test
public class DatabaseConnectionTest {
	
	private final String IP = "localhost";
	private final String DATABASE = "testuser_db";
	private final String USERNAME = "testuser";
	private final String PASSWORD = "password";
	
	DatabaseConnection dbc;
	
	@BeforeClass
	public void testSetup(){
		System.out.println("Entered the test setup...");
		dbc = new DatabaseConnection();
	}
	
	@AfterClass
	public void testCleanup(){
		System.out.println("Entered the test clean up...");
	}
	
	@Test
	public void testDatabaseConnection(){
		System.out.println("Entered the test testDatabaseConnection...");
		dbc.connectAndDisconnect(IP, DATABASE, USERNAME, PASSWORD);
	}
	
	@Test(dependsOnMethods={"testDatabaseConnection"})
	public void testDatabaseKeepConnectionOpen(){
		System.out.println("Entered the test testDatabaseKeepConnectionOpen...");
		dbc.connect(IP, DATABASE, USERNAME, PASSWORD);
	}
	
	@Test(dependsOnMethods={"testDatabaseKeepConnectionOpen"})
	public void testDatabaseRead(){
		System.out.println("Entered the test testDatabaseRead...");
		try {
			dbc.read("SELECT * FROM inis_tests");
		} catch (SQLException e) {
			System.out.println("Got an SQL Exception\n ");
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods={"testDatabaseRead"})
	public void testDatabaseDisconnect(){
		System.out.println("Entered the test testDatabaseDisconnect...");
		dbc.disconnect();
	}
}
