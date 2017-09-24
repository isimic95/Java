package hr.fer.zemris.java.hw02;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexNumberTest {

	public static final double DELTA = 1e-3;
	
	@Test
	public void testFromReal() {
		ComplexNumber a = ComplexNumber.fromReal(5);
		assertEquals(5, a.getReal(), DELTA);
	}

	@Test
	public void testFromImaginary() {
		ComplexNumber a = ComplexNumber.fromImaginary(5);
		assertEquals(5, a.getImaginary(), DELTA);
	}

	@Test
	public void testFromMagnitudeAndAngle() {
		ComplexNumber a = ComplexNumber.fromMagnitudeAndAngle(5, 4);
		assertEquals(-3.268, a.getReal(), DELTA);
		assertEquals(-3.784, a.getImaginary(), DELTA);
	}
	
	public void testFromMagnitudeAndAngle2PI() {
		ComplexNumber a = ComplexNumber.fromMagnitudeAndAngle(7, Math.PI*2);
		assertEquals(-7, a.getReal(), DELTA);
		assertEquals(0, a.getImaginary(), DELTA);
	}
	
	public void testFromMagnitudeAndAngle0() {
		ComplexNumber a = ComplexNumber.fromMagnitudeAndAngle(16, 0);
		assertEquals(16, a.getReal(), DELTA);
		assertEquals(0, a.getImaginary(), DELTA);
	}
	
	public void testFromMagnitudeAndAngleNegative() {
		ComplexNumber a = ComplexNumber.fromMagnitudeAndAngle(-2, 5);
		assertEquals(a, null);
	}

	@Test
	public void testParse() {
		ComplexNumber a = ComplexNumber.parse("5+6i");
		assertEquals(5, a.getReal(), DELTA);
		assertEquals(6, a.getImaginary(), DELTA);
	}

	@Test
	public void testAdd() {
		ComplexNumber a = new ComplexNumber(2.6, 7);
		ComplexNumber b = new ComplexNumber(3, 7.8);
		
		ComplexNumber aPlusB = a.add(b);
		assertEquals(5.6, aPlusB.getReal(), DELTA);
		assertEquals(14.8, aPlusB.getImaginary(), DELTA);
	}
	
	@Test
	public void testAddNegative() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		ComplexNumber b = new ComplexNumber(11, -4);
		
		ComplexNumber aPlusB = a.add(b);
		assertEquals(8.4, aPlusB.getReal(), DELTA);
		assertEquals(3, aPlusB.getImaginary(), DELTA);
	}
	
	@Test
	public void testSub() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		ComplexNumber b = new ComplexNumber(11, -4);
		
		ComplexNumber aSubB = a.sub(b);
		assertEquals(-13.6, aSubB.getReal(), DELTA);
		assertEquals(11, aSubB.getImaginary(), DELTA);
	}

	@Test
	public void testMul() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		ComplexNumber b = new ComplexNumber(11, -4);
		
		ComplexNumber aMulB = a.mul(b);
		assertEquals(-0.6, aMulB.getReal(), DELTA);
		assertEquals(87.4, aMulB.getImaginary(), DELTA);
	}

	@Test
	public void testDiv() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		ComplexNumber b = new ComplexNumber(11, -4);
		
		ComplexNumber aPlusB = a.div(b);
		assertEquals(-0.41314, aPlusB.getReal(), DELTA);
		assertEquals(0.486, aPlusB.getImaginary(), DELTA);
	}

	@Test
	public void testPowerPositive() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		
		ComplexNumber aPower3 = a.power(3);
		assertEquals(364.624, aPower3.getReal(), DELTA);
		assertEquals(-201.04, aPower3.getImaginary(), DELTA);
	}
	
	@Test
	public void testPowerNegative() {
		ComplexNumber a = new ComplexNumber(-2.6, 7);
		
		ComplexNumber aPower3 = a.power(-2);
		assertEquals(-0.0136, aPower3.getReal(), DELTA);
		assertEquals(0.0117, aPower3.getImaginary(), DELTA);
	}

	@Test
	public void testRoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString1() {
		ComplexNumber a = new ComplexNumber(5,6);
		
		assertEquals("5.0+6.0i", a.toString());
	}
	
	@Test
	public void testToString2() {
		ComplexNumber a = new ComplexNumber(-3,-2.3);
		
		assertEquals("-3.0-2.3i", a.toString());
	}
	
	@Test
	public void testToStringImaginary0() {
		ComplexNumber a = ComplexNumber.fromReal(5.7);
		
		assertEquals("5.7", a.toString());
	}
	
	@Test
	public void testToStringReal0() {
		ComplexNumber a = ComplexNumber.fromImaginary(4.3);
		
		assertEquals("4.3i", a.toString());
	}

}
