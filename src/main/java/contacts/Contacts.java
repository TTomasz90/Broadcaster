

/** 
 *  Contacts class
 *  Author: Tomasz Turek
 *	Description: Class designed to manipulate individual contact information. Contains 
 *	functionality of reading and writing contact information to and from the contact.txt file.
 *	As well as searching through individual contact information.
 *
 */

package contacts;

import contacts.Contact;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Contacts {

	
	
	/**
	 * Retrieve contacts from the contacts.txt file.
	 * 
	 * @return ArrayList<Contact> of contact objects.
	 * 
	 */
	public static ArrayList<Contact> getContacts() {

		// initialize an arrayList of contacts.
		ArrayList<Contact> contactList = new ArrayList<Contact>();

		// path to source file.
		
		File inputFile = new File("contacts.txt");

		// attempt to read from the inputFile
		try (BufferedReader buffer = new BufferedReader(new FileReader(inputFile))) {

			String line;

			// iterate over each line in the inputFile
			while ((line = buffer.readLine()) != null) {

				// split each line on "," and store it in a temporary array.
				// will help us with contact creation
				String[] contactString = line.split(",");

				// create new contact object with elements stored in contactString
				// contactSting 0-3 is firstName, lastName, phoneNumber, email
				Contact contact = new Contact(contactString[0], contactString[1], contactString[2], contactString[3]);

				// add contact to contactData
				contactList.add(contact);

			}
			buffer.close();

			// handle exceptions
		} catch (FileNotFoundException line) {
			System.out.println("File not found");

		} catch (IOException line) {
			System.out.println("Something went wrong");

		}catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}

		return contactList;

	}

	/**
	 * Write contacts to the contacts.txt file.
	 * 
	 * @param ArrayList<Contact> containing contacts to be recorded
	 */
	public static void setContacts(ArrayList<Contact> contactList) {

		// path to output file.
		//File outputFile = new File("src/main/java/resources/contacts.txt");
		File outputFile = new File("contacts.txt");

		// attempt to write to outputFile
		try {
			// initialize write buffer
			BufferedWriter buffer = new BufferedWriter(new FileWriter(outputFile));

			// iterate over contacts in the contactList
			for (Contact contact : contactList) {

				// create a new line to be recorded in the outputFile.
				// join each field with a "," character
				String line = String.join(",", contact.firstName, contact.lastName, contact.phoneNumber,
						contact.emailAddress);
				// write to file and add Line Feed to end of each line
				buffer.write(line + "\n");
			}
			buffer.close();
			// handle exceptions
		} catch (FileNotFoundException line) {
			System.out.println("File not found");

		} catch (IOException line) {
			System.out.println("Something went wrong");

		}
	}

	

	/**
	 * Search contactList for matching contacts containing a keyword
	 * @param contactList
	 * @param keyWord
	 * @return ArrayList of matching contacts
	 */
	public static ArrayList<Contact> findContact(ArrayList<Contact> contactList, String keyWord) {

		// list of matching contacts
		ArrayList<Contact> foundContacts = new ArrayList<Contact>();
		
		// compensate for possible lower case first or last name
		String keyWordNameString = keyWord.substring(0,1).toUpperCase() + keyWord.substring(1).toLowerCase();

		// search contactList for contacts containing supplied key word
		for (Contact contact : contactList) {

			if (contact.firstName.equals(keyWordNameString)) {
				
				foundContacts.add(contact);
				
			} else if (contact.lastName.equals(keyWordNameString)) {
				
				foundContacts.add(contact);
				
			} else if (contact.emailAddress.equals(keyWord)) {
				
				foundContacts.add(contact);
				
			} else if (contact.phoneNumber.equals("1" + keyWord)) {
				
				foundContacts.add(contact);
			}

		}

		return foundContacts;
	}

	
	/**
	 * Print all Contacts in the contactList
	 * @param contactList
	 */
	public static void toString(ArrayList<Contact> contactList) {
		int index = 1;
		for (Contact element : contactList) {
			try {
				System.out.println(index + ". " + element.toString());

			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
			index++;

		}

	}

}
