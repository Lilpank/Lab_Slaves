package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;

import java.io.*;

import static ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io.FunctionsIO.deserializeJson;

public class JsonMain {
    public static void main(String[] args) {
        File filePath = new File("temp/serialized arrayTabulatedFunction.Json");
        double[] xValue = {-3, -2, -1, 0, 1, 2, 3};
        double[] yValue = {-9, -4, -1, 0, 1, 4, 9};

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValue, yValue);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.serializeJson(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            System.out.println(deserializeJson(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
