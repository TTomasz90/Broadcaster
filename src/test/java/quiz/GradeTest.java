
/** 
 *  GradeTest class
 *  Author: Tomasz Turek
 *	Description: Tests designed to test the Grading capability of the application.
 *
 */

package quiz;

import org.junit.Assert;
import org.junit.Test;

public class GradeTest {

	/**
	 * Test when two matching strings are the input( Not case sensitive)
	 */
	@Test
	public void genericGradeTestString() {

		String s1 = "Mountain";
		String s2 = "mountain";

		Grade<String> testStrin = new Grade<String>(s1, s2);

		Boolean testStringAnswer = testStrin.checkAnswer();

		System.out.println("|| input 1: " + s1 + " || input 2: " + s2 + " || expected output: true" + " || actual output: "
				+ testStringAnswer);

		Assert.assertEquals(testStringAnswer, true);

	}

	/**
	 * Test when two different strings are the input (Not case sensitive)
	 */

	@Test
	public void genericGradeTestStringFail() {

		String s1 = "Mountain";
		String s2 = "Alaska";

		Grade<String> testStrin = new Grade<String>(s1, s2);

		Boolean testStringAnswer = testStrin.checkAnswer();

		System.out.println("|| input 1: " + s1 + " || input 2: " + s2 + " || expected output: false" + " || actual output: "
				+ testStringAnswer);

		Assert.assertEquals(testStringAnswer, false);

	}

	/**
	 * Test when two matching integers are the input
	 */

	@Test
	public void genericGradeTestInteger() {

		Integer i1 = 21;
		Integer i2 = 21;

		Grade<Integer> testInt = new Grade<Integer>(i1, i2);

		Boolean testIntegerAnswer = testInt.checkAnswer();

		System.out.println("|| input 1: " + i1 + " || input 2: " + i2 + " || expected output: true" + " || actual output: "
				+ testIntegerAnswer);

		Assert.assertEquals(testIntegerAnswer, true);

	}

	/**
	 * Test when two different integers are the input
	 */

	@Test
	public void genericGradeTestIntegerFail() {

		Integer i1 = 21;
		Integer i2 = 3232;

		Grade<Integer> testInt = new Grade<Integer>(i1, i2);

		Boolean testIntegerAnswer = testInt.checkAnswer();

		System.out.println("|| input 1: " + i1 + " || input 2: " + i2 + " || expected output: false" + " || actual output: "
				+ testIntegerAnswer);

		Assert.assertEquals(testIntegerAnswer, false);

	}

	/**
	 * Test when two matching doubles are the input
	 */

	@Test
	public void genericGradeTestDouble() {

		Double d1 = 2.1;
		Double d2 = 2.1;

		Grade<Double> testDouble = new Grade<Double>(d1, d2);

		Boolean testDoubleAnswer = testDouble.checkAnswer();

		System.out.println("|| input 1: " + d1 + " || input 2: " + d2 + " || expected output: true" + " || actual output: "
				+ testDoubleAnswer);

		Assert.assertEquals(testDoubleAnswer, true);

	}

	/**
	 * Test when two different doubles are the input
	 */

	@Test
	public void genericGradeTestDoubleFail() {

		Double d1 = 2.1;
		Double d2 = 3.2;

		Grade<Double> testDouble = new Grade<Double>(d1, d2);

		Boolean testDoubleAnswer = testDouble.checkAnswer();

		System.out.println("|| input 1: " + d1 + " || input 2: " + d2 + " || expected output: false" + " || actual output: "
				+ testDoubleAnswer);

		Assert.assertEquals(testDoubleAnswer, false);

	}

}
