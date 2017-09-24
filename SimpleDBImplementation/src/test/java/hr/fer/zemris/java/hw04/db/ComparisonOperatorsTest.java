package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComparisonOperatorsTest {

	@Test
	public void testBasic() {
		assertEquals(true, ComparisonOperators.LESS.satisfied("Ana", "Štefica"));
		assertEquals(false, ComparisonOperators.GREATER.satisfied("Ana", "Štefica"));
		assertEquals(false, ComparisonOperators.GREATER_OR_EQUALS.satisfied("Ana", "Štefica"));
		assertEquals(true, ComparisonOperators.LESS_OR_EQUALS.satisfied("Ana", "Štefica"));
		assertEquals(false, ComparisonOperators.EQUALS.satisfied("Ana", "Štefica"));
		assertEquals(true, ComparisonOperators.NOT_EQUALS.satisfied("Ana", "Štefica"));
	}
	
	@Test
	public void testLike() {
		assertEquals(true, ComparisonOperators.LIKE.satisfied("AAAA", "AA*AA"));
		
		assertEquals(true, ComparisonOperators.LIKE.satisfied("", "*"));
		
		assertEquals(true, ComparisonOperators.LIKE.satisfied("Split", "Split"));
		
		assertEquals(true, ComparisonOperators.LIKE.satisfied("škafiškafnjak", "*fnjak"));
		
		assertEquals(true, ComparisonOperators.LIKE.satisfied("popokatepetl", "popoka*"));
		
		assertEquals(true, ComparisonOperators.LIKE.satisfied("kukulele", "kuku*le"));
	}

}
