package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
public class MathFunctionTest {
    MathFunction x = new IdentityFunction();
    MathFunction sqr = new SqrFunction();
    MathFunction one = new UnitFunction();
    MathFunction lg = new LgFunction();
    MathFunction _1function = (sqr).andThen(sqr).andThen(x);
    MathFunction _2function = (lg).andThen(sqr).andThen(sqr).andThen(x);


    @Test
    public void testAndThen() {
        assertNotEquals(_1function.apply(10000), 100);
        assertEquals(_1function.andThen(one).apply(0), 1);
        assertEquals(_1function.apply(10), 10000);
        assertEquals(_2function.apply(100), 16);

    }
}