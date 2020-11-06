package operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.SqrFunction;

import static org.testng.Assert.*;


public class LeftSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.000001;

    @Test
    public void testDerive() {
        double step = 0.001;

        LeftSteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(4), 7.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 9.999, DELTA);
    }
}
