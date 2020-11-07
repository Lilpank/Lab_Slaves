package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.SqrFunction;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new SqrFunction().apply(2),4);
        assertEquals(new SqrFunction().apply(-5),25);
        assertEquals(new SqrFunction().apply(10),100);
    }
}