package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.IdentityFunction;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new IdentityFunction().apply(7.), 7.);
        assertEquals(new IdentityFunction().apply(88.), 88.);
        assertEquals(new IdentityFunction().apply(104.), 104.);
    }
}