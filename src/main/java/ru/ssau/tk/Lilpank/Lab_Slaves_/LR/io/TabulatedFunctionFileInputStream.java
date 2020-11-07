package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedDifferentialOperator;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        File arrayFile = new File("input/binary function.bin");
        LinkedListTabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(arrayFile))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(bufferedInputStream, arrayFactory);
            System.out.println(arrayFunction.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции");
            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(bufferedReader, listFactory);
            TabulatedDifferentialOperator diffList = new TabulatedDifferentialOperator(listFactory);
            System.out.println(diffList.derive(listFunction).toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
