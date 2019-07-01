
/** 
 *  UserStreams class
 *  Author: Tomasz Turek
 *	Description: Class used by the Ranker companion application to filter and print results 
 *	In various ways.
 *
 */

package statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import contacts.Contact;
import javafx.scene.control.TextArea;

public class UserStreams {

	public static Double topScore = 0.0;
	public static Double bottomScore = 100.0;

	/**
	 * Print the results to the screen.
	 * 
	 * @param resultList
	 */
	public static void printStream(List<Contact> resultList, TextArea textArea) {

		List<String> tempList = new ArrayList<String>();

		resultList.stream().forEach((object) -> {
			tempList.add(object.firstName + "\t" + object.lastName + "\t" + " Correct: " + object.numberCorrect + "  "
					+ " Incorrect: " + object.numberInocrrect + "  " + " Answered: " + object.questiosAnswered + "  "
					+ " Questions: " + Math.round(object.questionsAsked) + "  " + " Score: "
					+ Math.round(object.percentCorrect) + "%" + "\n");

		});

		// tempList.forEach((line)->{System.out.println(line);});
		tempList.forEach((line) -> {
			textArea.appendText(line);
		});

	}

	/**
	 * Organize the results by Ascending scores.
	 * 
	 * @param resultList
	 * @return resultList
	 */
	public static List<Contact> sortStreamAscending(ArrayList<Contact> resultList) {

		List<Contact> result = resultList.stream().sorted((o1, o2) -> o1.percentCorrect.compareTo(o2.percentCorrect))
				.collect(Collectors.toList());
		return result;

	}

	/**
	 * Organize the results by Descending scores.
	 * 
	 * @param resultList
	 * @return resultList
	 */
	public static List<Contact> sortStreamDescending(ArrayList<Contact> resultList) {

		List<Contact> result = resultList.stream().sorted((o1, o2) -> o2.percentCorrect.compareTo(o1.percentCorrect))
				.collect(Collectors.toList());
		return result;

	}

	/**
	 * Finds users with the highest score
	 * 
	 * @param resultList
	 * @return resultList
	 */
	public static List<Contact> topPerformers(ArrayList<Contact> resultList) {

		// find the highest score
		Contact max = resultList.stream().max((o1, o2) -> o1.percentCorrect.compareTo(o2.percentCorrect)).get();

		topScore = max.percentCorrect;

		// filter users that dont have the highest score
		List<Contact> result = resultList.stream().filter(contact -> contact.percentCorrect.equals(topScore))
				.collect(Collectors.toList());

		topScore = 0.0;

		return result;

	}

	/**
	 * Finds users with the lowest score.
	 * 
	 * @param resultList
	 * @return resultList
	 */
	public static List<Contact> bottomPerformers(ArrayList<Contact> resultList) {

		// find the lowest score
		Contact min = resultList.stream().max((o1, o2) -> o2.percentCorrect.compareTo(o1.percentCorrect)).get();

		bottomScore = min.percentCorrect;

		// filter users who dont have the lowest score
		List<Contact> result = resultList.stream().filter(contact -> contact.percentCorrect.equals(bottomScore))
				.collect(Collectors.toList());

		bottomScore = 100.0;

		return result;

	}

}
