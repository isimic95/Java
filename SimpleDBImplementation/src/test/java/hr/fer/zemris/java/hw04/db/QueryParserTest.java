package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueryParserTest {

	@Test
	public void test() {
		QueryParser qp1 = new QueryParser(" jmbag =\"0123456789\" ");
		assertEquals(true, qp1.isDirectQuery());// true
		assertEquals("0123456789", qp1.getQueriedJMBAG()); // 0123456789
		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\"");
		assertEquals(false, qp2.isDirectQuery()); // false
		assertEquals(2, qp2.getQuery().size()); // 2

	}

}
