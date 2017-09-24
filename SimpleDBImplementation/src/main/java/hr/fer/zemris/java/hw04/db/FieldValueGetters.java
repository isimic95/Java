package hr.fer.zemris.java.hw04.db;

/**
 * The Class FieldValueGetters implements value getters for student record.
 * FIRST_NAME returns student's first name. LAST_NAME returns its last name and
 * JMBAG returns student's jmbag.
 */
public class FieldValueGetters {
	
	/** The Constant FIRST_NAME. */
	public static final IFieldValueGetter FIRST_NAME = (record) -> {
		return record.getFirstName();
	};
	
	/** The Constant LAST_NAME. */
	public static final IFieldValueGetter LAST_NAME = (record) -> {
		return record.getLastName();
	};
	
	/** The Constant JMBAG. */
	public static final IFieldValueGetter JMBAG = (record) -> {
		return record.getJmbag();
	};
	
}
