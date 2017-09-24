package hr.fer.zemris.java.custom.scripting.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementConstantInteger;
import hr.fer.zemris.java.custom.scripting.elems.ElementFunction;
import hr.fer.zemris.java.custom.scripting.elems.ElementOperator;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.lexer.SmartLexer;
import hr.fer.zemris.java.custom.scripting.lexer.SmartLexerState;
import hr.fer.zemris.java.custom.scripting.lexer.SmartToken;
import hr.fer.zemris.java.custom.scripting.lexer.SmartTokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;

// TODO: Auto-generated Javadoc
/**
 * The Class SmartScriptParser takes in the text which needs to be parsed
 * and then creates the tree of nodes from it. It uses SmartLexer for production of tokens.
 * Elements are then created from tokens and nodes are made from elements.
 * 
 */
public class SmartScriptParser {
	
	/** The lexer. */
	private SmartLexer lexer;
	
	/** The token. */
	private SmartToken token;
	
	/** The stack. */
	private ObjectStack stack;
	
	/** The node. */
	private DocumentNode node;
	
	/**
	 * Instantiates a new smart script parser and parses the given text.
	 *
	 * @param text the text
	 */
	public SmartScriptParser(String text) {
		node = new DocumentNode();
		stack= new ObjectStack();
		lexer=new SmartLexer(text);
		parse(lexer);
	}
	
	/**
	 * Method to which the parsing is delegated. 
	 *
	 * @param lexer the lexer
	 */
	private void parse(SmartLexer lexer) {
		token=lexer.nextToken();
		stack.push(node);
		while(token.getType()!=SmartTokenType.EOF) {
			if(token.getType()==SmartTokenType.START_TAG) {
				lexer.setState(SmartLexerState.TAG);
			}
			
			if(token.getType()==SmartTokenType.END_TAG) {
				lexer.setState(SmartLexerState.TEXT);
			}
			
			if(token.getType()==SmartTokenType.END) {
				stack.pop();
				if(stack.isEmpty()) {
					throw new SmartScriptParserException("There's more END tags than FOR tags");
				}
			}
			
			
			if(token.getType()==SmartTokenType.FOR_EXPRESSION) {
				Pattern pattern = Pattern.compile("\\s*([a-zA-z]+[a-zA-Z0-9_]*){1}\\s+([-]?\\d+|\"[-]?\\d+\"|[a-zA-z]+[a-zA-Z0-9_]*){1}\\s+([-]?\\d+|\"[-]?\\d+\"|[a-zA-z]+[a-zA-Z0-9_]*){1}\\s+([-]?\\d+|\"[-]?\\d+\"|[a-zA-z]+[a-zA-Z0-9_]*)?\\s*");
				Matcher matcher = pattern.matcher((String)token.getValue());
				if(matcher.matches()) {
					Element start;
					Element end;
					Element step;
					if(matcher.group(2) instanceof String) {
						start = new ElementVariable(matcher.group(2));
					} else {
						start = new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(2))));
					}
					if(matcher.group(2) instanceof String) {
						end = new ElementVariable(matcher.group(3));
					} else {
						end = new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(3))));
					}
					if(matcher.group(2) instanceof String) {
						step = new ElementVariable(matcher.group(4));
					} else {
						step = new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(4))));
					}
					ForLoopNode node = new ForLoopNode(new ElementVariable(matcher.group(1)), start, end, step);
//					ForLoopNode node = new ForLoopNode(new ElementVariable(matcher.group(1)), 
//							new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(2)))), 
//							new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(3)))), 
//							new ElementConstantInteger(Integer.parseInt(String.valueOf(matcher.group(4)))));
					((Node) stack.peek()).addChildNode((Node)node);
					stack.push(node);
				} else {
					throw new SmartScriptParserException("For expression is invalid");
				}
			}
			
			if(token.getType()==SmartTokenType.ECHO) {
				lexer.setState(SmartLexerState.ECHO);
				ArrayIndexedCollection col = new ArrayIndexedCollection();
				while(token.getType()!=SmartTokenType.END_TAG) {
					token=lexer.nextToken();
					if(token.getType()==SmartTokenType.VARIABLE) {
						col.add(new ElementVariable((String)token.getValue()));
					}
					
					if(token.getType()==SmartTokenType.OPERATOR) {
						col.add(new ElementOperator(String.valueOf(token.getValue())));
					}
					
					if(token.getType()==SmartTokenType.STRING) {
						col.add(new ElementString((String)token.getValue()));
					}
					
					if(token.getType()==SmartTokenType.FUNCTION) {
						col.add(new ElementFunction((String)token.getValue()));
					}
					
					if(token.getType()==SmartTokenType.NUMBER) {
						col.add(new ElementConstantInteger(Integer.parseInt(String.valueOf(token.getValue()))));
					}
				}
				lexer.setState(SmartLexerState.TEXT);
				Element[] elem = new Element[col.size()];
				for(int i=0, len=col.size(); i < len; i++) {
					elem[i] = (Element)col.get(i);
				}
				col.clear();
				EchoNode node = new EchoNode(elem);
				((Node) stack.peek()).addChildNode((Node)node);
			}
			
			if(token.getType()==SmartTokenType.TEXT) {
				TextNode node = new TextNode((String)token.getValue());
				((Node) stack.peek()).addChildNode((Node)node);
			}
		
			token=lexer.nextToken();
		}
	}

	/**
	 * Gets the document node. Root node in the structure of the document.
	 *
	 * @return the document node
	 */
	public DocumentNode getDocumentNode() {
		return node;
	}
	
	
}
