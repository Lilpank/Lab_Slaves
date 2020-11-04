package exceptions;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.TabulatedFunctionFactory;

public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
