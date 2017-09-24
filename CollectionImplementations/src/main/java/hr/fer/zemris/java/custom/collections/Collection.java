package hr.fer.zemris.java.custom.collections;

/**
 * Generic collection class which defines basic operations with collections. All methods except isEmpty and addAll
 * are empty methods which are implemented in specific collections that inherit this one.
 */
public class Collection {
	
	/**
	 * Instantiates a new collection.
	 */
	protected Collection() {
		
	}
	
	/**
	 * Checks if the collection is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns current size of the collection.
	 *
	 * @return Collection size in integer
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * Adds new object to the collection.
	 *
	 * @param value Object which you want to add to the collection
	 */
	public void add(Object value) {
		
	}
	
	/**
	 * Checks if the collection contains the given object. Returns true if it does.
	 *
	 * @param value Object which we are checking.
	 * @return true, if successful
	 */
	public boolean contains(Object value) {
		return false;
	}
	
	/**
	 * Removes the object from the collection.
	 *
	 * @param value Object which we want to remove
	 * @return true, if successful
	 */
	public boolean remove(Object value) {
		return false;
	}
	
	/**
	 * Returns an array of objects from the collection.
	 *
	 * @return Array of collection objects
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * When called on a collection loops through each of its elements and applies the processor on it.
	 *
	 * @param processor Processor which processes the elements.
	 */
	public void forEach(Processor processor) {
		
	}
	
	/**
	 * Adds all elements from the collection given as an argument to the collection upon which the method is called.
	 *
	 * @param other Collection whose elements we want to add to this collection.
	 */
	public void addAll(Collection other) {
		
		class localProcessor extends Processor {
			
			public void process(Object obj) {
				add(obj);
			}
		}
		
		other.forEach(new localProcessor());
	}
	
	/**
	 * Clears the collection.
	 */
	public void clear() {
		
	}
}
