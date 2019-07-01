
/** 
 * 
 *	Title: Broadcaster
 *	Author: Tomasz turek
 *	Class: Cs622 project
 *	Description: An application for sending SMS and Email broadcasts 
 *	as well as administering SMS based quizzes.
 *
 */

// Broadcaster imports
// Other libraries
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.json.JSONArray;
import org.json.JSONObject;

import contacts.Contact;
import contacts.Contacts;
import exceptions.MessageLengthException;
import messages.Email;
import messages.Messages;
import messages.Sms;
import quiz.Grade;
import quiz.Quiz;
import threads.GetAnswerCount;
import threads.GetTimeElapsed;

public class Broadcaster {

	/**
	 * Validates message length before attempting to send SMS limit is 1600
	 * characters
	 * 
	 * @param message message to be broadcasted
	 * @throws MessageLengthException user defined message length exceeded exception
	 */
	public static void validateMessageLength(String message) throws MessageLengthException {

		// I SET LENGTH LIMIT TO 180 FOR EASIER DEMONSTRATION PURPOSES.
		if (message.length() > 180) {
			throw new MessageLengthException("Message is to long to send via SMS");
		} else {
			System.out.println("Message validated");
		}

	}

	/**
	 * Validate if email is valid format
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);

		if (email == null)

			return false;

		return pattern.matcher(email).matches();
	}

	/**
	 * Validate if phone number is valid format
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isValidPhoneNumber(String number) {

		String phoneRegexString = "\\d{8,10}";

		// Accepts only 10 digits
		Pattern pattern = Pattern.compile(phoneRegexString);

		if (number == null)

			return false;

		return pattern.matcher(number).matches();
	}

	/**
	 * Log message to the console.
	 * 
	 * @param message (Message) Sms or email object.
	 * 
	 */
	public static void displayMessage(Messages message) {

		// log email or sms to console
		System.out.println(message.toString());

	};

	/**
	 * Send SMS message
	 * 
	 * @param messageBody (String) body of the message
	 * @param phoneNumber (String) phone number of the recipient.
	 * 
	 */
	// IMPLEMENT MESSAGE LENGTH EXCEPTION of 918
	public static void sendSms(String messageBody, String phoneNumber) {

		System.out.println("");

		// check valid message length
		try {
			validateMessageLength(messageBody);
			// create a new SMS message
			Sms smsMessage = new Sms(messageBody);

			// add destination phone number
			smsMessage.setRecipientPhoneNumber(phoneNumber);

			// log action
			System.out.println("SENDING SMS...");

			// send message (polymorphic function)
			smsMessage.sendMessage();

			// upcast sms message to type Message
			Messages message = (Sms) smsMessage;

			// log message to console.
			displayMessage(message);

		} catch (MessageLengthException e) {

			System.out.println(e);
		}

	}

	/**
	 * Send Email message
	 * 
	 * @param emailAddress   (String) body of the message
	 * @param messageSubject (String) phone number of the recipient.
	 * @throws AddressException
	 * @throws MessagingException
	 */

	public static void sendEmail(String emailAddress, String messageSubject, String messageBody)
			throws AddressException, MessagingException {

		System.out.println("");

		try {

			// create a new Email message
			Email emailMessage = new Email(emailAddress, messageSubject, messageBody);

			// upcast Email message to type Message
			Messages message = (Email) emailMessage;

			// log message to console.
			displayMessage(message);

			// send message (polymorphic function)
			emailMessage.sendMessage();

		} catch (AddressException e) {
			System.out.println("Invalid email Address");

		} catch (MessagingException e2) {
			System.out.println("Something went wrong message not sent");

		}

	}

	/**
	 * MAIN
	 */

	public static void main(String[] args) throws Exception {

		// ----------------Variables----------------//

		Quiz currentQuiz = null;

		Double questionCounter = 0.0;

		Boolean quizInProgress = false;

		Boolean running = true;

		ArrayList<Contact> contactList = Contacts.getContacts();

		// References for threads
		GetTimeElapsed elapsedTime = null;

		Thread elapsedTimeThread = null;

		GetAnswerCount answerCount = null;

		Thread answerCounThread = null;

		// check if ObjectFile.dat is empty
		Boolean isEmpty = Quiz.isObjectFileEmpty();

		// If it due to first use write initial values
		Quiz.writeInitial(contactList, isEmpty);

		// ---------------------------------------//

		// handle user input

		Scanner scanner = new Scanner(System.in); // Create a Scanner object

		// Main Event loop
		while (running) {

			// log menu to console.
			System.out.println("\nMenu:\n" + "1. Broadcast SMS\n" + "2. Broadcast Email\n" + "3. Add Contact\n"
					+ "4. Delete Contact\n" + "5. View Contact List\n" + "6. Begin Question\n" + "7. End Question\n"
					+ "8. Current Question Status\n" + "9. Exit");

			String userInput = scanner.nextLine();

			// if user input is 1 (SEND SMS)

			if (userInput.equals("1")) {

				// Read user input
				System.out.println("Your SMS will be broadcasted to all contacts! \n What would you like to say?:");

				String message = scanner.nextLine();

				for (Contact contact : contactList) {

					sendSms(message, contact.phoneNumber);
				}

				// if input is 2 (SEND EMAIL)
			} else if (userInput.equals("2")) {

				// Read user input
				System.out.println("Your Email will be broadcasted to all contacts! \n What would you like to say?:");

				String message = scanner.nextLine();

				System.out.println("Enter your Email subject:");

				String emailSubject = scanner.nextLine();

				for (Contact contact : contactList) {

					sendEmail(contact.emailAddress, emailSubject, message);
				}

				// if input is 3 (ADD NEW CONTACT)
			} else if (userInput.equals("3")) {

				System.out.println("ADD A NEW CONTACT \n");
				// Read user input
				System.out.println("Enter first name:");

				String userName = scanner.nextLine();

				userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

				System.out.println("Enter last name:");

				String userLastName = scanner.nextLine();

				userLastName = userLastName.substring(0, 1).toUpperCase() + userLastName.substring(1).toLowerCase();

				System.out.println("Enter phone number: (ex 6464001111 without the 1)");

				String userPhoneNumber = scanner.nextLine();

				if (!isValidPhoneNumber(userPhoneNumber)) {

					System.out.println("Invalid Phone Number");

					continue;

				}

				userPhoneNumber = "1" + userPhoneNumber;

				System.out.println("Enter email adress:");

				String userEmailAddress = scanner.nextLine();

				Boolean validEmailBoolean = isValidEmail(userEmailAddress);

				if (!validEmailBoolean) {

					System.out.println("Invalid Email Address");

					continue;
				}

				Contact newContact = new Contact(userName, userLastName, userPhoneNumber, userEmailAddress);

				contactList.add(newContact);

				Contacts.setContacts(contactList);

				System.out.println("\nCONTACT ADDED");

				// if input is 4 (REMOVE CONTACT)
			} else if (userInput.equals("4")) {

				System.out.println("REMOVE CONTACT\n");

				System.out.println("Remove Contact: (provide any information to lookup)");

				// Read user input
				String searchInput = scanner.nextLine();

				ArrayList<Contact> foundContacts = Contacts.findContact(contactList, searchInput);

				System.out.println();

				// if multiple matching contacts found
				if (foundContacts.size() > 1) {

					try {
						System.out.println("We found the folowing contacts:\n");

						Contacts.toString(foundContacts);

						System.out.println("\nWhich one would you like to remove (provide number):");

						String inputChoice = scanner.nextLine();

						// search for matching contacts
						Contact chosenContact = foundContacts.get(Integer.parseInt(inputChoice) - 1);

						System.out.println("Is this the contact you want to REMOVE (yes or no)\n");

						System.out.println(chosenContact.toString());

						// confirm removal
						String confirmationInput = scanner.nextLine();

						if (confirmationInput.equals("yes")) {
							contactList.remove(chosenContact);
						} else {
							continue;
						}

					} catch (IndexOutOfBoundsException e) {
						System.out.println("Your choice is Invalid");

					} catch (NumberFormatException e) {
						System.out.println("Your choice is Invalid");

					}

					// if no matching contacts
				} else if (foundContacts.size() == 0) {

					System.out.println("We didnt find any matching contacts");

					continue;

					// if one matching contact found
				} else {
					System.out.println("We found the folowing contact:\n");

					// search for matching contacts
					Contacts.toString(foundContacts);

					System.out.println("\nWould you like to Remove it (yes or no)");

					// confirm removal
					String input = scanner.nextLine();

					if (input.equals("yes")) {

						contactList.remove(foundContacts.get(0));

						Contacts.setContacts(contactList);

						System.out.println("\nCONTACT REMOVED");

					} else {
						continue;
					}

				}

				// if input is 5 (VIEW CONTACTS)

			} else if (userInput.equals("5")) {

				System.out.println("CONTACT LIST\n");

				Contacts.toString(contactList);

				Contacts.setContacts(contactList);

				// if input is 6 (BEGIN QUIZ)

			} else if (userInput.equals("6")) {

				System.out.println("INITIATE A Question \n");

				System.out.println("Input a question you want to ask:");

				// Read user input
				String question = scanner.nextLine();

				System.out.println("Provide expected answer:");

				// Read user input
				String answer = scanner.nextLine();

				// Initialize a new quiz instance
				currentQuiz = new Quiz(question, answer, contactList);

				// flag quiz is in progress
				// prevents multiple quizzes being run at the same time
				quizInProgress = true;

				// request Broadcaster server to send the question to all users
				// in the contact list and wait for replies
				currentQuiz.initializeQuiz("http://broadcaster.ngrok.io/start");

				// start quiz question monitoring
				elapsedTime = new GetTimeElapsed(System.currentTimeMillis());

				// Initialize threads
				elapsedTimeThread = new Thread(elapsedTime);

				answerCount = new GetAnswerCount(contactList.size());

				answerCounThread = new Thread(answerCount);

				// start elapsedTime thread
				elapsedTime.startRunning();

				elapsedTimeThread.start();

				// start answerCount thread
				answerCount.startRunning();

				answerCounThread.start();

				// if input is 7 (END QUIZ AND GRADE RESULTS)
			} else if (userInput.equals("7")) {

				// end quiz option is only available if there is a quiz running
				if (quizInProgress == true) {

					questionCounter += 1.0;

					// end observation threads

					elapsedTime.stopRunning();

					answerCount.stopRunning();

					// get recorded answers from the Broadcaster server
					String resultString = currentQuiz.terminateQuiz("http://broadcaster.ngrok.io/results");

					// convert results to JSON object
					JSONObject jsonObject = new JSONObject(resultString);

					// extract array of results
					JSONArray jsonArray = jsonObject.getJSONArray("results");

					// iterate over array of results
					for (int i = 0; i < jsonArray.length(); i++) {

						// Reference individual object
						JSONObject objectInArray = jsonArray.getJSONObject(i);

						// phone number of the response
						String number = objectInArray.optString("number");

						// provided answer of the response
						String answer = objectInArray.optString("answer");

						// remove "+" from incoming phone number.
						number = number.substring(1);

						// grade the individual response

						currentQuiz.gradeAnswer(answer);

						// record answer statistics for each participating user for active Broadcaster
						// session
						Quiz.recordAnswers(contactList, answer, number, currentQuiz, questionCounter);

					}

					// write student statistics to .dat file

					Quiz.saveSessionRecord(contactList);

					// Display Statistics of the student responses
					System.out.println("QUESTION TERMINATED here are statistics for this question:\n");

					System.out.println("# of correct answers: " + Grade.correctCount);

					System.out.println("# of incorrect answers: " + Grade.incorrectCount);

					System.out.println("Total # of answers: " + Grade.answerCount);

					// flag end of quiz
					quizInProgress = false;

					// rest statistics for a new quiz
					Grade.resetCounters();

				} else {

					System.out.println("You have no active running Questions");

				}

			}
			// if input is 8 (VIEW QUIZ QUESTION PROGRESS)
			else if (userInput.equals("8")) {

				try {

					// toggle visible
					if (elapsedTime.getVsibility() == false) {

						elapsedTime.setVisible();

						answerCount.setVisible();

						// toggle invisible
					} else {

						elapsedTime.setInvisible();

						answerCount.setInvisible();
					}

					// toggle change on input
					while (true) {
						String input = scanner.nextLine();
						if (!(input == null)) {

							elapsedTime.setInvisible();

							answerCount.setInvisible();
						}

						break;
					}

				} catch (NullPointerException e) {
					System.out.println("You must have a quiz running");
				}

			}

			// if input is 9 (TERMINATE APPLICATION)

			else if (userInput.equals("9")) {

				running = false;

				System.out.println("\nthank you for using Broadcaster.");

				scanner.close();

			}

			else {

				System.out.println("Wrong input");

			}

		}

	}

}
