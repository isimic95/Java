package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Class ElementConstantInteger represents a single integer number.
 */
public class ElementConstantInteger extends Element {
	
	/** The value. */
	private int value;
	
	
	/**
	 * Instantiates a new element constant integer.
	 *
	 * @param value the value
	 */
	public ElementConstantInteger(int value) {
		super();
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		return String.valueOf(value);
	}
	
	/**
	 * Gets the value of the number which the element is representing.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
