package hr.fer.zemris.java.hw04.db;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw04.collections.SimpleHashtable;

/**
 * The Class StudentDatabase represents a database of student records. Records are kepts in a list
 * but if you know student's jmbag you can use the index to access it in O(1) complexity.
 */
public class StudentDatabase {
	
	/** The list. */
	StudentRecord[] list;
	
	/** The index. */
	SimpleHashtable<String, StudentRecord> index;
	
	/**
	 * Instantiates a new student database.
	 *
	 * @param data the data
	 */
	public StudentDatabase(String[] data) {
		list = new StudentRecord[data.length];
		index = new SimpleHashtable<>(data.length);
		int i = 0;
		
		for(String record : data) {
			String[] splitRecord = record.split("\\s+");
			list[i] = new StudentRecord(splitRecord[0],splitRecord[1],splitRecord[2],splitRecord[3]);
			index.put(splitRecord[0], list[i]);
			i++;
		}
	}
	
	/**
	 * Returns the records with the given jmbag in O(1) complexity.
	 *
	 * @param jmbag the jmbag
	 * @return the student record
	 */
	public StudentRecord forJMBAG(String jmbag) {
		return index.get(jmbag);
	}
	
	/**
	 * Goes through all records and filters them based on the passed filter.
	 * Records that satisfy the filter are returned in a list.
	 *
	 * @param filter the filter
	 * @return the list
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> tempList = new ArrayList<>();
		
		for(StudentRecord record : list) {
			if(filter.accepts(record)) {
				tempList.add(record);
			}
		}
		
		return tempList;
	}
}
