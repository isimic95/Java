package hr.fer.zemris.java.custom.collections;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayIndexedCollectionTest {
	
	@Test
	public void testSize() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection();
		array1.add(56);
		array1.add("Petar");
		array1.add("Pas");
		array1.add(111);
		assertEquals(4, array1.size());
	}

	@Test
	public void testAddInt() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection();
		array1.add(56);
		assertEquals(true,array1.contains(56));
		
	}
	
	@Test
	public void testAddString() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection();
		array1.add("Makarska");
		assertEquals(true,array1.contains("Makarska"));
		
	}
	
	@Test
	public void testAddDoubling() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(56);
		array1.add(31);
		array1.add(23);
		assertEquals(4, array1.size());
		
	}

	@Test
	public void testContains() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(31);
		assertEquals(true, array1.contains(56));
		assertEquals(true, array1.contains(31));
		assertEquals(false, array1.contains(1));
	}
	

	@Test
	public void testRemoveObject() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add("Zvonko");
		
		assertEquals(true, array1.remove("Zvonko"));
		assertEquals(1, array1.size());
		assertEquals(false, array1.remove("Zadro"));
		assertEquals(true, array1.remove(Integer.valueOf(56)));
	}

	@Test
	public void testForEach() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(6);
		array1.add("Zvonko");
		
		class localProcessor extends Processor {
			
			public void process(Object obj) {
				if (obj instanceof Integer) {
					array1.add(obj);
				}
			}
		}
		
		array1.forEach(new localProcessor());
		assertEquals(56, array1.get(3));
		assertEquals(6, array1.get(4));
	}

	@Test
	public void testClear() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(31);
		
		array1.clear();
		assertEquals(0, array1.size());
	}

	@Test
	public void testArrayIndexedCollectionCollectionInt() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(31);
		array1.add(22);
		ArrayIndexedCollection array2 = new ArrayIndexedCollection(array1, 8);
		assertEquals(31, array2.get(1));
	}

	@Test
	public void testArrayIndexedCollectionCollection() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(3);
		array1.add(56);
		array1.add(31);
		array1.add(22);
		
		ArrayIndexedCollection array2 = new ArrayIndexedCollection(array1);
		assertEquals(22, array2.get(2));
		assertEquals(31, array2.get(1));
	}

	@Test
	public void testRemoveInt() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection();
		array1.add(56);
		array1.add(31);
		array1.add("Marinko");
		
		array1.remove(1);
		assertEquals("Marinko", array1.get(1));
	}

	@Test
	public void testGet() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection();
		array1.add(56);
		array1.add(31);
		array1.add("Marinko");
		
		assertEquals(56, array1.get(0));
		assertEquals("Marinko", array1.get(2));
	}

	@Test
	public void testInsert() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(4);
		array1.add(56);
		array1.add(31);
		array1.add("Marinko");
		
		array1.Insert(21, 1);
		assertEquals(21, array1.get(1));
		assertEquals("Marinko", array1.get(3));
		
		array1.Insert("Kukulele", 4);
		assertEquals("Kukulele", array1.get(4));
	}

	@Test
	public void testIndexOf() {
		ArrayIndexedCollection array1 = new ArrayIndexedCollection(4);
		array1.add(56);
		array1.add(31);
		array1.add("Marinko");
		array1.add("Marinko");
		
		assertEquals(2, array1.indexOf("Marinko"));
		assertEquals(-1, array1.indexOf(26));
	}

}
