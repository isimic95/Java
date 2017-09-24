package hr.fer.zemris.java.custom.scripting.elems;

/**
 * The Class ElementString represents a single string.
 */
public class ElementString extends Element {
	
	/** The value of the string. */
	private String value;
	
	
	/**
	 * Instantiates a new element string.
	 *
	 * @param value the value
	 */
	public ElementString(String value) {
		super();
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		return '"'+value+'"';
	}
	
	/**
	 * Gets the string.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return '"'+value+'"';
	}
	 
}
