package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LgFunction;

import static org.testng.Assert.*;

public class LgFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new LgFunction().apply(1),0);
        assertEquals(new LgFunction().apply(100),2);
        assertEquals(new LgFunction().apply(0.001),-3);
    }
}