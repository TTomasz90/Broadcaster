
/** 
 *  UserStreamTest class
 *  Author: Tomasz Turek
 *	Description: Test correct List stream sorting functionality.
 *
 */

package statistics;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import contacts.Contact;

public class UserStreamsTest {

	/**
	 * Test correct Element ordering using Streams Highest -> Lowest
	 */
	@Test
	public void userStreamDescendingTest() {

		// given random list of results.
		ArrayList<Contact> actuaList = new ArrayList<Contact>();

		Contact c1 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		c1.percentCorrect = 50.0;
		actuaList.add(c1);

		Contact c2 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		c2.percentCorrect = 100.0;
		actuaList.add(c2);

		Contact c3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		c3.percentCorrect = 10.0;
		actuaList.add(c3);

		// given expected list of results after applying filter.
		ArrayList<Contact> expectedList = new ArrayList<Contact>();

		Contact e1 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		e1.percentCorrect = 100.0;
		expectedList.add(e1);

		Contact e2 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		e2.percentCorrect = 50.0;
		expectedList.add(e2);

		Contact e3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		e3.percentCorrect = 10.0;
		expectedList.add(e3);

		// when applying a filter to the actual results
		List<Contact> resultList = UserStreams.sortStreamDescending(actuaList);

		// then filtered results should equal expected results
		Assert.assertEquals(expectedList.toString(), resultList.toString());

	}

	/**
	 * Test correct Element ordering using Streams Lowest -> Highest
	 */
	@Test
	public void userStreamAscendingTest() {

		// given random list of results.
		ArrayList<Contact> actuaList = new ArrayList<Contact>();

		Contact c1 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		c1.percentCorrect = 50.0;
		actuaList.add(c1);

		Contact c2 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		c2.percentCorrect = 100.0;
		actuaList.add(c2);

		Contact c3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		c3.percentCorrect = 10.0;
		actuaList.add(c3);

		// given expected list of results after applying filter.
		ArrayList<Contact> expectedList = new ArrayList<Contact>();

		Contact e3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		e3.percentCorrect = 10.0;
		expectedList.add(e3);

		Contact e2 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		e2.percentCorrect = 50.0;
		expectedList.add(e2);

		Contact e1 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		e1.percentCorrect = 100.0;
		expectedList.add(e1);

		// when applying a filter to the actual results
		List<Contact> resultList = UserStreams.sortStreamAscending(actuaList);

		// then filtered results should equal expected results
		Assert.assertEquals(expectedList.toString(), resultList.toString());

	}

	/**
	 * Test correct Element filtering using Streams Finding Top scoring individuals
	 */
	@Test
	public void userStreamTopScoreTest() {

		// given random list of results.
		ArrayList<Contact> actuaList = new ArrayList<Contact>();

		Contact c1 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		c1.percentCorrect = 50.0;
		actuaList.add(c1);

		Contact c2 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		c2.percentCorrect = 100.0;
		actuaList.add(c2);

		Contact c3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		c3.percentCorrect = 10.0;
		actuaList.add(c3);

		// given expected list of results after applying filter.
		ArrayList<Contact> expectedList = new ArrayList<Contact>();

		Contact e1 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		e1.percentCorrect = 100.0;
		expectedList.add(e1);

		// when applying a filter to the actual results
		List<Contact> resultList = UserStreams.topPerformers(actuaList);

		// then filtered results should equal expected results
		Assert.assertEquals(expectedList.toString(), resultList.toString());

	}

	/**
	 * Test correct Element filtering using Streams Finding Lowest scoring
	 * individuals
	 */
	@Test
	public void userStreamLowestScoreTest() {

		// given random list of results.
		ArrayList<Contact> actuaList = new ArrayList<Contact>();

		Contact c1 = new Contact("Arnold", "Logan", "4343434343", "Test2@gmail.com");
		c1.percentCorrect = 50.0;
		actuaList.add(c1);

		Contact c2 = new Contact("Ronals", "Park", "2323323232", "Test@gmail.com");
		c2.percentCorrect = 100.0;
		actuaList.add(c2);

		Contact c3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		c3.percentCorrect = 10.0;
		actuaList.add(c3);

		// given expected list of results after applying filter.
		ArrayList<Contact> expectedList = new ArrayList<Contact>();

		Contact e3 = new Contact("Kevin", "Smith", "5454545454", "Test3@gmail.com");
		e3.percentCorrect = 10.0;
		expectedList.add(e3);

		// when applying a filter to the actual results
		List<Contact> resultList = UserStreams.bottomPerformers(actuaList);

		// then filtered results should equal expected results
		Assert.assertEquals(expectedList.toString(), resultList.toString());

	}

}
