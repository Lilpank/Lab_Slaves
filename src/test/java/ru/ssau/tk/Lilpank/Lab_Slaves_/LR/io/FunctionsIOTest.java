package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class FunctionsIOTest {
    private final File filePath = new File("temp/array function.txt");
    TabulatedFunction listFunction = new LinkedListTabulatedFunction(new double[]{1.2, 2.3, 3.6}, new double[]{4, 5, 6});

    @AfterClass
    public void clearDir() {
        filePath.deleteOnExit();
    }

    @Test
    public void testFunctionsIO() {
        try (BufferedWriter outArray = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.writeTabulatedFunction(outArray, listFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader inList = new BufferedReader(new FileReader(filePath))){
            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(inList, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}