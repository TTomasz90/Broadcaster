/** 
 *  Messages class
 *  Author: Tomasz Turek
 *	Description: Abstract class governing message creation.
 *
 */

package messages;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Abstract class Messages includes subclasses Sms and Email. Required
 * implementation: sendMessage()
 */
public abstract class Messages {

	// user message
	protected String userMessage;

	// constructor
	public Messages(String message) {

		this.userMessage = message;

	}

	/**
	 * Implement send message method
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	abstract public void sendMessage() throws AddressException, MessagingException;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return userMessage;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.userMessage = message;
	}

	/**
	 * @param print message contents
	 */
	public String toString() {
		return "Message [message=" + userMessage + "]";
	}

}