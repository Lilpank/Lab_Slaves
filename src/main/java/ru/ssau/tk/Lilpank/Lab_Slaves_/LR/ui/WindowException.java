package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import javax.swing.*;

public class WindowException {
    public WindowException(ExceptionPanel exception){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
               exception.getMessage(),
                "Ошибка :'(",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
