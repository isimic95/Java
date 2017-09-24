package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import hr.fer.zemris.java.hw03.prob1.LexerException;

// TODO: Auto-generated Javadoc
/**
 * The Class SmartLexer is a lexic analyzer. It takes in a text and tokenizes it
 * into smaller lexic parts called tokens. Text consists of words, numbers and symbols.
 * All numbers in the text must be representable in type long. Numbers and character '\'
 * are escapable by '\' and they are then considered as a part of the word.
 * Lexer works in "lazy" mode which means it returns the next token only when it is
 * explicitly asked to through its nextToken method. 
 * 
 * Lexer works in 3 states, TEXT, TAG and ECHO. In TEXT mode lexer reads
 * the text up until opening tag symbol "{$" and returns it as a single token. In TAG mode lexer recognises
 * two keywords "FOR" and equal sign "=" and processes them accordingly.Keywords inside the tags can be preceded
 * and succeded by white spaces. Lexer goes into echo state after it comes upon "=" and then produces appropriate
 * tokens in this state until it reaches the end tag "{$". Then it goes into TEXT state again. When lexer comes upon
 * "FOR" keyword it returns the whole for expression as one token. Lexer returns EOF token when it reaches the end of text.
 */

public class SmartLexer {
	
	/** The text which is being analyzed. */
	private char[] data;
	
	/** The current token. */
	private SmartToken token;
	
	/** The current index in the text. */
	private int currentIndex;
	
	/** The lexer state. Possible states are TEXT, TAG and ECHO defined by SmartLexerState enum. */
	private SmartLexerState state = SmartLexerState.TEXT;
	
	/**
	 * Instantiates a new smart lexer.
	 *
	 * @param text the text
	 */
	public SmartLexer(String text) {
		data = text.toCharArray();
	}
	
	/**
	 * Returns the next token.
	 *
	 * @return the smart token
	 */
	public SmartToken nextToken() {
		if(token!=null && token.getType()==SmartTokenType.EOF) {
			throw new LexerException("No new tokens available!");
		}
		
		skipWhiteSpace();
		
		if(currentIndex >= data.length) {
			token = new SmartToken(SmartTokenType.EOF, null);
			return token;
		}
		
		if(data[currentIndex]=='{' && data[currentIndex+1]=='$') {
			token = new SmartToken(SmartTokenType.START_TAG,"{$");
			currentIndex += 2;
			return token;
		}
		
		if(data[currentIndex]=='$' && data[currentIndex+1]=='}') {
			token = new SmartToken(SmartTokenType.END_TAG, "$}");
			currentIndex +=2;
			return token;
		}
		
		
		if(state==SmartLexerState.TAG) {
			return tagMode();
		}
		
		if(state==SmartLexerState.ECHO) {
				skipWhiteSpace();
				if(Character.isLetter(data[currentIndex])) {
					int startIndex = currentIndex;
					while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex])
							|| data[currentIndex]=='_') {
						currentIndex++;
					}
					int endIndex = currentIndex;
					String name = new String(data, startIndex, endIndex-startIndex);
					token = new SmartToken(SmartTokenType.VARIABLE, name);
					return token;
					
				}
				
				if(Character.isDigit(data[currentIndex])) {
					int startIndex = currentIndex;
					while(Character.isDigit(data[currentIndex])) {
						currentIndex++;
					}
					int endIndex = currentIndex;
					String number = new String(data, startIndex, endIndex-startIndex);
					token = new SmartToken(SmartTokenType.NUMBER, Integer.parseInt(number));
					return token;
				}
				
				if(	data[currentIndex]=='*' 
					|| data[currentIndex]=='+'
					|| data[currentIndex]=='/' 
					|| data[currentIndex]=='-' 
					|| data[currentIndex]=='^' ) {
					token = new SmartToken(SmartTokenType.OPERATOR,data[currentIndex]);
					currentIndex++;
					return token;
				}
				
				
				//provjerit jel u navodnicima mogu biti samo brojevi ili bilo šta
				if(data[currentIndex]=='"') {
					currentIndex++;
					StringBuilder sb = new StringBuilder();
					while(data[currentIndex]!='"') {
						if(data[currentIndex]=='\\' ) {
							if(data[currentIndex+1]=='"') {
								sb.append('"');
								currentIndex += 2;
							} else if(data[currentIndex+1]=='\\') {
								sb.append('\\');
								currentIndex += 2;
							} else if(data[currentIndex+1] == 'r' || data[currentIndex+1] == 't' 
									|| data[currentIndex+1] == 'n') {
								sb.append("\\" + data[currentIndex+1]);
								currentIndex += 2;
							}  else {
								throw new SmartScriptParserException("You have escaped an invalid character.");
							}
						}
						sb.append(data[currentIndex]);
						currentIndex++;
				}
					currentIndex++;
					token = new SmartToken(SmartTokenType.STRING, sb.toString());
					return token;
			}
				
				if(data[currentIndex]=='@') {
					currentIndex++;
					int startIndex = currentIndex;
					if(Character.isLetter(data[currentIndex])) {
						while(Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex])
								|| data[currentIndex]=='_') {
							currentIndex++;
						}
						int endIndex = currentIndex;
						String name = new String(data, startIndex, endIndex-startIndex);
						token = new SmartToken(SmartTokenType.FUNCTION, name);
					} else {
						throw new SmartScriptParserException("Function name must start with a letter followed by"
								+ " digits,letters or underscore");
					}
					
				}
		}
		
		if(state==SmartLexerState.TEXT) {
			return textMode();
	}
		return token;
}
	
	/**
	 * Gets the current token.
	 *
	 * @return the current token
	 */
	public SmartToken getToken() {
		return token;
	}
	
	/**
	 * The textMode method is entered when SmartLexer enters text state.
	 * 
	 * @return SmartToken
	 */
	private SmartToken textMode() {
		StringBuilder sb = new StringBuilder();
		while(data[currentIndex]!='{' && data[currentIndex+1]!='$') {
			if(data[currentIndex]=='\\' ) {
				if(data[currentIndex+1]=='{') {
					sb.append('{');
					currentIndex += 2;
				} else if(data[currentIndex+1]=='\\') {
					sb.append('\\');
					currentIndex += 2;
				}
			}
			sb.append(data[currentIndex]);
			currentIndex++;
			if(currentIndex >= data.length) break;
		}
		token = new SmartToken(SmartTokenType.TEXT, sb.toString());
		return token;
	}
	
	/**
	 * The tagMode method is called when SmartLexer enters tag state.
	 * 
	 * @return SmartToken
	 */
	private SmartToken tagMode() {
		skipWhiteSpace();
		
		if(String.copyValueOf(data, currentIndex, 3).toLowerCase().equals("end")) {
			token = new SmartToken(SmartTokenType.END, "end");
			currentIndex += 3;
			return token;
		} else if(String.copyValueOf(data, currentIndex, 3).toLowerCase().equals("for")) {
			currentIndex += 3;
			int startIndex = currentIndex;
			while(data[currentIndex]!='$') {
				currentIndex++;
			}
			int endIndex = currentIndex;
			String expression = new String(data, startIndex, endIndex-startIndex);
			token = new SmartToken(SmartTokenType.FOR_EXPRESSION, expression);
			return token;
		} else if(data[currentIndex]=='=') {
			token = new SmartToken(SmartTokenType.ECHO, "echo");
			currentIndex++;
			return token;
		} else {
			throw new SmartScriptParserException("Valid tag keywords are FOR, = and END");
		}
	}
	
	/**
	 * Helpful private method which is used to skip all white space until another character.
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
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(SmartLexerState state) {
		this.state = state;
	}
}
