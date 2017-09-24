package hr.fer.zemris.java.custom.collections;

// TODO: Auto-generated Javadoc
/**
 * Class which implements a double linked list collection with operations such as add, remove by index and value, get, contains etc.
 */
public class LinkedListIndexedCollection extends Collection {
	
	/**
	 * Class which represents one node of the list.
	 */
	private static class ListNode {
		
		/** The previous node */
		ListNode prev;
		
		/** The next node. */
		ListNode next;
		
		/** The value. */
		Object value;
	}
	
	/** The size of list. */
	private int size = 0;
	
	/** The first node. */
	private ListNode first;
	
	/** The last node. */
	private ListNode last;
	
	/**
	 * Instantiates a new linked list indexed collection and sets first and last node to null.
	 */
	public LinkedListIndexedCollection() {
		first=last=null;
	}
	
	/**
	 * Instantiates a new linked list indexed collection.
	 *
	 * @param collectionToCopy Copies this collection to the instantiated collection
	 */
	public LinkedListIndexedCollection(Collection collectionToCopy) {
		addAll(collectionToCopy);
		size = collectionToCopy.size();
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#size()
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks if the collection contains the given object. Returns true if it does.
	 *
	 * @param value Object which we are checking.
	 * @return true, if successful
	 * @throws IllegalArgumentException if null is passed as the parameter
	 */
	public boolean contains(Object value) {
		if(value==null) {
			throw new IllegalArgumentException();
		}
		if(first==null) {
			return false;
		}
		ListNode temp = findNodeByValue(value);
		if(temp==null) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#toArray()
	 */
	public Object[] toArray() {
		Object[] array = new Object[size+1];
		
		int counter = 0;
		ListNode temp=first;
		while(temp!=null) {
			array[counter] = temp.value;
			temp=temp.next;
			counter++;
		}
		
		return array;
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#forEach(hr.fer.zemris.java.custom.collections.Processor)
	 */
	public void forEach(Processor processor) {
		ListNode temp = first;
		while(temp!=null) {
			processor.process(temp.value);
			temp=temp.next;
		}
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object value) {
		ListNode temp = findNodeByValue(value);
		if(temp==null) {
			return false;
		}
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		return true;
		
	}
	
	/**
	 * Adds new object to the collection. Average complexity O(1).
	 *
	 * @param value Object which you want to add to the collection
	 * @throws IllegalArgumentException if null is passed as the parameter
	 */
	public void add(Object value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;
		
		if(first==null) {
			first=last=newNode;
			size++;
			return;
		}
		last.next = newNode;
		newNode.prev = last;
		last = newNode;
		size++;
	}
	
	/**
	 * Returns the object at the given index. Average complexity O(n/2).
	 *
	 * @param index the index
	 * @return the object
	 */
	public Object get(int index) {
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		return findNodeByPosition(index).value;
		
		
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	public void clear() {
		first=last=null;
		size=0;
	}
	
	/**
	 * Insert the given value at the given position. Elements at and after the given position are shifted towards the end by 1.
	 * Average complexity O(n/2).
	 *
	 * @param value the value
	 * @param position the position
	 */
	public void Insert(Object value, int position) {
		if(position < 0 || position > size || value==null) {
			throw new IllegalArgumentException();
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;
		
		if(first==null) {
			first=last=newNode;
			size++;
			return;
		}
		
		ListNode temp = findNodeByPosition(position);
		newNode.next = temp.next;
		newNode.prev = temp;
		temp.next.prev = newNode;
		temp.next = newNode;
		size++;
	}
	
	/**
	 * Returns the index of the first occurence of the given value. Average complexity O(n).
	 *
	 * @param value the value
	 * @return index
	 */
	public int indexOf(Object value) {
		if(value==null) {
			throw new IllegalArgumentException();
		}
		
		if(first==null) {
			return -1;
		}
		
		int counter = 0;
		ListNode temp = first;
		while(temp != null) {
			if(temp.value==value) {
				return counter;
			}
			counter++;
			temp = temp.next;
		}
		return -1;
		
	}
	
	/**
	 * Removes the object at the given position.
	 *
	 * @param index the index
	 */
	public void remove(int index) {
		ListNode temp = findNodeByPosition(index);
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
	}
	
	/**
	 * Find node by position.
	 *
	 * @param position the position
	 * @return the list node
	 */
	private ListNode findNodeByPosition(int position) {
		int counter = 0;
		ListNode temp = new ListNode();
		
		if(position > size/2) {
			temp = last;
			position = Math.abs(size-position-1);
			while(counter < position) {
				counter++;
				temp = last.prev;
			}
			return temp;
			
		} else {
			temp = first;
			while(counter < position) {
				counter++;
				temp = first.next;
			}
			return temp;
		}
	}
	
	/**
	 * Find node by value.
	 *
	 * @param value the value
	 * @return the list node
	 */
	private ListNode findNodeByValue(Object value) {
		ListNode temp = first;
		while(temp != null) {
			if(temp.value==value) {
				return temp;
			}
			temp = temp.next;
		}
		return temp;
	}
}
