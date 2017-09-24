package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Class Element represents a general single expression.
 * All other elements which represent more specific expression
 * inherit this class.
 */
public class Element {
	
	/**
	 * Element's value returned as a string. Here it returns
	 * an empty string.
	 *
	 * @return the string
	 */
	public String asText() {
		return new String();
	}
}
