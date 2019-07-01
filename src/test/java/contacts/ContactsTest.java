
/** 
 *  ContactsTest class
 *  Author: Tomasz Turek
 *	Description: Tests designed for important methods in the Contacts class
 *
 */

package contacts;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class ContactsTest {


	/**
	 * Test searching for contacts in the contactList based on keyword.
	 */
	@Test
	public void findContactTest() {

		// given 

		//list of test contacts
		ArrayList<Contact> testList = new ArrayList<Contact>();

		testList.add(new Contact("James", "Smith", "6464001133", "James@gmail.com"));
		
		testList.add(new Contact("Kevin", "Logan", "6464434344", "Kevin@gmail.com"));
		
		testList.add(new Contact("Bill", "Anderson", "6464005433", "Bill@gmail.com"));

		// when 
		
		// sample expected contact
		ArrayList<Contact> expected = new ArrayList<Contact>();
		
		Contact queryContact = new Contact("Kevin", "Logan", "6464434344", "Kevin@gmail.com");
		
		expected.add(queryContact);
		
		// result from a contact search
		ArrayList<Contact> actual = Contacts.findContact(testList, "Logan");
		
	
		// then
		
		Assert.assertEquals(Arrays.toString(expected.toArray()), Arrays.toString(actual.toArray()));
	}

}
