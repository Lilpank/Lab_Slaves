package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

final public class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point temp : function) {
            printWriter.printf("%f %f\n", temp.x, temp.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point point : function) {
            dataOutputStream.writeDouble(point.x);
            dataOutputStream.writeDouble(point.y);
        }
        dataOutputStream.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String tempString = reader.readLine();
            try {
                xValues[i] = formatter.parse(tempString.split(" ")[0]).doubleValue();
                yValues[i] = formatter.parse(tempString.split(" ")[1]).doubleValue();
            } catch (ParseException parseException) {
                throw new IOException(parseException);
            }
        }
        return factory.create(xValues, yValues);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int count = dataInputStream.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        return factory.create(xValues, yValues);
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(function);
        objectOutputStream.flush();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (TabulatedFunction) new ObjectInputStream(stream).readObject();
    }

    public static void serializeXml(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        XStream objectXml = new XStream();
        writer.write(objectXml.toXML(function));
    }

    public static ArrayTabulatedFunction deserializeXml(BufferedReader reader) {
        XStream xStream = new XStream();
        return (ArrayTabulatedFunction) xStream.fromXML(reader);
    }

    public static void serializeJson(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(function));
    }

    public static ArrayTabulatedFunction deserializeJson(BufferedReader reader) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readerFor(ArrayTabulatedFunction.class).readValue(reader);
    }
}