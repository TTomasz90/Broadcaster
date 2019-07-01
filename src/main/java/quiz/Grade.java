/**
 * Grade Class
 * Author: Tomasz Turek
 * Description: Grade is a generic class responsible for grading student
 *              responses Regardless of the data type of the student answer.
 */

package quiz;

public class Grade<T> {

	// student answer statistics
	public static int incorrectCount = 0;
	public static int correctCount = 0;
	public static int answerCount = 0;

	// generic answer types
	private T expectedAnswer;
	private T studentAnswer;

	// constructor
	public Grade(T expectedAnswer, T studentAnswer) {
		this.expectedAnswer = expectedAnswer;
		this.studentAnswer = studentAnswer;

	}

	/**
	 * Reset class counters.
	 */
	public static void resetCounters() {
		incorrectCount = 0;
		correctCount = 0;
		answerCount = 0;
	}

	/**
	 * generic method for checking if student answer is correct
	 * 
	 * @return Boolean
	 */
	public Boolean checkAnswer() {

		// if type string ignore case sensitivity
		if (this.expectedAnswer.getClass().getName() == "java.lang.String") {

			if (((String) (this.expectedAnswer)).equalsIgnoreCase(((String) (this.studentAnswer)))) {
				correctCount += 1;
				answerCount += 1;
				return true;
			} else {
				incorrectCount += 1;
				answerCount += 1;
				return false;
			}
			// all other primitive types
		} else {

			if (this.expectedAnswer.equals(this.studentAnswer)) {
				correctCount += 1;
				answerCount += 1;
				return true;
			} else {

				incorrectCount += 1;
				answerCount += 1;
				return false;
			}
		}
	}

	/**
	 * generic method for peeking if student answer is correct
	 * 
	 * @return Boolean
	 */
	public Boolean peekAnswer() {

		// if type string ignore case sensitivity
		if (this.expectedAnswer.getClass().getName() == "java.lang.String") {

			if (((String) (this.expectedAnswer)).equalsIgnoreCase(((String) (this.studentAnswer)))) {
				return true;
			} else {

				return false;
			}
			// all other primitive types
		} else {

			if (this.expectedAnswer.equals(this.studentAnswer)) {

				return true;
			} else {

				return false;
			}
		}
	}

}