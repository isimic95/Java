package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 * The Class ForLoopNode represents a for loop. It consists of a variable
 * start expression, end expression and step expression which is optional and can be null.
 * For example in {$FOR var -1 15 3 $} variable is var, -1 is start, 15 is end
 * and 3 is the step
 */
public class ForLoopNode extends Node {
	
	/** The variable. */
	private ElementVariable variable;
	
	/** The start expression. */
	private Element startExpression;
	
	/** The end expression. */
	private Element endExpression;
	
	/** The step expression. */
	private Element stepExpression;
	
	/**
	 * Instantiates a new for loop node.
	 *
	 * @param variable the variable
	 * @param startExpression the start expression
	 * @param endExpression the end expression
	 * @param stepExpression the step expression
	 */
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression,
			Element stepExpression) {
		super();
		
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	}

	/**
	 * Gets the variable.
	 *
	 * @return the variable
	 */
	public ElementVariable getVariable() {
		return variable;
	}

	/**
	 * Gets the start expression.
	 *
	 * @return the start expression
	 */
	public Element getStartExpression() {
		return startExpression;
	}

	/**
	 * Gets the end expression.
	 *
	 * @return the end expression
	 */
	public Element getEndExpression() {
		return endExpression;
	}

	/**
	 * Gets the step expression.
	 *
	 * @return the step expression
	 */
	public Element getStepExpression() {
		return stepExpression;
	}

	@Override
	public String toString() {
		return "{$FOR " + variable + " " + startExpression + " " + endExpression + " " + stepExpression + "$}";
	}
	
	
}
