package com.david.simple_testing.utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.david.simple_testing.models.Project;

public class DeleteDatabase extends DatabaseConnection {

	// Create a Project in DB
	public boolean deleteProject(Project p) {

		String sql = String.format(" DELETE FROM `simpletesting`.`Projects` WHERE `id`='%d';", p.getId());

		System.out.println("Creating deleteProject statement...");
		System.out.println(sql);

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(sql);
			// execute the preparedstatement
			preparedStmt.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Got an SQLException in the Delete Project");
			e.printStackTrace();
			return false;
		}

	}

	// Create a Suite in DB

	// Create an InisTest in DB

	// Create a Step in DB

	// Create Project which will populate all child tables in one method ( like
	// with the read )

}
