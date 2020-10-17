package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    @Test
    public void testApply() {
        MathFunction arctg = new ArctanFunction();
        MathFunction self = new IdentityFunction();
        MathFunction ln = new LgFunction();

        MathFunction selfArc = new CompositeFunction(self, arctg);
        MathFunction doubleArc = new CompositeFunction(arctg, arctg);
        MathFunction arcSelf = new CompositeFunction(arctg, self);
        MathFunction lnArc = new CompositeFunction(ln, arctg);
        MathFunction doubleLn = new CompositeFunction(ln, ln);
        MathFunction lnSelf = new CompositeFunction(ln, self);


        assertEquals(selfArc.apply(1), Math.PI / 4, 0.00001);
        assertEquals(doubleArc.apply(3), 0.90, 0.01);
        assertEquals(arcSelf.apply(0), 0, 0.00001);
        assertEquals(lnArc.apply(1), 0);
        assertEquals(doubleLn.apply(10), 0);
        assertEquals(lnSelf.apply(10), 1);
        assertEquals(lnArc.apply(100), 1.1, 0.01);
    }

    @Test
    public void tabulatedFunctionTest() {
        MathFunction arctg = new ArctanFunction();
        MathFunction self = new IdentityFunction();
        MathFunction ln = new LgFunction();
        final double DELTA  = 0.0001;

        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[10];
        for (int i = 0; i < 10; i++) {
            valuesY[i] = arctg.apply(valuesX[i]);
        }

        LinkedListTabulatedFunction temp = new LinkedListTabulatedFunction(valuesX, valuesY);
        assertEquals(temp.andThen(arctg).apply(-Math.PI/4), -0.5527173621691903, DELTA);
        assertEquals(temp.andThen(arctg).apply(-Math.PI/2), -0.8896437320754238, DELTA);
        assertEquals(temp.andThen(arctg).apply(0),0, DELTA);
        assertEquals(temp.andThen(arctg).apply(Math.PI/4), 0.5527173621691903, DELTA);
        assertEquals(temp.andThen(arctg).apply(Math.PI/2),0.7696823514451917, DELTA);
        assertEquals(temp.andThen(arctg).apply(6), 0.9524497451540792, DELTA);
        assertEquals(temp.andThen(arctg).apply(10), 0.9746453159844062, DELTA);
    }
}