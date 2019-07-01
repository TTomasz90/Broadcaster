
/**
 * Quiz Class
 * Author: Tomasz Turek
 * Description: Class responsible for operations of the quiz functionality
 * 
 */

package quiz;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import contacts.Contact;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import statistics.Functional;

public class Quiz {

	// Quiz parameters
	private String quizQuestion;

	private String quizSolution;

	private ArrayList<Contact> contactList;

	// Constructor
	public Quiz(String question, String answer, ArrayList<Contact> contactList) {

		this.quizQuestion = question;

		this.quizSolution = answer;

		this.contactList = contactList;
	}

	/**
	 * Sends an Http request to the server to initiate the quiz. Sends a list of
	 * contact numbers along with the quiz question.
	 * 
	 * @param url
	 * @throws IOException
	 */
	public void initializeQuiz(String url) throws IOException {

		String dataString = url + "?message=" + URLEncoder.encode(this.quizQuestion, "UTF-8") + "&numbers=";

		for (Contact contact : contactList) {

			dataString += contact.phoneNumber + "/";
		}

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(dataString).build();

		try (Response response = client.newCall(request).execute()) {

			System.out.println(response.body().string());

		}

	}

	/**
	 * Sends an Http request to the server to terminate the quiz. Sends a request
	 * for the results submitted by the quiz participants.
	 * 
	 * @param url
	 * @return response (JSON)
	 * @throws IOException
	 */

	public String terminateQuiz(String url) throws IOException {

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {

			return response.body().string();
		}

	}

	/**
	 * Checks if the input string is numeric.
	 * 
	 * @param string
	 * @return boolean
	 */
	private static boolean isNumeric(String string) {

		NumberFormat formatter = NumberFormat.getInstance();

		ParsePosition pos = new ParsePosition(0);

		formatter.parse(string, pos);

		return string.length() == pos.getIndex();
	}

	/**
	 * Method computes the grades for each participating student and recalculates
	 * their progress each turn of the quiz.
	 * 
	 * @param contactList
	 * @param answer
	 * @param number
	 * @param currentQuiz
	 * @param questionCounter
	 */
	public static void recordAnswers(ArrayList<Contact> contactList, String answer, String number, Quiz currentQuiz,
			Double questionCounter) {

		// User defined lambda operations
		Functional<Integer> addInt = (a, b) -> {
			return (a + b);
		};

		Functional<Integer> subtract = (a, b) -> {
			return (a - b);
		};

		Functional<Double> percentage = (a, b) -> {
			return ((a / b) * 100);
		};

		// record Question counter
		for (Contact contact : contactList) {

			contact.questionsAsked = questionCounter;

		}

		// calculate contact quiz progress
		for (Contact contact : contactList) {

			// if contact answered the question
			if (contact.phoneNumber.equals(number)) {

				Boolean isCorrectBoolean = currentQuiz.peekCorrectAnswer(answer);

				if (isCorrectBoolean) {

					// Increment correct count
					contact.numberCorrect = addInt.operation(contact.numberCorrect, 1);

					// Increment questions answered count
					contact.questiosAnswered = addInt.operation(contact.questiosAnswered, 1);

				} else {

					// Increment incorrect count
					contact.numberInocrrect = addInt.operation(contact.numberInocrrect, 1);

					// Increment questions answered count
					contact.questiosAnswered = addInt.operation(contact.questiosAnswered, 1);
				}

			}

			// calculate percentage of correct answers
			Double percentCorrect = percentage.operation((double) contact.numberCorrect, questionCounter);

			contact.percentCorrect = percentCorrect;

		}

	}

	/**
	 * Initiates the grading process of received Quiz responses
	 * 
	 * @param submitedAnswer expected answer to the quiz
	 */
	public void gradeAnswer(String submitedAnswer) {

		boolean isnum = isNumeric(this.quizSolution);

		// if answer is numeric
		if (isnum == true) {

			// check that types match before constructing a Grade object
			try {
				double expectedAnswer = Double.parseDouble(this.quizSolution);

				double studentAnswer = Double.parseDouble(submitedAnswer);

				Grade<Double> grade = new Grade<Double>(expectedAnswer, studentAnswer);

				grade.checkAnswer();

			} catch (NumberFormatException exception) {

				Grade.incorrectCount += 1;

				Grade.answerCount += 1;
			}

			// if answer is a String
		} else {

			String expectedAnswer = this.quizSolution;

			String studentAnswer = submitedAnswer;

			Grade<String> test2 = new Grade<String>(expectedAnswer, studentAnswer);

			test2.checkAnswer();

		}

	}

	/**
	 * Initiates the grading process of received Quiz responses without altering
	 * Static variables
	 * 
	 * @param submitedAnswer
	 * @return boolean
	 */
	public Boolean peekCorrectAnswer(String submitedAnswer) {

		boolean isnum = isNumeric(this.quizSolution);

		boolean answer = false;

		// if answer is numeric
		if (isnum == true) {

			// check that types match before constructing a Grade object
			try {
				double expectedAnswer = Double.parseDouble(this.quizSolution);

				double studentAnswer = Double.parseDouble(submitedAnswer);

				Grade<Double> grade = new Grade<Double>(expectedAnswer, studentAnswer);

				boolean res = grade.peekAnswer();

				answer = res;

			} catch (NumberFormatException exception) {

			}

			// if answer is a String
		} else {

			String expectedAnswer = this.quizSolution;

			String studentAnswer = submitedAnswer;

			Grade<String> test2 = new Grade<String>(expectedAnswer, studentAnswer);

			boolean res = test2.peekAnswer();

			answer = res;

		}
		return answer;

	}

	/**
	 * Writes changes of each participants progress in the objecFile.dat after every
	 * quiz question asked and graded.
	 * 
	 * @param contactList
	 */
	public static void saveSessionRecord(ArrayList<Contact> contactList) {

		// initialize output stream to objectFile.dat
		try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("objectFile.dat"));) {

			// write objects to file
			contactList.forEach((contact) -> {

				try {
					// write to file
					outfile.writeObject(contact);

				} catch (IOException e) {

					System.out.println(e);
				}
			});

		} catch (FileNotFoundException e) {

			System.out.println(e);

		} catch (IOException e) {

			System.out.println(e);

		} catch (Exception e) {

			System.out.println(e);
		}

	}

	/**
	 * Retrieves quiz record from objectFile.dat
	 * 
	 * @return contact List
	 */
	public static ArrayList<Contact> retreveSessionRecord() {

		// initialize temporary contactList
		ArrayList<Contact> conactList = new ArrayList<Contact>();

		// initialize input stream from objectFile.dat
		try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream("objectFile.dat"));) {

			// Retrieve each object and store in contactList
			while (true) {

				Contact c = (Contact) infile.readObject();

				conactList.add(c);

			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {

			System.out.println(e);

		} catch (IOException e) {

			System.out.println(e);

		} catch (Exception e) {

			System.out.println(e);
		}
		return conactList;

	}

	/**
	 * Calculates the Grade id based on supplied score
	 * 
	 * @param score
	 * @return
	 */
	private static Integer calculateLetterGrade(Double score) {

		if (score >= 95) {
			return 8;
		} else if (score >= 90 && score < 95) {
			return 7;
		} else if (score >= 85 && score < 90) {
			return 6;
		} else if (score >= 80 && score < 85) {
			return 5;
		} else if (score >= 75 && score < 80) {
			return 4;
		} else if (score >= 70 && score < 75) {
			return 3;
		} else if (score >= 65 && score < 70) {
			return 2;
		} else if (score >= 60 && score < 65) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Updates the contact list with correct grade ID after every quiz question.
	 * 
	 * @param rankList
	 * @return
	 */
	public static ArrayList<Contact> assignGradeDesignators(ArrayList<Contact> rankList) {

		for (Contact contact : rankList) {

			Integer designator = calculateLetterGrade(contact.percentCorrect);

			contact.designatedGrade = designator;

		}

		return rankList;

	}

	/// UNDER CONSTRUCTION UNUSED

	public static void writeInitial(ArrayList<Contact> contactList, Boolean isEmpty) {
		if (isEmpty == true) {

			try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("objectFile.dat"))) {

				for (Contact c : contactList) {

					outfile.writeObject(c);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public static boolean isObjectFileEmpty() throws FileNotFoundException {

		BufferedReader input = new BufferedReader(new FileReader("objectFile.dat"));
		try {
			if (input.readLine() == null) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return false;

	}

}
