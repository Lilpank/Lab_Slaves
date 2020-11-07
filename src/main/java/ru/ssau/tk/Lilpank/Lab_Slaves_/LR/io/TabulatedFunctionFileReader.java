package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        File myFile = new File("input/function.txt");
        try (BufferedReader inArray = new BufferedReader(new FileReader(myFile));
             BufferedReader inList = new BufferedReader(new FileReader(myFile))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(inArray, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());

            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(inList, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
