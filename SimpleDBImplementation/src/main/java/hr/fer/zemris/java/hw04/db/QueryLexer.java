package hr.fer.zemris.java.hw04.db;

/**
 * The Class QueryLexer is a simple lexer for QueryParser. It takes in the query string
 * and returns tokens whose type is defined in DatabaseTokenType.
 */
public class QueryLexer {
	
	/** The current index. */
	private int currentIndex;
	
	/** The data. */
	private char[] data;
	
	/** The token. */
	private DatabaseToken token;
	
	/**
	 * Instantiates a new query lexer.
	 *
	 * @param text the text
	 */
	public QueryLexer(String text) {
		data = text.toCharArray();
	}
	
	/**
	 * Returns next token.
	 *
	 * @return the database token
	 */
	public DatabaseToken nextToken() {
		if(token!=null && token.getType()==DatabaseTokenType.EOF) {
			return new DatabaseToken(DatabaseTokenType.EOF, "EOF");
		}
		
		skipWhiteSpace();
		
		if(currentIndex >= data.length) {
			token = new DatabaseToken(DatabaseTokenType.EOF, "EOF");
			return token;
		}
		
		if(Character.isLetter(data[currentIndex])) {
			StringBuilder sb = new StringBuilder();
			while(Character.isLetter(data[currentIndex])) {
				sb.append(data[currentIndex]);
				currentIndex++;
			}
			String attribute = sb.toString();
			if(attribute.equals("LIKE")) return new DatabaseToken(DatabaseTokenType.OPERATOR, attribute);
			return new DatabaseToken(DatabaseTokenType.KEYWORD, attribute);
		}
		
		if(data[currentIndex] =='"') {
			StringBuilder sb = new StringBuilder();
			currentIndex++;
			while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex]=='*') {
				sb.append(data[currentIndex]);
				currentIndex++;
			}
			currentIndex++;
			String literal = sb.toString();
			return new DatabaseToken(DatabaseTokenType.LITERAL, literal);
		}
		
		if(data[currentIndex]=='=' || data[currentIndex]=='<' || data[currentIndex]=='>') {
			return new DatabaseToken(DatabaseTokenType.OPERATOR, String.valueOf(data[currentIndex++]));
		}
		
		if(String.copyValueOf(data, currentIndex, 2).equals("<=")
			|| String.copyValueOf(data, currentIndex, 2).equals(">=")
			|| String.copyValueOf(data, currentIndex, 2).equals("=<")
			|| String.copyValueOf(data, currentIndex, 2).equals("=>")
			|| String.copyValueOf(data, currentIndex, 2).equals("!=")) {
			currentIndex += 2;
			return new DatabaseToken(DatabaseTokenType.OPERATOR, String.copyValueOf(data, currentIndex, 2));
		}
		
		return token;
	}
	
	/**
	 * Skip white space.
	 */
	private void skipWhiteSpace() {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			if(Character.isWhitespace(c)) {
				currentIndex++;
				continue;
			}
			break;
		}
	}
}
