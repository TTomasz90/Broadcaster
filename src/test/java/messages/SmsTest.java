
/** 
 *  SMSTest class
 *  Author: Tomasz Turek
 *	Description: Tests designed for important methods in the Sms class
 *
 */

package messages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import messages.Sms;

import org.junit.Assert;
import org.junit.Test;

public class SmsTest {

	/**
	 * Test sending SMS to a test number that exist
	 */
	@Test
	public void successMessageTest() {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// given
		Sms testSms = new Sms("test message");

		// when

		// Deliverable phone number
		testSms.setRecipientPhoneNumber("+15005550006");

		// then
		testSms.sendMessage();

		String actual = outContent.toString();
		System.out.println(actual);
		String expected = "Message Delivered";

		Assert.assertEquals(expected, actual.trim());

	}

	/**
	 * Test sending SMS exception handling when phone number is invalid
	 */
	@Test
	public void unsuccesfullMessageTest() {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// given
		Sms testSms = new Sms("test message");

		// when

		// Undeliverable phone number
		testSms.setRecipientPhoneNumber("+150055500011");

		// then
		testSms.sendMessage();

		String actual = outContent.toString();

		System.out.println(actual);

		String expected = "Message Undeliverable";

		Assert.assertEquals(expected, actual.trim());

	}

	/**
	 * Test message logging function
	 */
	@Test
	public void toStringTest() {
		// given
		Sms testSms = new Sms("test message");

		// when
		testSms.setRecipientPhoneNumber("6464004444");

		// then
		String actual = testSms.toString();

		String expected = "Sms [recipientPhoneNumber=6464004444, senderPhoneNumber=+19179050846, message=test message]";

		Assert.assertEquals(expected, actual);

	}

}
