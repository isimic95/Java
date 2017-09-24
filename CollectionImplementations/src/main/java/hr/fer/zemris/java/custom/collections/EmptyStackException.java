package hr.fer.zemris.java.custom.collections;

// TODO: Auto-generated Javadoc
/**
 * Custom EmptyStackException which is thrown when you try to pop empty stack.
 */
public class EmptyStackException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new empty stack exception.
	 *
	 * @param message the message which describes the exception
	 */
	public EmptyStackException(String message) {
		super(message);
	}
}
