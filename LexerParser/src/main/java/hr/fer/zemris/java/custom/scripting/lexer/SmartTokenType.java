package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * The Enum SmartTokenType represents all posible tokens in smart lexer.
 */
public enum SmartTokenType {
	
 /** The start tag {$. */
	START_TAG, 
 /** The end tag $}. */
 END_TAG, 
 /** The text. */
 TEXT, 
 /** The operator, +,-,/ etc. */
 OPERATOR, 
 /** The function. */
 FUNCTION, 
	
 /** The echo, returned when "=" inside a tag is found. */
	ECHO, 
 /** The end of file. */
 EOF, 
 /** The variable. */
 VARIABLE, 
 /** The end keyword . */
 END, 
 /** The number. */
 NUMBER, 
 /** The for expression, e.g. var 1 5 1. */
 FOR_EXPRESSION, 
 /** The string. */
 STRING;
}
