package hr.fer.zemris.java.hw01;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void proizvoljniSlucaj() {
		long n = Factorial.calculateFactorial(6);
		assertEquals(720, n);
	}
	@Test
	public void donjiGranicniSlucaj() {
		long n = Factorial.calculateFactorial(1);
		assertEquals(1, n);
	}
	
	@Test
	public void gornjiGranicniSlucaj() {
		long n = Factorial.calculateFactorial(20);
		assertEquals(2432902008176640000L, n);
	}

}
