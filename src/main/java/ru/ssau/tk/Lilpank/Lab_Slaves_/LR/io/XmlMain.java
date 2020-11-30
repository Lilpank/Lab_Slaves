package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;

import java.io.*;

import static ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io.FunctionsIO.deserializeXml;

public class XmlMain {
    public static void main(String[] args) {
        File filePath = new File("serialized arrayTabulatedFunction.xml");
        double[] x = {-3, -2, -1, 0, 1, 2, 3};
        double[] y = {-9, -4, -1, 0, 1, 4, 9};

        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(x, y);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.serializeXml(out, arrayTabulatedFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            System.out.println(deserializeXml(in).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}