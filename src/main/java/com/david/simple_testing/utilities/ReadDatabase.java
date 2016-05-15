package com.david.simple_testing.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.david.simple_testing.models.InisTest;
import com.david.simple_testing.models.Project;
import com.david.simple_testing.models.Step;
import com.david.simple_testing.models.Suite;

public class ReadDatabase extends DatabaseConnection {

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

		System.out.println("Creating readAllTestsBySuite statement...");
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

	public ArrayList<Step> readAllStepsByTest(InisTest test) throws SQLException {
		String sql = String.format("SELECT * FROM Steps WHERE test=%d", test.getId());
		ArrayList<Step> results = new ArrayList<>();
		Step s;

		System.out.println("Creating readAllStepsByTest statement...");
		System.out.println(sql);

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			// int test_id = rs.getInt("test");
			String stepAction = rs.getString("action");
			String stepActionData1 = rs.getString("action_data_1");
			String stepActionData2 = rs.getString("action_data_2");
			s = new Step(id, test, stepAction, stepActionData1, stepActionData2);
			results.add(s);
		}
		rs.close();

		return results;
	}

	public Project getAllProjectData(int projectId) throws SQLException {
		Project p = readProjectById(projectId);

		// Get all suites belonging to that project
		ArrayList<Suite> projectSuites = readAllSuitesByProject(p);
		for (Suite s : projectSuites) {
			// Get all InisTests belonging to that Suite
			ArrayList<InisTest> suitesTests = readAllTestsBySuite(s);

			for (InisTest t : suitesTests) {

				// Get all Steps belonging to that InisTest
				ArrayList<Step> testsSteps = readAllStepsByTest(t);

				for (Step step : testsSteps) {
					// Add each Step to the InisTest
					t.addStep(step);
				}
				// Add each InisTest to the Suite
				s.addInisTest(t);
			}
			// Add each Suite to the project ADD LAST after every else has been
			// ordered. Backwards
			p.addSuite(s);
		}
		return p;
	}
}// end DatabaseConnection