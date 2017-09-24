package hr.fer.zemris.java.custom.collections.demo;

public enum AritmeticOperations {
	ADD("+"),
	SUBTRACT("-"),
	DIVIDE("/"),
	MULTIPLY("*"),
	MODULO("%");
	
	private final String operator;
	
	AritmeticOperations(String operator) {
		this.operator = operator;
	}
	
	String operator() {
		return operator;
	}
}