
/** 
 *  DBOperations class
 *  Author: Tomasz Turek
 *	Description: Tests designed to test database connectivity.
 */

package database;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

public class DBOperationsTest {

	/**
	 * Test designed to check if application can establish database connection
	 */
	@Test
	public void testConnection() {

		// initialize connection
		Connection testConnection = null;

		// try connecting to database
		try {

			testConnection = DbOperations.getConnection();

		} catch (Exception e) {

			System.out.println(e);

		}

		// assert if exception was thrown
		assertNotNull(null, testConnection);

	}

}
