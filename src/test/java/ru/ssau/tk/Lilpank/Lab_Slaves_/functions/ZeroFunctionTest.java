package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new ZeroFunction().apply(200), 0);
        assertEquals(new ZeroFunction().apply(500), 0);
        assertEquals(new ZeroFunction().apply(667), 0);
    }
}
