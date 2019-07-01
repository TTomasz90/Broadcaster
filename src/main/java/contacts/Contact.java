
/** 
 *  Contact class
 *  Author: Tomasz Turek
 *	Description: Contact object designed to hold information about a specific contact
 *	including name, last name, phone number and email
 *
 */

package contacts;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public String emailAddress;

	public Integer numberCorrect = 0;
	public Integer numberInocrrect = 0;
	public Integer questiosAnswered = 0;
	public Double percentCorrect = 0.0;
	public Double questionsAsked = 0.0;
	public Integer designatedGrade = 0;

	public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;

	}

	public Contact(String firstName, String lastName, Double score) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.percentCorrect = score;

	}

	/**
	 * Return contact information as string
	 */
	public String toString() {

		return this.firstName + " " + this.lastName + " " + this.phoneNumber + " " + this.emailAddress;

	}

	/**
	 * Compare percent correct answers
	 */
	public int compareTo(Contact o) {
		return this.percentCorrect.compareTo(o.percentCorrect);
	}

}
