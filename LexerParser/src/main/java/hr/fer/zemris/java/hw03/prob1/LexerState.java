package hr.fer.zemris.java.hw03.prob1;

// TODO: Auto-generated Javadoc
/**
 * The Enum LexerState represents valid lexer states.
 */
public enum LexerState {
	
	/** The basic state. State in which lexer operates 
	 * up until "#" sign. */
	BASIC, 
 /** The extended state. State in which lexer operates
  * after "#" sign up until another "#" sign. */
 EXTENDED;
}
