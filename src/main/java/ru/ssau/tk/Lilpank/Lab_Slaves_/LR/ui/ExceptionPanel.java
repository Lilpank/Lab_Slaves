package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

public class ExceptionPanel extends Exception {
    private static final long serialVersionUID = 1366240616178894726L;

    public ExceptionPanel() {
    }

    public ExceptionPanel(String message) {
        super(message);
    }

    public ExceptionPanel(NumberFormatException message) {
        super("Введите число!");
    }

    public ExceptionPanel(NullPointerException message) {
        super("Введите число!");
    }
}
