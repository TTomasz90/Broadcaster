
/**
 * DBoperations Class
 * Author: Tomasz Turek
 * Description: Class responsible for reading and writing to and from the MySql database.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;

public class DbOperations {

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

			String USER = "cs622admin";
			String PASS = "cs622";
			Class.forName(DRIVER);

			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			if (connection != null) {

				return connection;
			}
		} catch (SQLException e) {

			throw e;

		}
		return null;

	}

	/**
	 * Add Most recent Quiz results to database.
	 * 
	 * @param quizName
	 * @param userFirstName
	 * @param userLastName
	 * @param score
	 * @throws Exception
	 */
	public static void postData(String quizName, String userFirstName, String userLastName, Double score,
			Integer gradeID) throws Exception {

		// initialize connection
		Connection connection = getConnection();

		try {
			// Create table SQL statement
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO quiz (quizName,firstName,lastName, score,gradeID)" + "VALUES('" + quizName + "','"
							+ userFirstName + "','" + userLastName + "','" + score + "','" + gradeID + "')");

			// execute statement
			statement.executeUpdate();

			// end of statement
			statement.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Fetch all quiz data from the database.
	 * 
	 * @param textArea - result output destination
	 * @throws Exception
	 */
	public static void getAllData(TextArea textArea) throws Exception {

		// initialize a list for results
		List<String> tempList = new ArrayList<String>();

		// initialize connection
		Connection connection = getConnection();

		try {
			// Create table SQL statement
			PreparedStatement statement = connection.prepareStatement(
					"SELECT quiz.quizName,quiz.firstName,quiz.lastName,quiz.score,grades.letterGrade FROM quiz "
							+ "INNER JOIN grades ON quiz.gradeID=grades.gradeID ");

			// ResultSet results
			ResultSet result = statement.executeQuery();

			// Iterate over ResultSet
			while (result.next()) {

				// Add individual fields to list of results.
				tempList.add("Quiz: " + result.getString("quiz.quizName") + "\t\t " + result.getString("quiz.firstName")
						+ "\t" + result.getString("quiz.lastName") + "\t\t" + "Score: "
						+ Math.round(result.getDouble("quiz.score")) + "%" + "\t\t" + "Grade: "
						+ result.getString("grades.letterGrade") + "\n");

			}

			// iterate over array of results and print to screen
			tempList.forEach((line) -> {
				textArea.appendText(line);
			});

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Fetch specific quiz data based on supplied constraints. ordered by Ascending
	 * scores
	 * 
	 * @param resultList
	 * @param field      - table column
	 * @param query      - supplied query constraint
	 * @return list of results
	 */
	public static List<String> performQuery(List<String> resultList, String field, String query) {

		try {

			// initialize a list for results
			Connection connection = getConnection();

			// Create table SQL statement
			PreparedStatement statement = connection.prepareStatement(
					"SELECT quiz.quizName,quiz.firstName,quiz.lastName,quiz.score,grades.letterGrade FROM quiz "
							+ "INNER JOIN grades ON quiz.gradeID=grades.gradeID WHERE " + field + "=" + "'" + query
							+ "'" + "ORDER BY grades.letterGrade ASC");

			// ResultSet results
			ResultSet result = statement.executeQuery();

			// Iterate over ResultSet
			while (result.next()) {

				// Add individual fields to list of results.
				resultList.add("Quiz: " + result.getString("quizName") + "\t\t " + result.getString("firstName") + "\t"
						+ result.getString("lastName") + "\t\t" + "Score: " + Math.round(result.getDouble("score"))
						+ "%" + "\t\t" + "Grade: " + result.getString("grades.letterGrade") + "\n");

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;

	}

	/**
	 * Method managing different query and field constraints supplied by user.
	 * 
	 * @param textArea      - result output destination
	 * @param selectedValue - user selected constraint
	 * @param query         - user input query
	 * @throws Exception
	 */
	public static void getSpecificData(TextArea textArea, Object selectedValue, String query) throws Exception {

		// results returned from database
		List<String> resultList = new ArrayList<String>();

		// field selected by user
		if (selectedValue.equals("First Name")) {

			// perform search
			performQuery(resultList, "firstName", query);

			// display results
			resultList.forEach((line) -> {
				textArea.appendText(line);
			});

		} else if (selectedValue.equals("Last Name")) {

			// perform search
			performQuery(resultList, "lastName", query);

			// display results
			resultList.forEach((line) -> {
				textArea.appendText(line);
			});

		} else if (selectedValue.equals("Quiz Name")) {

			// perform search
			performQuery(resultList, "quizName", query);

			// display results
			resultList.forEach((line) -> {
				textArea.appendText(line);
			});
		}

	}

//	public static void test() throws Exception {
//
//		try {
//
//			ArrayList<String> resultList = new ArrayList<String>();
//			String field = "firstName";
//			String query = "Tomasz";
//			Connection connection = getConnection();
//
//			PreparedStatement statement = connection.prepareStatement(
//					"SELECT quiz.quizName,quiz.firstName,quiz.lastName,quiz.score,grades.letterGrade FROM quiz "
//							+ "INNER JOIN grades ON quiz.gradeID=grades.gradeID WHERE " + field + "=" + "'" + query
//							+ "'");
//
//			ResultSet result = statement.executeQuery();
//
//			while (result.next()) {
//
//				resultList.add("Quiz Name: " + result.getString("quiz.quizName") + "\t "
//						+ result.getString("quiz.firstName") + "\t\t" + result.getString("quiz.lastName") + "\t\t"
//						+ "Score: " + Math.round(result.getDouble("quiz.score")) + "%" + "\t\t" + "Grade: "
//						+ result.getString("grades.letterGrade") + "\n");
//
//			}
//
//			System.out.println(resultList.toString());
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//	}

}
