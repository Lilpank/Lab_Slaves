package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions;


public class InconsistentFunctionsException extends RuntimeException {
    private static final long serialVersionUID = -4782325309222880210L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
