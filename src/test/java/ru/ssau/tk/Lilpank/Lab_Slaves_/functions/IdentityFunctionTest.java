package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new IdentityFunction().apply(7.), 7.);
        assertEquals(new IdentityFunction().apply(88.), 88.);
        assertEquals(new IdentityFunction().apply(104.), 104.);
    }
}