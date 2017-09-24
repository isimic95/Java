
package hr.fer.zemris.java.hw01;

import java.util.Scanner;


/**
 * Program takes two arguments, width and height of a rectangle, in that order.
 * It then calculates and prints out its area and perimeter.
 * The program strictly needs to get one argument at a time.
 * 
 * @author Ivica Šimić
 */
public class Rectangle {

	/**
	 * The main method.
	 *
	 * @param args width and height of the rectangle
	 */
	public static void main(String[] args) {
		if(args.length==2) {
			double width = Double.parseDouble(args[0]);
			double height = Double.parseDouble(args[1]);
			calculateAndPrint(width, height);
			System.exit(0);
	}
		Scanner sc = new Scanner(System.in);
		int argumentCounter = 0;
		double width = 0.0;
		double height = 0.0;
		
		while(argumentCounter<2) {
			
			if(argumentCounter==0) {
				System.out.println("Unesite širinu > ");
			} else {
				System.out.println("Unesite visinu > ");
			}
			
			if (sc.hasNextDouble()) {
				double d = sc.nextDouble();
				if(d < 0) {
					System.out.println("Unijeli ste negativnu vrijednost");
				} else if(argumentCounter==0) {
					width = d;
					argumentCounter++;
				} else {
					height = d;
					argumentCounter++;
				}
			} else {
				String elem = sc.next();
				System.out.format("%s se ne može protumačiti kao broj%n", elem);
			}
		}
		sc.close();
		calculateAndPrint(width, height);
		
		
	}
	
	/**
	 * Calculates area and perimeter with given arguments and prints out the result.
	 *
	 * @param width
	 * @param height
	 */
	public static void calculateAndPrint(double width, double height) {
		double area = width*height;
		double perimeter = 2*width+2*height;
		System.out.format("Pravokutnik širine %.1f i visine %.1f ima površinu %.1f te opseg %.1f", width, height, area, perimeter);
	}
}
