package hr.fer.zemris.java.hw04.db;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class QueryParser parses the given query. Query that has a jmbag expression with "=" operator
 * and only that expression is a direct query, e.g. jmbag="0123456789". That is the only allowed
 * direct query. Other queries must be in the exact form keyword comparisonOperator stringLiteral with or without
 * spaces in between. Expressions can be linked with and only with AND operator which is case insensitive. 
 * If the query is invalid QueryParserException is thrown.
 */
public class QueryParser {
	
	/** The token. */
	private DatabaseToken token;
	
	/** The is direct query flag. */
	private boolean isDirectQuery;
	
	/** The jmbag string. */
	String jmbagString;
	
	/** The expressions list. */
	List<ConditionalExpression> expressions = new ArrayList<>();
	
	/**
	 * Instantiates a new query parser.
	 *
	 * @param query the query
	 */
	public QueryParser(String query) {
		parse(query);
	}
	
	/**
	 * Parses the given query.
	 *
	 * @param query the query
	 * @throws QueryParserException if the query is invalid
	 */
	private void parse(String query) {
		QueryLexer lexer = new QueryLexer(query);
		token = lexer.nextToken();
		int checker = 0;
		
		while(token.getType()!=DatabaseTokenType.EOF) {
			ConditionalExpression expression;
			IComparisonOperator operator = null;
			IFieldValueGetter getter = null;
			String literal = null;
			
			if(token.getType()==DatabaseTokenType.KEYWORD) {
				if(getter==null) {
					if(token.getValue().equals("jmbag")) {
						getter = FieldValueGetters.JMBAG;
						checker++;
					
					} else if(token.getValue().equals("firstName")) {
						getter = FieldValueGetters.FIRST_NAME;
					
					} else if(token.getValue().equals("lastName")) {
						getter = FieldValueGetters.LAST_NAME;
					
					}
				}
				
				while(!token.getValue().toLowerCase().equals("and") && !token.getValue().equals("EOF")) {
					if(token.getType()==DatabaseTokenType.OPERATOR) {
						if(operator == null) {
							if(token.getValue().equals("<")) {
								operator = ComparisonOperators.LESS;
							
							} else if(token.getValue().equals(">")) {
								operator = ComparisonOperators.GREATER;
							
							} else if(token.getValue().equals(">=") || token.getValue().equals("=>")) {
								operator = ComparisonOperators.GREATER_OR_EQUALS;
							
							} else if(token.getValue().equals("<=") || token.getValue().equals("=<")) {
								operator = ComparisonOperators.LESS_OR_EQUALS;
							
							} else if(token.getValue().equals("=")) {
								operator = ComparisonOperators.EQUALS;
								checker++;
							
							} else if(token.getValue().equals("!=")) {
								operator = ComparisonOperators.NOT_EQUALS;
							
							} else if(token.getValue().equals("LIKE")) {
								operator = ComparisonOperators.LIKE;
							}
						} else {
							throw new QueryParserException("Invalid query, check the documentation!");
						}
					}
					
					if(token.getType()==DatabaseTokenType.LITERAL) {
						if(literal==null) {
							literal = token.getValue();
							if(checker==2) {
								jmbagString = literal;
							}
						} else {
							throw new QueryParserException("Invalid query, check the documentation!");
						}
					}
					try {
						token = lexer.nextToken();
					} catch(ArrayIndexOutOfBoundsException e) {
						throw new QueryParserException("Invalid query!");
					}
					
				}
			} else {
				throw new QueryParserException("Invalid query, check the documentation!");
			}
			expression = new ConditionalExpression(getter, literal, operator);
			expressions.add(expression);
			token = lexer.nextToken();
		}
		if(expressions.size()==1 && checker==2) {
			isDirectQuery = true;
		} else {
			isDirectQuery = false;
		}
	}
	
	/**
	 * Checks if query is direct.
	 *
	 * @return true, if query is direct
	 */
	public boolean isDirectQuery() {
		return isDirectQuery;
	}
	
	/**
	 * Gets the queried JMBAG. Only works if the query was direct, that is 
	 * if it was in the form jmbag="xxx". Otherwise an exception is thrown.
	 *
	 * @return the queried JMBAG
	 * @throws IllegalStateException if the query wasn't direct
	 */
	public String getQueriedJMBAG() {
		if(isDirectQuery) {
			return jmbagString;
		} else {
			throw new IllegalStateException("Query was not direct!");
		}
	}
	
	/**
	 * Gets the list of queries.
	 *
	 * @return the query
	 */
	public List<ConditionalExpression> getQuery() {
		return expressions;
	}
}
