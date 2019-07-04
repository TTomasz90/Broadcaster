
/** 
 *  QuizTest class
 *  Author: Tomasz Turek
 *	Description: Tests designed to test Quiz server connectivity
 *
 */

package quiz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import contacts.Contact;

public class QuizTest {

	/**
	 * Tests server connectivity by initializing a test quiz.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testServerConnectivity() throws IOException {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));

		// given a test question,answer and contact list

		ArrayList<Contact> testList = new ArrayList<Contact>();

		testList.add(new Contact("Test", "Test", "6464001776", "Ttomasz77@gmail.com"));

		// when Quiz is initialized and test request sent to server

		Quiz testQuiz = new Quiz("Test question?", "test", testList);

		testQuiz.initializeQuiz("http://brod-server.herokuapp.com/test");

		// then Compare server response to expected response.

		String actual = outContent.toString();

		System.out.println(actual);

		String expected = "Test response";

		Assert.assertEquals(expected, actual.trim());
	}

}
