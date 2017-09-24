package hr.fer.zemris.java.custom.scripting.nodes;

// TODO: Auto-generated Javadoc
/**
 * The Class TextNode represents a text node.
 */
public class TextNode extends Node {
	
	/** The text. */
	private String text;

	/**
	 * Instantiates a new text node.
	 *
	 * @param text the text
	 */
	public TextNode(String text) {
		super();
		this.text = text;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}
	
	
	
	
}
