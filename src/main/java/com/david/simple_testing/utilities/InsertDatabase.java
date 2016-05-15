package com.david.simple_testing.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.david.simple_testing.models.Project;

public class InsertDatabase extends DatabaseConnection {

	// Create a Project in DB
	public int insertProject(Project p) {

		int lastInsertId = 0;
		
		String sql = " insert into Projects (name, created_on)" + " values (?, ?)";

		System.out.println("Creating insertProject statement...");
		System.out.println(sql);

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, p.getName());
			preparedStmt.setString(2, p.getCreatedOn());
			// execute the preparedstatement
			preparedStmt.execute();
			
			ResultSet keys = preparedStmt.getGeneratedKeys();    
			keys.next();  
			lastInsertId = keys.getInt(1);

			return lastInsertId;
		} catch (SQLException e) {
			System.out.println("Got an SQLException in the Insert Project");
			e.printStackTrace();
			return lastInsertId;
		}

	}

	// Create a Suite in DB

	// Create an InisTest in DB

	// Create a Step in DB

	// Create Project which will populate all child tables in one method ( like
	// with the read )

}
