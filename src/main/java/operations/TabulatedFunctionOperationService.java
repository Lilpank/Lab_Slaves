package operations;

import exceptions.InconsistentFunctionsException;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }
    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] dots = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            dots[i++] = newPoint;
        }
        return dots;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {

        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("Количества записей в табулированных функциях не совпадают.");
        }

        Point[] aDots = asPoints(a);
        Point[] bDots = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[b.getCount()];

        for(int i = 0; i < a.getCount(); i++) {
            if(aDots[i].x != bDots[i].x) {
                throw new InconsistentFunctionsException("Координаты x двух функций разные.");
            }
            xValues[i] = aDots[i].x;
            yValues[i] = operation.apply(aDots[i].y, bDots[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

}
