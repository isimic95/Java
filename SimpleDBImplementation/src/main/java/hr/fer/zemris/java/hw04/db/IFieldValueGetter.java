package hr.fer.zemris.java.hw04.db;

/**
 * The Interface IFieldValueGetter has one method get. The method takes in a StudentRecord object
 * and gets a particular field value from it, depending on the implementation.
 */
public interface IFieldValueGetter {
	
	/**
	 * Gets the record's field value.
	 *
	 * @param record the record
	 * @return the string
	 */
	public String get(StudentRecord record);
}
