package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class Node represents one structure inside of a document. It consists
 * of its elements. All other nodes inherit this node.
 */
public class Node {
	
	/** The array for the children of the node. */
	ArrayIndexedCollection array;
	
	/**
	 * Adds the child node.
	 *
	 * @param child the child
	 */
	public void addChildNode(Node child) {
		if(array==null) {
			array = new ArrayIndexedCollection();
		}
		array.add(child);
	}
	
	/**
	 * Number of children.
	 *
	 * @return the int
	 */
	public int numberOfChildren() {
		return array.size();
	}
	
	/**
	 * Gets the child at the specified index.
	 *
	 * @param index the index
	 * @return the child
	 */
	public Node getChild(int index) {
		if(index < 0 || index >= array.size()) {
			throw new IllegalArgumentException("Index must be positive and less than collections size!");
		}
		
		return (Node)array.get(index);
	}
}
