package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Class ElementConstantDouble represents a single decimal number.
 */
public class ElementConstantDouble extends Element {
	
	/** The value. */
	private double value;
	
	
	/**
	 * Instantiates a new element constant double with the given value.
	 *
	 * @param value the value
	 */
	public ElementConstantDouble(double value) {
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
	public double getValue() {
		return value;
	}
}
