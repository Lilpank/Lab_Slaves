package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.operations.TabulatedDifferentialOperator;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;
public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File outList = new File("output/serialized linked list functions.bin");
        double[] x = {-3, -2, -1, 0, 1, 2, 3};
        double[] y = {-9, -4, -1, 0, 1, 4, 9};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction listFunction1 = differentialOperator.derive(listFunction);
        TabulatedFunction listFunction2 = differentialOperator.derive(listFunction1);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outList));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(outList))) {

            FunctionsIO.serialize(out, listFunction);
            FunctionsIO.serialize(out, listFunction1);
            FunctionsIO.serialize(out, listFunction2);

            TabulatedFunction resultList = FunctionsIO.deserialize(in);
            TabulatedFunction resultList1 = FunctionsIO.deserialize(in);
            TabulatedFunction resultList2 = FunctionsIO.deserialize(in);

            System.out.println(resultList.toString() + "\n");
            System.out.println(resultList1.toString() + "\n");
            System.out.println(resultList2.toString() + "\n");
        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }

    }
}
