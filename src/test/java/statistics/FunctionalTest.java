
/** 
 *  FunctionalTest class
 *  Author: Tomasz Turek
 *	Description: Test correct output of User defined lambda Expressions.
 *
 */

package statistics;

import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

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

	/**
	 * Test User defined lambda expression Adding two integers
	 */
	@Test
	public void addTest() {

		// given a two elements
		Integer a = 10;

		Integer b = 2;

		// when adding two integers expected result should be 12.
		Integer expected = 12;

		// then perform operation
		Integer actual = addInt.operation(a, b);

		Assert.assertEquals(expected, actual);

	}

	/**
	 * Test User defined lambda expression Subtracting two integers
	 */
	@Test
	public void sbtractTest() {

		// given a two elements
		Integer a = 10;

		Integer b = 2;

		// when subtracting two integers expected result should be 8.
		Integer expected = 8;

		// then perform operation
		Integer actual = subtract.operation(a, b);

		Assert.assertEquals(expected, actual);

	}

	/**
	 * Test User defined lambda expression calculating percentage
	 * 
	 */
	@Test
	public void percentageTest() {

		// given a two elements

		Double a = 5.0;
		Double b = 10.0;

		// when calculating percentage a of b result should be 50.0.

		Double expected = 50.0;

		// then perform operation
		Double actual = percentage.operation(a, b);

		Assert.assertEquals(expected, actual);

	}

}
