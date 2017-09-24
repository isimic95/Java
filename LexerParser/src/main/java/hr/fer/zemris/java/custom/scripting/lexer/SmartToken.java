package hr.fer.zemris.java.custom.scripting.lexer;

// TODO: Auto-generated Javadoc
/**
 * The Class SmartToken represents a single token produced by SmartLexer.
 */
public class SmartToken {
	
	/** The type. */
	private SmartTokenType type;
	
	/** The value. */
	private Object value;
	
	/**
	 * Instantiates a new smart token.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public SmartToken(SmartTokenType type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public SmartTokenType getType() {
		return type;
	}
}
