package hr.fer.zemris.java.hw04.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Class SimpleHashtable represents a hashtable with keys K and values V. You can get and remove objects by key, 
 * check if the table contains value or key and iterate over the table. If two items fall into the
 * same "box" they are linked into a list one after another.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K,V>> {
	
	/** The size. */
	private int size;
	
	/** The modification count. */
	private int modificationCount;
	
	/** The table. */
	TableEntry<K, V>[] table;
	
	/**
	 * Instantiates a new simple hashtable with size 16.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable() {
		size = 0;
		
		table = new TableEntry[16];
	}
	
	/**
	 * Instantiates a table
	 * of size closest to the one given that is a power of 2.
	 *
	 * @param capacity the capacity
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if(capacity < 1) {
			throw new IllegalArgumentException("Size can not be lower than 1.");
		}
		size=0;
		int pom = 2;
		while(pom < capacity) {
			pom *= 2;
		}
		
		table = new TableEntry[pom];
	}
	
	/**
	 * Put the key value pair into the table.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void put(K key, V value) {
		if(key == null) {
			throw new IllegalArgumentException("Key can not be null.");
		}
		TableEntry<K, V> entry = new TableEntry<>(key, value, null);
		int slot = key.hashCode() % table.length;
		if(table[slot] == null) {
			table[slot] = entry;
			size++;
			modificationCount++;
			if(size > table.length*0.75) resize();
			return;
		}
		
		TableEntry<K, V> pom = table[slot];
		while(pom!=null) {
			if(pom.getKey().equals(key)) {
				pom.setValue(value);
			}
			
			if(pom.next==null) {
				pom.next = entry;
				size++;
				modificationCount++;
				if(size > table.length*0.75) resize();
				break;
			}
			pom = pom.next;
		}
	}
	
	/**
	 * Gets the entry by the key.
	 *
	 * @param key the key
	 * @return the v
	 */
	//odvojit šetanje po listi u zasebnu metodu
	public V get(Object key) {
		int slot = key.hashCode() % table.length;
		
		TableEntry<K, V> pom = table[slot];
		while(pom!=null) {
			if(pom.getKey().equals(key)) return pom.getValue();
			pom = pom.next;
		}
		
		return null;	
	}
	
	/**
	 * Size of the table, counts all entries.
	 *
	 * @return the int
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Contains key.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean containsKey(Object key) {
		if(key==null) return false;
		int slot = key.hashCode() % table.length;
		
		TableEntry<K, V> pom = table[slot];
		while(pom!=null) {
			if(pom.getKey().equals(key)) return true;
		}
		return false;
	}
	
	/**
	 * Contains value.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean containsValue(Object value) {
		for(TableEntry<K,V> entry : table) {
			while(entry!=null) {
				if(entry.getValue().equals(value)) return true;
				entry = entry.next;
			}
		}
		return false;
	}
	
	/**
	 * Removes the object with the specified key.
	 *
	 * @param key the key
	 */
	public void remove(Object key) {
		if(!containsKey(key)) return;
		
		int slot = key.hashCode() % table.length;
		
		if(table[slot].getKey().equals(key)) {
			table[slot] = null;
			size--;
			modificationCount++;
			return;
		}
		
		TableEntry<K, V> pom = table[slot];
		while(pom!=null) {
			if(pom.next.getKey().equals(key)) {
				pom.next = pom.next.next;
				size--;
				modificationCount++;
			}
			pom = pom.next;
		}
	}
	
	/**
	 * Checks if the table is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if(size==0) return true;
		return false;
	}
	
	/**
	 * Clears the table.
	 */
	public void clear() {
		for(TableEntry<K, V> entry : table) {
			entry = null;
			size = 0;
		}
	}
	
	/**
	 * Resize the table.
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		TableEntry<K, V>[] tab = new TableEntry[table.length*2];
		TableEntry<K, V>[] tablecp = table;
		table = tab;
		for(TableEntry<K, V> entry : tablecp) {
			while(entry!=null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(TableEntry<K,V> entry : table) {
			while(entry!=null) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
				entry = entry.next;
			}
		}
		sb.append("]");
		return sb.toString();
	}


	/**
	 * The Class TableEntry.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 */
	public static class TableEntry<K,V> {
		
		/** The key. */
		private K key;
		
		/** The value. */
		private V value;
		
		/** The next. */
		private TableEntry<K,V> next;
		
		/**
		 * Instantiates a new table entry.
		 *
		 * @param key the key
		 * @param value the value
		 * @param next the next
		 */
		public TableEntry(K key, V value, TableEntry<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * Gets the key.
		 *
		 * @return the key
		 */
		public K getKey() {
			return key;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<SimpleHashtable.TableEntry<K,V>> iterator() {
		return new IteratorImpl();
	}
	
	/**
	 * The Class IteratorImpl.
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
		
		/** The current entry. */
		TableEntry<K,V> currentEntry;
		
		/** The count. */
		int count;
		
		/** The current slot. */
		int currentSlot;
		
		/** The initial size. */
		int initialSize;
		
		/** The initial modification count. */
		int initialModificationCount;
		
		/** The next flag. */
		boolean nextFlag;
		
		/**
		 * Instantiates a new iterator impl.
		 */
		public IteratorImpl() {
			currentSlot=0;
			initialSize = size;
			count = size;
			initialModificationCount = modificationCount;
			while(table[currentSlot]==null) currentSlot++;
			currentEntry = table[currentSlot];
			nextFlag = false;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			if(modificationCount > initialModificationCount) throw new ConcurrentModificationException();
			if(count > 0) return true;
			
			return false;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public SimpleHashtable.TableEntry<K,V> next() {
			if(modificationCount > initialModificationCount) throw new ConcurrentModificationException();
			if(count == initialSize) {
				nextFlag = true;
				return currentEntry;
			}
			
			if(currentEntry.next!=null) {
				currentEntry = currentEntry.next;
				count--;
				nextFlag = true;
				return currentEntry;
			} else {
				do {
					currentSlot++;
					if(currentSlot >= table.length-1) {
						throw new NoSuchElementException("There are no more elements in this collection!");
					}
					
				} while(table[currentSlot]==null);
				
				currentEntry = table[currentSlot];
				count--;
				nextFlag = true;
				return currentEntry;
			}
			
		}
	
		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			if(modificationCount > initialModificationCount) throw new ConcurrentModificationException(
					"Collections must not be changed except by the iterator while being iterated!");
			
			if(!nextFlag) throw new IllegalStateException("Method remove can only be called once after method next");
			
			SimpleHashtable.this.remove(currentEntry.getKey());
			initialModificationCount++;
			nextFlag=false;
		}
		}
}
