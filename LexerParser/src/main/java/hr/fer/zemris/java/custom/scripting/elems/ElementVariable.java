package hr.fer.zemris.java.custom.scripting.elems;

/**
 * The Class ElementVariable represents a single variable.
 */
public class ElementVariable extends Element {
	
	/** The name. */
	private String name;
	
	
	/**
	 * Instantiates a new element variable.
	 *
	 * @param name the name
	 */
	public ElementVariable(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		return name;
	}
	
	/**
	 * Gets the name of the variable.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
