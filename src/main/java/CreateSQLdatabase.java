/** 
 *  CreateSQLdatabase class
 *  Author: Tomasz Turek
 *	Description: Create tables in the SQL database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

public class CreateSQLdatabase {

	public static void main(String[] args) {

		try {

			// initialize connection.
			getConnection();

			// create quiz table if not exists.
			createQuizTable();

			// create grade table if not exists.
			createGradeTable();

			// populate grade table if not exists.
			populateGradeTable();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Data succesfully created");
		}

	}

	/**
	 * Initializes database connection
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {

		try {

			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String DB_URL = "jdbc:mysql://localhost:3306/broadcaster";

			String USER = "admin";
			String PASS = "pass";
			Class.forName(DRIVER);

			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			if (connection != null) {

				return connection;
			}
		} catch (SQLException e) {

			System.out.println(e);

		}
		return null;

	}

	/**
	 * Create Quizzes Table in MySql Database
	 * 
	 * @throws Exception
	 */
	public static void createQuizTable() throws Exception {

		try {
			// initialize connection
			Connection connection = getConnection();

			// Create table SQL statement
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS quiz ("
					+ "id int NOT NULL AUTO_INCREMENT," + "quizName varchar(255), " + "firstName varchar(255), "
					+ "lastName varchar(255)," + "score float(4), " + "gradeID int," + "PRIMARY KEY (id))");

			// execute statement
			create.executeUpdate();
			// end statement
			create.close();
			// close connection
			connection.close();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

	/**
	 * Create Grade table
	 * 
	 * @throws Exception
	 */
	public static void createGradeTable() throws Exception {

		try {
			// initialize connection
			Connection connection = getConnection();

			// Create table SQL statement
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Grades ("
					+ "gradeID int NOT NULL," + "letterGrade varchar(255), " + "PRIMARY KEY (gradeID))");

			// execute statement
			create.executeUpdate();
			// end statement
			create.close();
			// close connection
			connection.close();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

	/**
	 * Creates fields in the Grade Table of the database
	 * 
	 * @throws Exception
	 */
	public static void populateGradeTable() throws Exception {

		HashMap<Integer, String> grades = new HashMap<Integer, String>();

		grades.put(8, "A");
		grades.put(7, "-A");
		grades.put(6, "+B");
		grades.put(5, "B");
		grades.put(4, "-B");
		grades.put(3, "+C");
		grades.put(2, "C");
		grades.put(1, "-C");
		grades.put(0, "D");

		// initialize connection
		Connection connection = getConnection();

		for (Integer i : grades.keySet()) {

			Integer id = i;
			String letterGrade = grades.get(i);

			try {

				// Create table SQL statement
				PreparedStatement create = connection.prepareStatement(
						"INSERT INTO grades (gradeID,letterGrade)" + "VALUES('" + id + "','" + letterGrade + "')");

				// execute statement
				create.executeUpdate();
				// end statement
				create.close();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("Data already exists in Database");
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		// close connection
		connection.close();

	}

}
