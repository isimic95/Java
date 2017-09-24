package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class EchoNode represents the echo command. It consists of a list
 * of echo tag elements. For example in {$= i i *$} elements are two ElementVariables
 * "i" and one ElementOperator "*"
 */
public class EchoNode extends Node {
	
	/** The elements. */
	private Element[] elements;

	/**
	 * Instantiates a new echo node.
	 *
	 * @param elements the elements
	 */
	public EchoNode(Element[] elements) {
		super();
		this.elements = elements;
	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public Element[] getElements() {
		return elements;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{$=");
		for(int i=0; i < elements.length; i++) {
			sb.append(" "+elements[i].asText());
		}
		sb.append(" $}");
		return sb.toString();
	}
	
	
}
