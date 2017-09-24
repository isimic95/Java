package hr.fer.zemris.java.hw04.db;

/**
 * The Interface IFilter has the method accepts. The method takes in a StudentRecord object
 * works on it and if the record is acceptable returns true. Returns false otherwise.
 */
public interface IFilter {
	
	/**
	 * Accepts.
	 *
	 * @param record the record
	 * @return true, if successful. False otherwise.
	 */
	public boolean accepts(StudentRecord record);
}
