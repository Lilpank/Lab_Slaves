package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void test(){
        assertEquals(new ZeroFunction().apply(200),0);
    }

}