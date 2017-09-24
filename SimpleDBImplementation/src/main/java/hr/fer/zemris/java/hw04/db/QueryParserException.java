package hr.fer.zemris.java.hw04.db;

/**
 * The Class QueryParserException is an exception which is thrown by QueryParser when 
 * query is invalid.
 */
public class QueryParserException extends RuntimeException {
	
	/**
	 * Instantiates a new query parser exception.
	 *
	 * @param message the message
	 */
	public QueryParserException(String message) {
		super(message);
	}
}
