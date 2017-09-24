package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class StudentDatabaseTest {

	@Test
	public void testForJMBAG() throws FileNotFoundException {

		StudentDatabase db = LoadDB.getDB();
		
		assertEquals(true, db.forJMBAG("0000000011").equals(new StudentRecord("0000000011", "Dvorničić", "Jura", "4")));
	}

	@Test
	public void testFilter() throws FileNotFoundException {
		
		StudentDatabase db = LoadDB.getDB();
		
		assertEquals(63,db.filter(new testFilter1()).size());
		assertEquals(0, db.filter(new testFilter2()).size());
	}

	private class testFilter1 implements IFilter {

		@Override
		public boolean accepts(StudentRecord record) {
			return true;
		}
		
	}
	
	private class testFilter2 implements IFilter {

		@Override
		public boolean accepts(StudentRecord record) {
			return false;
		}
		
	}
}
