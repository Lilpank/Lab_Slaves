package operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] dots = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            dots[i++] = newPoint;
        }
        return dots;
    }

}
