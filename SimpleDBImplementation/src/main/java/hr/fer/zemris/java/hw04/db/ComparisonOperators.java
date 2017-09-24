package hr.fer.zemris.java.hw04.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class ComparisonOperators implements specific operators of interface IComparisonOperator.
 */
public class ComparisonOperators {
	
	/** The Constant LESS implements operator "<". */
	public static final IComparisonOperator LESS = (value1, value2) -> {
		if(value1.compareTo(value2) < 0) return true;
		return false;
	};
	
	/** The Constant LESS_OR_EQUALS implements operator "=<" . */
	public static final IComparisonOperator LESS_OR_EQUALS = (value1, value2) -> {
		if(value1.compareTo(value2) <= 0) return true;
		return false;
	};
	
	/** The Constant GREATER implements operator ">". */
	public static final IComparisonOperator GREATER = (value1, value2) -> {
		if(value1.compareTo(value2) > 0) return true;
		return false;
	};
	
	/** The Constant GREATER_OR_EQUALS implements operator ">=. */
	public static final IComparisonOperator GREATER_OR_EQUALS = (value1, value2) -> {
		if(value1.compareTo(value2) >= 0) return true;
		return false;
	};
	
	/** The Constant EQUALS implements operator "=". */
	public static final IComparisonOperator EQUALS = (value1, value2) -> {
		if(value1.equals(value2)) return true;
		return false;
	};
	
	/** The Constant NOT_EQUALS implements operator "!=". */
	public static final IComparisonOperator NOT_EQUALS = (value1, value2) -> {
		if(!value1.equals(value2)) return true;
		return false;
	};
	
	/** The Constant LIKE implements operator LIKE, e.g. lastName LIKE "B*" returns all names
	 * that start with letter B. * is a wildcard that stands for 0 or more of any character. */
	public static final IComparisonOperator LIKE = (value1, value2) -> {
		if(value2.equals("*")) return true;
		
		Pattern pattern = Pattern.compile("\\*|\\*?[a-zA-Z]+|[a-zA-Z]+\\*?|[a-zA-Z]+\\*?[a-zA-Z]+");
		Matcher matcher = pattern.matcher(value2);
		if(matcher.matches()) {
			
			if(value2.contains("*")) {
				int from = value2.indexOf("*");
				
				String firstHalf = value2.substring(0, from);
				String secondHalf = value2.substring(from).replace("*","");
				int combinedLen = firstHalf.length() + secondHalf.length();
				
				if(value1.startsWith(firstHalf) && value1.endsWith(secondHalf) 
						&& value1.length() >= combinedLen) {
					return true;
				}
			}
			
			if(value1.equals(value2)) return true;
		} else {
			throw new IllegalArgumentException("Expression for like operator matching can contain"
					+ "letters and a maximum of 1 wildcard (*)");
		}
		
		return false;
	};;
}
