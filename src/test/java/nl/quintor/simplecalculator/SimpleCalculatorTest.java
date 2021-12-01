package nl.quintor.simplecalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    SimpleCalculator simpleCalculator;

    @BeforeEach
    void setUp() {
        simpleCalculator = new SimpleCalculator();
    }

//    Adding
    @Test
    public void testAdding() {
        assertEquals(15, simpleCalculator.add(10, 5));
        assertEquals(3, simpleCalculator.add(3, 0));
        assertEquals(5, simpleCalculator.add(0, 5));
    }

    @Test
    public void testNegativeAdding() {
        assertEquals(5, simpleCalculator.add(-5, 10));
        assertEquals(0, simpleCalculator.add(8, -8));
        assertEquals(-10, simpleCalculator.add(-5, -5));
    }

//    Subtraction
    @Test
    public void testSubtraction() {
        assertEquals(5, simpleCalculator.subtract(10, 5));
        assertEquals(-3, simpleCalculator.subtract(7, 10));
        assertEquals(5, simpleCalculator.subtract(5, 0));
    }

    @Test
    public void testNegativeSubtraction() {
        assertEquals(-10, simpleCalculator.subtract(-8, 2));
        assertEquals(7, simpleCalculator.subtract(5, -2));
        assertEquals(3, simpleCalculator.subtract(-5, -8));
    }

//    Multiply
    @Test
    public void testMultiply() {
        assertEquals(56, simpleCalculator.multiple(7, 8));
        assertEquals(156, simpleCalculator.multiple(12, 13));
    }

    @Test
    public void testNegativeMultiply() {
        assertEquals(-50, simpleCalculator.multiple(-10, 5));
        assertEquals(-30, simpleCalculator.multiple(10, -3));
        assertEquals(250, simpleCalculator.multiple(-50, -5));
    }

    @Test
    public void testMultiplyByZero() {
        assertEquals(0, simpleCalculator.multiple(10, 0));
        assertEquals(0, simpleCalculator.multiple(-5, 0));
    }

    @Test
    public void testDivision() {
        assertEquals(4, simpleCalculator.divide(12, 3));
        assertEquals(2.5, simpleCalculator.divide(5, 2));
    }
    @Test
    public void testNegativeDivision() {
        assertEquals(-3, simpleCalculator.divide(9, -3));
        assertEquals(-0.625, simpleCalculator.divide(-5, 8));
    }

    @Test
    public void testDivisionByZero() {
        assertEquals(Double.POSITIVE_INFINITY, simpleCalculator.divide(5, 0));
        assertEquals(Double.NEGATIVE_INFINITY, simpleCalculator.divide(-5, 0));
    }
}