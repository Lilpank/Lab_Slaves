package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MockTabulatedFunctionTest {

    MockTabulatedFunction obj = new MockTabulatedFunction();

    @Test
    public void testApply() {
        assertEquals(obj.apply(5.), 12.);
        assertEquals(obj.apply(0), 0);
        assertEquals(obj.apply(1), 1);

    }

    @Test
    public void testInterpolate() {
        final double delta = 0.00005;
        assertEquals(obj.interpolate(2.5, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 4.5, delta);
        assertEquals(obj.interpolate(21, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 60, delta);
        assertEquals(obj.interpolate(5, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 12, delta);
    }
}