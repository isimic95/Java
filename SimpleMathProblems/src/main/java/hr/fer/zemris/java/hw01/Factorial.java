package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Program which calculates factorial for given integer and prints it out.
 * Only calculates n! for numbers in between and including 1 to 20.
 * 
 * @author Ivica Šimić
 *
 */
public class Factorial {

	/**
	 * The main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("Unesite cijeli broj > ");
			if(sc.hasNextInt()) {
				int n = sc.nextInt();
				if(n>=1 && n<=20) {
					long result = calculateFactorial(n);
					System.out.printf("%d! = %d%n", n, result);
				} else {
					System.out.printf("%d nije broj u dozvoljenom rasponu%n", n);
				}
			} else {
				String elem = sc.next();
				if(elem.compareTo("kraj")==0) {
					System.out.println("Doviđenja.");
					break;
				}
				System.out.printf("%s nije cijeli broj.%n", elem);
			}
		}
		sc.close();
	}

	/**
	 * Method which takes in integer n and gives back its factorial n!.
	 * 
	 * @param n 
	 * @return n!
	 */
	public static long calculateFactorial(int n) {
		long result = 1;
		for (int i=2; i<=n; i++) {
			result = result*i;
		}
		return result;
	}
} 

