package hr.fer.zemris.java.custom.scripting.elems;

/**
 * The Class ElementFunction represents a single function.
 */
public class ElementFunction extends Element {
	
	/** The name of the function. */
	private String name;
	
	
	/**
	 * Instantiates a new element function.
	 *
	 * @param name the name
	 */
	public ElementFunction(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		return "@"+name;
	}
	
	/**
	 * Gets the name of the function.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
}
