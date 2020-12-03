package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

import static org.testng.Assert.assertEquals;

public class FunctionsIOTest {
    private final File filePath = new File("temp/array function.txt");
    private final TabulatedFunction listFun = new LinkedListTabulatedFunction(new double[]{1.2, 2.3, 3.6}, new double[]{4, 5, 6});

    @AfterClass
    public void clearDir() {
        filePath.deleteOnExit();
    }

    @Test
    public void testFunctionsIO() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.writeTabulatedFunction(out, listFun);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader inList = new BufferedReader(new FileReader(filePath))) {
            TabulatedFunction tempListFunction = FunctionsIO.readTabulatedFunction(inList, new LinkedListTabulatedFunctionFactory());

            assertEquals(tempListFunction.getCount(), listFun.getCount());
            for (int i = 0; i < tempListFunction.getCount(); i++) {
                assertEquals(tempListFunction.getX(i), listFun.getX(i));
                assertEquals(tempListFunction.getY(i), listFun.getY(i));
            }

            System.out.println(tempListFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}