package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * The Enum SmartLexerState represents state in which SmartLexer works.
 */
public enum SmartLexerState {
	
 /** The tag mode. Starts when opening tag, {$, is read. */
 TAG, 
 /** The text mode. Starts when closing tag, $}, is read. */
 TEXT, 
 /** The echo mode. Starts when '=' tag inside tag mode is read. */
 ECHO;
}
