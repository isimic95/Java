package hr.fer.zemris.java.hw04.db;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryFilter is an implementation of IFilter. It has a list of conditional expressions
 * specified in the query. In the accepts method, filter goes through records and checks if they
 * satisfy all conditions. If they do they are accepted and they are rejected otherwise.
 */
public class QueryFilter implements IFilter {
	
	/** The list. */
	private List<ConditionalExpression> list;
	
	/**
	 * Instantiates a new query filter.
	 *
	 * @param list the list
	 */
	public QueryFilter(List<ConditionalExpression> list) {
		this.list = list;
	}
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw04.db.IFilter#accepts(hr.fer.zemris.java.hw04.db.StudentRecord)
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		
		for(ConditionalExpression expr : list) {
			boolean satisfied = expr.getComparisonOperator().satisfied(
					 expr.getFieldGetter().get(record),
					 expr.getStringLiteral()
					);
			if(!satisfied) return false;
		}
		return true;
	}

}
