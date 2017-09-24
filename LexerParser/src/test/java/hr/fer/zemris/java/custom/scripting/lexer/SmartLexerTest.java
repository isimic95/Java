package hr.fer.zemris.java.custom.scripting.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

public class SmartLexerTest {

	@Test
	public void testNextToken() {
		SmartLexer lexer = new SmartLexer("blablabla bla?{$= 1$}");
		assertEquals("blablabla bla?", lexer.nextToken().getValue());
	}
	
	@Test
	public void testNextToken2() {
		SmartLexer lexer = new SmartLexer("{$For lok 1 10 2 $} blablabla bla?{$= 1$}");
		lexer.nextToken();
		assertEquals("For lok 1 10 2", lexer.nextToken().getValue());
	}

}
