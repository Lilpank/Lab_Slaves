package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private final MathFunction x = new IdentityFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction one = new UnitFunction();
    private final MathFunction lg = new LgFunction();
    private final MathFunction function1 = (sqr).andThen(sqr).andThen(x);
    private final MathFunction function2 = (lg).andThen(sqr).andThen(sqr).andThen(x);


    @Test
    public void testAndThen() {
        assertNotEquals(function1.apply(10000), 100);
        assertEquals(function1.andThen(one).apply(0), 1);
        assertEquals(function1.apply(10), 10000);
        assertEquals(function2.apply(100), 16);

    }
}