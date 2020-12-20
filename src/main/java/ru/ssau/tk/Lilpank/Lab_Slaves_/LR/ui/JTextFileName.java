package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import javax.swing.*;
import java.awt.*;

public class JTextFileName extends JDialog {
    private static String fileName = "";
    private final JTextField jTextField = new JTextField("Введите название файла");
    private final JButton jButtonYes = new JButton("Сохранить");


    public JTextFileName() {
        super();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout());
        setLocationRelativeTo(null);
        jButtonYes.addActionListener(args -> {
            if (!jTextField.getText().equals("Введите название файла")) {
                if (!jTextField.getText().isEmpty()) {
                    fileName = jTextField.getText();
                    dispose();
                }
            }
        });
        compose();
        setSize(400, 130);
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonYes))
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(jButtonYes)
                        )
        );
    }

    public String getFileName() {
        return fileName;
    }
}
