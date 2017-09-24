package hr.fer.zemris.java.hw04.db;

/**
 * The Class DatabaseToken represents a token produced by QueryLexer.
 */
public class DatabaseToken {
	
	/** The type. */
	private DatabaseTokenType type;
	
	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new database token.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public DatabaseToken(DatabaseTokenType type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public DatabaseTokenType getType() {
		return type;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
}
