package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.exceptions.DifferentLengthOfArraysException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private final double[] valuesX1 = new double[]{-15, -3, -1, 0, 1, 3, 15};
    private final double[] valuesY1 = new double[]{-5, -2, -1, -0, 1, 2};

    private final double[] valuesX2 = new double[]{-15, -3, -1, 0, 1, 3, 2};
    private final double[] valuesY2 = new double[]{};

    private final double[] valuesX3 = new double[]{-15, -3, -1, 0, 1, -1};
    private final double[] valuesY3 = new double[]{-5, -2, -1, -0, 1, 2, 9};

    private final double[] valuesX4 = new double[]{0, 1, -2};

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX1, valuesY1));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX2, valuesY2));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX3, valuesY3));
        double[] valuesX = new double[]{-1, 5};
        double[] valuesY = new double[]{9,2};
        AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);//работает без броска
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX2));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX3));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX4));
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3, -3, 7, 7, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
        AbstractTabulatedFunction.checkSorted(valuesX1);//нормально работает без броска))
    }

    @Test
    public void testToString() {
        assertEquals(new LinkedListTabulatedFunction(new double[]{-15, -3, -1, 0}, new double[]{-15, -3, -1, 0}).toString(),
                "LinkedListTabulatedFunction size = 4\n[-15.0; -15.0]\n[-3.0; -3.0]\n[-1.0; -1.0]\n[0.0; 0.0]\n");
        assertEquals(new ArrayTabulatedFunction(new double[]{-15, -3, -1, 0}, new double[]{-15, -3, -1, 0}).toString(),
                "ArrayTabulatedFunction size = 4\n[-15.0; -15.0]\n[-3.0; -3.0]\n[-1.0; -1.0]\n[0.0; 0.0]\n");
    }
}
