package hr.fer.zemris.java.hw03.prob1;

/**
 * The Class Lexer is a lexic analyzer. It takes in a text and tokenizes it
 * into smaller lexic parts called tokens. It works in "lazy" mode which means
 * it returns the next token only when it is explicitly asked to through its
 * nextToken method.* 
 * 
 * Lexer works in 2 states, BASIC and EXTENDED. In BASIC mode lexer recognizes
 * SYMBOL, NUMBER and WORD tokens. Lexer enters EXTENDED mode when it comes upon
 * "#" sign and stays in that state until another "#". In EXTENDED mode everything until
 * the end of the mode is considered one word. Lexer returns EOF token when it reaches the end of text.
 */
public class Lexer {
	
	/** The data. */
	private char[] data;
	
	/** The current token. */
	private Token token;
	
	/** The current index. */
	private int currentIndex;
	
	/** The state. */
	private LexerState state = LexerState.BASIC;
	
	/**
	 * Instantiates a new lexer.
	 *
	 * @param text the text
	 */
	public Lexer(String text) {
		if(text==null) {
			throw new IllegalArgumentException("Text must not be null!");
		}
		data = text.toCharArray();
	}
	
	/**
	 * Generates and returns the next token.
	 *
	 * @return the token
	 * @throws LexerException if the text has invalid expressions
	 */
	public Token nextToken() {
		if(token!=null && token.getType()==TokenType.EOF) {
			throw new LexerException("No new tokens available!");
		}
		
		skipWhiteSpace();
		
		if(currentIndex >= data.length) {
			token = new Token(TokenType.EOF, null);
			return token;
		}
		
		if(state==LexerState.EXTENDED) {
			return extendedMode();
			
		}
		
		if(Character.isLetter(data[currentIndex]) || data[currentIndex]=='\\') {
			StringBuilder sb = new StringBuilder();
			
			while(currentIndex<data.length && (Character.isLetter(data[currentIndex]) || data[currentIndex]=='\\')) {
				if(Character.isLetter(data[currentIndex])) {
					sb.append(data[currentIndex++]);
					
				} else if(data[currentIndex]=='\\') {
					
					if(currentIndex==data.length-1 || !Character.isDigit(data[currentIndex+1]) && data[currentIndex+1]!='\\') {
						throw new LexerException("Invalid escape ending!");
					}
					
					sb.append(data[++currentIndex]);
					currentIndex++;
				}
			}
			token = new Token(TokenType.WORD, sb.toString());
			return token;
		}
		
		if(Character.isDigit(data[currentIndex])) {
			StringBuilder sb = new StringBuilder();
			
			while(currentIndex<data.length && Character.isDigit(data[currentIndex])) {
				sb.append(data[currentIndex++]);
			}
			try {
				Long broj = Long.valueOf(sb.toString());
				token = new Token(TokenType.NUMBER, broj);
				return token;
			} catch(NumberFormatException ex) {
				throw new LexerException("Number must not be bigger than " +Long.MAX_VALUE+"!");
			}
		}
		token = new Token(TokenType.SYMBOL, data[currentIndex++]);

		return token;
	} 
	
	/**
	 * Returns the current token. Does not generate a new token.
	 *
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	
	private Token extendedMode() {
		if(data[currentIndex]=='#') {
			currentIndex++;
			token = new Token(TokenType.SYMBOL, '#');
			return token;
		}
		StringBuilder sb = new StringBuilder();
		skipWhiteSpace();
		while(!Character.isWhitespace(data[currentIndex]) && data[currentIndex]!='#') {
			
			sb.append(data[currentIndex++]);
		}
		token = new Token(TokenType.WORD, sb.toString());
		return token;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state The new state. Valid states are defined by LexerState enum.
	 */
	public void setState(LexerState state) {
		if(state==null) {
			throw new IllegalArgumentException("Null is not a valid argument for state");
		}
		this.state = state;
	}
	
	/**
	 * Method for skipping of white space.
	 */
	private void skipWhiteSpace() {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			//prominit ovo u Character.iswhitespace
			if(c==' ' || c=='\n' || c=='\r' || c=='\t') {
				currentIndex++;
				continue;
			}
			break;
		}
	}
}
