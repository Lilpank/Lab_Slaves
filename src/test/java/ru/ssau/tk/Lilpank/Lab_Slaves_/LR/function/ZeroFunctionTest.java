package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ZeroFunction;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new ZeroFunction().apply(200), 0);
        assertEquals(new ZeroFunction().apply(500), 0);
        assertEquals(new ZeroFunction().apply(667), 0);
    }
}
