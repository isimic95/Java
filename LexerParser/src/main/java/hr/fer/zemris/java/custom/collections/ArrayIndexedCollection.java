package hr.fer.zemris.java.custom.collections;

/**
 * Resizable array-backed collection of objects. Provides methods for adding, removing, inserting
 * and getting collection elements among others. Extends Collection class.
 */
public class ArrayIndexedCollection extends Collection {
	
	/** The size of collection. */
	private int size = 0;
	
	/** The capacity of collection. */
	private int capacity;
	
	/** The array of collection elements. */
	private Object[] elements;
	
	/**
	 * Instantiates a new array indexed collection and copies all the elements from collection passed as argument
	 * to the new one. Also takes the initial capacity for the new collection which should preferably be atleast the size of the collection
	 * which was given for copying.
	 * 
	 * @param collectionToCopy Collection which we want to copy into the new collection.
	 * @param initialCapacity Initial capacity of the new collection.
	 */
	public ArrayIndexedCollection(Collection collectionToCopy, int initialCapacity) {
		
		if(initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		capacity = initialCapacity;
		elements = new Object[capacity];
		if(collectionToCopy != null) {
			this.addAll(collectionToCopy);
		}
	}
	
	/**
	 * Instantiates a new array indexed collection.
	 *
	 * @param initialCapacity the initial capacity of the collection.
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		this(null, initialCapacity);
	}
	
	/**
	 * Instantiates a new array indexed collection with capacity of 16.
	 */
	public ArrayIndexedCollection() {
		this(null, 16);
	}
	
	/**
	 * Instantiates a new array indexed collection with initial capacity 16. Copies all the elements from collection passed
	 * as an argument to the new one.
	 *
	 * @param collectionToCopy Collection which we want to copy into the new collection.
	 */
	
	public ArrayIndexedCollection(Collection collectionToCopy) {
		this(collectionToCopy, 16);
	}
	
	/**
	 * Returns the current size of the collection.
	 * 
	 * @return Size of collection.
	 */
	@Override
	public int size() {
		
		return size;
	}
	
	/**
	 * Adds a new object to the collection into first empty place. If the collection 
	 * is full doubles its capacity. Complexity O(n).
	 * 
	 * @param value Object which we want to add into the collection.
	 * @throws IllegalArgumentException if null is passed as an argument
	 */
	@Override
	public void add(Object value) {
		
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < capacity; i++) {
			if(elements[i] == null) {
				elements[i] = value;
				size++;
				break;
			}
			
			if(size == capacity) {
				doubleTheCapacity();
			}
		}
	}
	
	/**
	 * Checks if the collections contains value passed as an argument. Returns true if it does and false otherwise.
	 * Complexity O(n)
	 * 
	 * @return false or true depending on the existence of the value inside the collection.
	 */
	@Override
	public boolean contains(Object value) {
		for(Object element : elements) {
			if(element != null && element.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the first occurrence of value passed as an argument.
	 * 
	 * @param value Object which we want to remove.
	 * @return true if the value was removed, false otherwise.
	 */
	@Override
	public boolean remove(Object value) {
		for(int i=0; i < size; i++) {
			if(elements[i].equals(value)) {
				elements[i] = null;
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the element at specified index from the collection. Element that was previously at location index+1 after this
	 * operation is on location index.
	 *
	 * @param index the index.
	 * @throws IndexOutOfBoundsException if specified index < 0 or > size -1.
	 */
	public void remove(int index) {
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		elements[index] = null;
		
		for(int i=index; i < size-1; i++) {
			elements[i] = elements[i+1];
		}
		size--;
	}
	//napisat javadoc
	public Object[] toArray() {
		return elements;
	}
	/**
	 * Loops through each element of the collection and applies given processor's process to it.
	 */
	@Override
	public void forEach(Processor processor) {
		for(Object element : elements) {
			processor.process(element);
		}
	}
	
	/**
	 * Clears the collection by setting all of its elements to null and its size to 0.
	 */
	@Override
	public void clear() {
		for(int i = 0, len = elements.length; i < len; i++)  {
			elements[i] = null;
		}
			size = 0;
	}
	
	/**
	 * Returns the object that is stored at specified index.
	 *
	 * @param index the index
	 * @return the object at the specified index
	 * @throws IndexOutOfBoundsException if index < 0 or index > size-1
	 */
	public Object get(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		return elements[index];
	}
	
	/**
	 * Inserts the given object at the given position in the collection. If there is already an object at that position 
	 * replaces it and shifts all the elements one place toward the end. Complexity O(1) if the position is empty, O(n) otherwise.
	 *
	 * @param value the value
	 * @param position the position
	 * @throws IndexOutOfBoundsException if given position is out of bounds.
	 * @throws IllegalArgumentException if null is passed as an argument
	 */
	
	public void Insert(Object value, int position) {
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		if(size == capacity) {
			doubleTheCapacity();
		}
		
		if(elements[position] == null) {
			elements[position] = value;
			size++;
			
		} else {
			
			for(int i=size; i > position; i--) {
				elements[i] = elements[i-1];
			}
			elements[position] = value;
			size++;
		}
	}
	
	/**
	 * Returns the index of the first occurrence of the given value. If the value does not exist in the array
	 * returns -1. Average complexity O(n/2).
	 *
	 * @param value The value for which the index is returned.
	 * @return Index of the given value or -1 if the value does not exist.
	 */
	public int indexOf(Object value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		for(int i=0; i < size; i++) {
			if(elements[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}
	
	private void doubleTheCapacity() {
		Object[] elementsDoubleSize = new Object[size*2];
		System.arraycopy(elements, 0, elementsDoubleSize, 0, size);
		elements = elementsDoubleSize;
		capacity = size*2;
	}
	
}
