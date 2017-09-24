package hr.fer.zemris.java.custom.scripting.elems;

/**
 * The Class ElementOperator represents an operator.
 */
public class ElementOperator extends Element {
	
	/** The symbol of the operator. */
	private String symbol;
	
	
	/**
	 * Instantiates a new element operator.
	 *
	 * @param symbol the symbol
	 */
	public ElementOperator(String symbol) {
		super();
		this.symbol = symbol;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		return symbol;
	}
	
	/**
	 * Gets the operator symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
}
