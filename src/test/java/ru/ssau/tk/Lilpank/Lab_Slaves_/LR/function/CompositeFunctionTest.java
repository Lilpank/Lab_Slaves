package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.*;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    MathFunction arctg = new ArctanFunction();
    final double DELTA = 0.0001;

    @Test
    public void testApply() {
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
        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[]{0., 10., 20., 30., 40., 50., 60., 70., 80., 90.};
        TabulatedFunction tempArray = new ArrayTabulatedFunction(valuesX, valuesY);
        TabulatedFunction tempLL = new LinkedListTabulatedFunction(valuesX, valuesY);
        MathFunction sqr = new SqrFunction();

        assertEquals(tempLL.andThen(tempArray).andThen(sqr).apply(1.5), 22500.0, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(sqr).apply(3), 90000.0, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(sqr).apply(0.5), 2500.0, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(sqr).apply(0.125), 156.25, DELTA);

        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(-Math.PI / 2), -1.5644302150732212, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(0), 0, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(Math.PI / 4), 1.5580646193133636, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(Math.PI / 2), 1.5644302150732212, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(6), 1.5691296616714372, DELTA);
        assertEquals(tempLL.andThen(tempArray).andThen(arctg).apply(10), 1.5697963271282298, DELTA);
    }
}