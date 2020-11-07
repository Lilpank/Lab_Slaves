package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.000001;

    @Test
    public void testDerive() {
        double step = 0.001;

        MiddleSteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 6, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(4), 8, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 10, DELTA);

    }
}
