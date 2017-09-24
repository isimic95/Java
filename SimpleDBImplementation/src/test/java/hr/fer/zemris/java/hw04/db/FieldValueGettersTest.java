package hr.fer.zemris.java.hw04.db;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class FieldValueGettersTest {

	@Test
	public void test() throws FileNotFoundException {
		
		StudentDatabase db = LoadDB.getDB();
		
		StudentRecord record = db.forJMBAG("0000000011");
		
		assertEquals("Dvorničić", FieldValueGetters.LAST_NAME.get(record));
		assertEquals("Jura", FieldValueGetters.FIRST_NAME.get(record));
		assertEquals("0000000011", FieldValueGetters.JMBAG.get(record));
	}

}
