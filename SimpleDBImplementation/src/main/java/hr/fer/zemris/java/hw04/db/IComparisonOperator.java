package hr.fer.zemris.java.hw04.db;

/**
 * The Interface IComparisonOperator represents a comparison operator interface implemented
 * through the satisfied method. The method takes in two String values, works on them, and if
 * the values satisfy the implementation the method returns true, otherwise it returns false.
 */
public interface IComparisonOperator {
	
	/**
	 * Satisfied.
	 *
	 * @param value1 the value 1
	 * @param value2 the value 2
	 * @return true, if successful. False otherwise
	 */
	public boolean satisfied(String value1, String value2);
}
