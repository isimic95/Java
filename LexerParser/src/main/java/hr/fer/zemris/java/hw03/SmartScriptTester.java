package hr.fer.zemris.java.hw03;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


/**
 * The Class SmartScriptTester is used for testing of SmartScriptParser. All documents
 * which can be tested should be put in examples folder.
 */
public class SmartScriptTester {

		/**
		 * The main method.
		 *
		 * @param args the arguments
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public static void main(String[] args) throws IOException {
			String docBody = new String(
					 Files.readAllBytes(Paths.get("examples/document1")),
					 StandardCharsets.UTF_8
					);
			SmartScriptParser parser = null;
			try {
			 parser = new SmartScriptParser(docBody);
			} catch(SmartScriptParserException e) {
			 System.out.println("Unable to parse document!");
			 System.exit(-1);
			} catch(Exception e) {
			 System.out.println("If this line ever executes, you have failed this class!");
			 System.exit(-1);
			}
			DocumentNode document = parser.getDocumentNode();
			String originalDocumentBody = createOriginalDocumentBody(document);
			System.out.println(originalDocumentBody); // should write something like original
			 // content of docBody
		}
		
		/**
		 * Creates the original document body from the given DocumentNode
		 * and returns it as string. The returned string does not have to exactly
		 * represent the original document, more specifically the white spaces between
		 * text can be different.
		 *
		 * @param node the DocumentNode
		 * @return string
		 */
		private static String createOriginalDocumentBody(Node node) {
			
			String s = "";
			for(int i = 0, size=node.numberOfChildren(); i < size; i++) {
				Node currentNode = node.getChild(i);
				if(currentNode instanceof ForLoopNode) {
					 s += currentNode.toString()  + createOriginalDocumentBody(currentNode)+"{$END$}";
					}
				if(currentNode instanceof TextNode) {
					s+=currentNode.toString();
				}
				if(currentNode instanceof EchoNode) {
					s+=currentNode.toString();
				}
				
				}
			
			return s;
			
		}
	
}

