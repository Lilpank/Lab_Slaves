package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = 8220567454129991108L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
