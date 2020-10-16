package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MockTabulatedFunctionTest {
    public final MockTabulatedFunction obj = new MockTabulatedFunction();
    private final double DELTA = 0.000001;

    @Test
    public void testApply() {
        assertEquals(obj.apply(5.), 12., DELTA);
        assertEquals(obj.apply(0), 0, DELTA);
        assertEquals(obj.apply(1), 1, DELTA);

    }

    @Test
    public void testInterpolate() {
        assertEquals(obj.interpolate(2.5, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 4.5, DELTA);
        assertEquals(obj.interpolate(21, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 60, DELTA);
        assertEquals(obj.interpolate(5, obj.getX(0), obj.getX(1), obj.getY(0), obj.getY(1)), 12, DELTA);
    }
}