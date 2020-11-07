package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArctanFunction;

import static org.testng.Assert.*;

public class ArctanFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new ArctanFunction().apply(0), 0);
        assertEquals(new ArctanFunction().apply(1), Math.PI / 4);
        assertEquals(new ArctanFunction().apply(-Math.sqrt(3)), -Math.PI / 3);
    }
}