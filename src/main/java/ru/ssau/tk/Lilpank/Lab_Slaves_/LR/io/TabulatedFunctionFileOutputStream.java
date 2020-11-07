package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File arrayFile = new File("output/array function.bin");
        File listFile = new File("output/linked list function.bin");

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{1.1, 2.2, 3.3}, new double[]{1, 4, 9});

        try (BufferedOutputStream outArray = new BufferedOutputStream(new FileOutputStream(arrayFile));
             BufferedOutputStream outList = new BufferedOutputStream(new FileOutputStream(listFile))) {
            FunctionsIO.writeTabulatedFunction(outArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outList, listFunction);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
