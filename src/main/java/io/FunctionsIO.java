package io;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;

import java.io.*;

final public class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point temp : function) {
            printWriter.printf("%f %f\n", temp.x, temp.y);
        }
        printWriter.flush();
    }
}
