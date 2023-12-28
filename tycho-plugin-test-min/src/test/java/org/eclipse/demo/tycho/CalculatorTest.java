package org.eclipse.demo.tycho;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.demo.tycho.Calculator;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	@Test
	public void testAddingTwoNumbers() {
		Calculator calc = new Calculator();
		assertEquals(4, calc.addTwoPositiveNumbers(1, 3));
	}
}
