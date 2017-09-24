package hr.fer.zemris.java.hw04.db;

/**
 * The Enum DatabaseTokenType represents token types which QueryLexer can produce.
 */
public enum DatabaseTokenType {
	
 /** The operator token. */
 OPERATOR, 
 /** The literal represents a string literal in the query. */
 LITERAL, 
 /** The eof, end of file. */
 EOF, 
 /** The keyword, allowed keywords are lastName, firstName and jmbag. */
 KEYWORD;
}
