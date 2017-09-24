package hr.fer.zemris.java.hw04.db;

/**
 * The Class ConditionalExpression represents one conditional expression
 * which consists of a field getter for the field in the expression, 
 * comparison operator and a string literal.
 */
public class ConditionalExpression {
	
	/** The field getter. */
	private IFieldValueGetter fieldGetter;
	
	/** The comparison operator. */
	private IComparisonOperator comparisonOperator;
	
	/** The string literal. */
	private String stringLiteral;
	
	/**
	 * Instantiates a new conditional expression.
	 *
	 * @param getter the getter
	 * @param literal the literal
	 * @param compop the compop
	 */
	public ConditionalExpression(IFieldValueGetter getter, String literal, IComparisonOperator compop) {
		this.fieldGetter = getter;
		this.comparisonOperator = compop;
		this.stringLiteral = literal;
	}

	/**
	 * Gets the field getter.
	 *
	 * @return the field getter
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}

	/**
	 * Gets the comparison operator.
	 *
	 * @return the comparison operator
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	/**
	 * Gets the string literal.
	 *
	 * @return the string literal
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}
	
	
}
