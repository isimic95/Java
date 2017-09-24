package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

// TODO: Auto-generated Javadoc
/**
 * Program which takes in a mathematical expression in postfix notation and returns the result of the expression. For example
 * the expression -1 8 2 / + returns 3. The expression should be given as one argument under quotation marks. Supported operators
 * are +,-,*,/,%(modulo) and they all work with and produce integer results, so 3/2=1.
 */
public class StackDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Izraz mora biti zadan pod navodnicima kao jedan argument!");
			System.exit(1);
		}
		
		String[] splitExpression = args[0].split(" ");
		String[] operators = {"+", "-", "/", "*", "%"};
		ObjectStack stack = new ObjectStack();
		
		for(String s : splitExpression) {
			try {
				int number = Integer.parseInt(s);
				stack.push(number);
				continue;
			}
			catch(NumberFormatException e) {
				for(String operator : operators) {
					if(s.equals(operator)) {
						int number2 = (Integer)stack.pop();
						int number1 = (Integer)stack.pop();
						int result = calculate(operator, number1, number2);
						stack.push(result);
						break;
				}
			}
		}
	}
		if(stack.size()!=1) {
			System.out.println("Oops! Expression is invalid!");
		}
		else {
			System.out.println(stack.pop());
		}
  }
	
	private static int calculate(String operator, int n1, int n2) {
		switch(operator) {
			case("+"):	return n1+n2;
			
			case("-"):	return n1-n2;
			
			case("*"):	return n1*n2;
						
			case("/"):	try {
							return n1/n2;
						} catch(IllegalArgumentException e) {
							System.out.println("Pokušali ste dijeliti s nulom!");
						}
			
			case("%"):	return n1%n2;
		}
		return 0;
			
	}
}
