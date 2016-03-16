package com.javaCore.junit._1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	private static Calculator calculator = new Calculator();
	 
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	public void testAdd() {
		calculator.add(2);
		calculator.add(3);
		assertEquals(5, calculator.getResult());
	}

	@Test
	public void testSubstract() {
		calculator.add(10);
        calculator.substract(2);
        assertEquals(8, calculator.getResult());
	}

	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		fail("Not yet implemented");
	}

	@Test
	public void testSquare() {
		fail("Not yet implemented");
	}

	@Test
	public void testSquareRoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResult() {
		fail("Not yet implemented");
	}

}
