package hr.fer.zemris.java.hw02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * Class which represents an imaginary number dividing it into real and imaginary part. Provides static constructors which take
 * the imaginary or real part, magnitude and angle or imaginary number as a string. Provides the methods for all four basic aritmetic
 * operations as well as root and power of the number.
 * 
 */
public class ComplexNumber {
	
	/** The real part. */
	private double real;
	
	/** The imaginary part. */
	private double imaginary;
	
	/** The magnitude. */
	private double magnitude;
	
	/** The angle. */
	private double angle;
	
	/**
	 * Instantiates a new complex number.
	 *
	 * @param real the real part
	 * @param imaginary the imaginary part
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		this.magnitude = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
		this.angle = Math.atan2(imaginary, real) + Math.PI;
	}
	
	/**
	 * Returns a new complex number with imaginary part set to 0.
	 *
	 * @param real the real part
	 * @return the complex number
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0);
	}
	
	/**
	 * Returns a new complex number with real part set to 0.
	 *
	 * @param imaginary the imaginary part
	 * @return the complex number
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0, imaginary);
	}
	
	/**
	 * Returns a new complex number calculated from magnitude and angle passed as parameters.
	 *
	 * @param magnitude the magnitude
	 * @param angle the angle
	 * @return the complex number
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		if(magnitude < 0) {
			System.out.println("Magnitude has to be a positive number!");
			return null;
		}
		double r = magnitude*Math.cos(angle);
		double i = magnitude*Math.sin(angle);
		return new ComplexNumber(r, i);
	}
	
	/**
	 * Parses the imaginary number given as a string in form of a+bi.
	 *
	 * @param s the imaginary number as a string
	 * @return the complex number
	 */
	public static ComplexNumber parse(String s) {
		Pattern pattern = Pattern.compile("([+-]?\\d*(\\.\\d+)?)([+-]?\\d*(\\.\\d+)?)i");
		Matcher matcher = pattern.matcher(s);
		 
		if(matcher.matches()) {
			return new ComplexNumber(Float.parseFloat(matcher.group(1)), Float.parseFloat(matcher.group(3)));
		}
		
		return null;
	}
	
	/**
	 * Returns the real part.
	 *
	 * @return the real
	 */
	public double getReal() {
		return real;
	}
	
	/**
	 * Returns the imaginary part.
	 *
	 * @return the imaginary
	 */
	public double getImaginary() {
		return imaginary;
	}
	
	/**
	 * Returns the magnitude.
	 *
	 * @return the magnitude
	 */
	public double getMagnitude() {
		return magnitude;
	}
	
	/**
	 * Returns the angle.
	 *
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Adds the number given as the parameter to the number upon which the method is called and returns
	 * the result as a new complex number.
	 *
	 * @param c the c
	 * @return the complex number
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.real+c.getReal(), this.imaginary+c.getImaginary());
	}
	
	/**
	 * Subtracts the number given as a parameter from the number upon which the method is called
	 * and returns the result as a new complex number.
	 *
	 * @param c the c
	 * @return the complex number
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(this.real-c.getReal(), this.imaginary-c.getImaginary());
	}

	/**
	 * Multiplies the number upon which it is called with the number given as the parameter and returns
	 * the result as a new complex number.
	 *
	 * @param c the c
	 * @return the complex number
	 */
	public ComplexNumber mul(ComplexNumber c) {
		return new ComplexNumber(real*c.getReal()-imaginary*c.getImaginary(),real*c.getImaginary()+imaginary*c.getReal());
	}
	
	/**
	 * Divides the number upon which it is called by the number given as the parameter and returns the result as a new 
	 * complex number
	 *
	 * @param c the c
	 * @return the complex number
	 */
	public ComplexNumber div(ComplexNumber c) {
		double denominator = Math.pow(c.getMagnitude(),2);
        return new ComplexNumber((real*c.getReal()+imaginary*c.getImaginary())/denominator,(imaginary*c.getReal()-real*c.getImaginary())/denominator);
	}
	
	/**
	 * Calculates the nth power of the number upon which the method is called and returns the result as a new complex number.
	 *
	 * @param n the n
	 * @return the complex number
	 */
	public ComplexNumber power(int n) {
		if(n<0) {
			throw new IllegalArgumentException();
		}
		return new ComplexNumber(Math.pow(magnitude, n)*Math.cos(n*angle),Math.pow(magnitude, n)*Math.sin(n*angle));
	}
	
	/**
	 * Calculates the nth root of the number upon which the method is called and returns the results as an array.
	 *
	 * @param n the n
	 * @return the complex number[]
	 */
	public ComplexNumber[] root(int n) {
		if(n<=0) {
			throw new IllegalArgumentException();
		}
		ComplexNumber[] cnArray = new ComplexNumber[n];
		for(int i = 0; i < n; i++) {
			cnArray[i] = new ComplexNumber(Math.pow(magnitude, 1/n)*Math.cos((angle+2*i*Math.PI)/n), Math.pow(magnitude, 1/n)*Math.sin((angle+2*i*Math.PI)/n)); 
		}
		return cnArray;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(imaginary==0) {
			return String.valueOf(real);
		}
		
		if(real==0) {
			return String.valueOf(imaginary) + "i";
		}
		String sign = imaginary > 0 ? "+" : "";
		return real + sign + imaginary + "i";
	}
}
