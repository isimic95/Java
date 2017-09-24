package hr.fer.zemris.java.custom.collections;

// TODO: Auto-generated Javadoc
/**
 * Class which implements an object stack. You can check if the stack is empty, pop, push and peek an element, get the
 * size of the stack and clear it.
 */
public class ObjectStack {
	
	/** The array. */
	ArrayIndexedCollection array = new ArrayIndexedCollection();
	
	/**
	 * Checks if the stack is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	/**
	 * Returns the size of the stack.
	 *
	 * @return size
	 */
	public int size() {
		return array.size();
	}
	
	/**
	 * Pushes the value on top of the stack.
	 *
	 * @param value the value
	 */
	public void push(Object value) {
		array.Insert(value, array.size());
	}
	
	/**
	 * Removes and returns the object at the top of the stack.
	 *
	 * @return the object
	 */
	public Object pop() {
		Object element = array.get(array.size()-1);
		array.remove(array.size()-1);
		
		return element;
	}
	
	/**
	 * Returns the object at the top of the stack but does not remove it.
	 *
	 * @return the object
	 */
	public Object peek() {
		return array.get(array.size()-1);
	}
	
	/**
	 * Clears the stack.
	 */
	public void clear() {
		array.clear();
	}
}
