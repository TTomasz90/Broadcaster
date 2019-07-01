
/** 
 *  EmailTest class
 *  Author: Tomasz Turek
 *	Description: Tests designed for important methods in the Email class
 *
 */

package messages;

import static org.junit.Assert.assertTrue;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

public class EmailTest {

	/**
	 * Tests proper exception handling when EmailAdress is invalid.
	 *
	 */
	@Test
	public void invalidEmailAdressTest() throws MessagingException {
		Email test = new Email("ttomasz77@", "Test subject", "test message");

		boolean thrown = false;

		try {
			test.sendMessage();
		} catch (AddressException e) {
			System.out.println("Invalid email Address");
			thrown = true;
		}

		assertTrue(thrown);

	}

	/**
	 * Tests proper exception handling for other errors. such as empty fields.
	 */
	@Test
	public void invalidEmailMessageFormatTest() {
		Email test = new Email("", "", "");

		boolean thrown = false;

		try {
			test.sendMessage();
		} catch (MessagingException e) {
			System.out.println("Other error");
			thrown = true;
		}

		assertTrue(thrown);

	}

}
