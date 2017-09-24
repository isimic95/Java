package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class ConditionalExpressionTest {

	@Test
	public void test() throws FileNotFoundException {
		ConditionalExpression expr = new ConditionalExpression(
				 FieldValueGetters.LAST_NAME,
				 "Dvor*ić",
				 ComparisonOperators.LIKE
				);
				StudentRecord record = LoadDB.getDB().forJMBAG("0000000011");
				boolean recordSatisfies = expr.getComparisonOperator().satisfied(
				 expr.getFieldGetter().get(record), // returns lastName from given record
				 expr.getStringLiteral() // returns "Bos*"
				);
				
				assertEquals(true, recordSatisfies);

	}

}
