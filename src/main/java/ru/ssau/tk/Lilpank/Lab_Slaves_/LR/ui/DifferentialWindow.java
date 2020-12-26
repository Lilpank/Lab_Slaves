package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io.FunctionsIO;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class DifferentialWindow extends JDialog {
    private final JButton jButtonResult = new JButton("Результат");
    private final JButton leftJButtonCreateOfArray = new JButton("Создать из массива");
    private final JButton leftJButtonCreateOfFunction = new JButton("Создать из функции");
    private TabulatedFunction firstTabulatedFunction;
    private TabulatedFunction resultTabulatedFunction;
    private final AbstractTableModelForOperation firstTableModel = new AbstractTableModelForOperation(firstTabulatedFunction);
    private final JTable firstTableXY1 = new JTable(firstTableModel);
    private final JButton leftJButtonSave = new JButton("Сохранить");
    private final JButton leftJButtonDownload = new JButton("Загрузить");

    private final JButton resultJButtonSave = new JButton("Сохранить");

    private final AbstractTableModelForResult resultTableModel = new AbstractTableModelForResult();
    private final JTable resultTable = new JTable(resultTableModel);
    private final TabulatedDifferentialOperator diffOperator = new TabulatedDifferentialOperator();
    private final JFileChooser jFileChooser = new JFileChooser();

    public DifferentialWindow() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        jFileChooser.setFileFilter(filter);
        jButtonResult.setEnabled(firstTabulatedFunction != null);
        diffOperator.setFactory(MainFrame.getFactory());
        //Настройки окна
        getContentPane().setLayout(new GridLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new Dimension(1000, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(1000, MainFrame.HEIGHT));
        compose();
        setLocationRelativeTo(null);
        setVisible(false);
        setModal(true);

        leftJButtonCreateOfArray.addActionListener(args -> {
            CreatingTabulatedFunctionOfArray tabulatedArray = new CreatingTabulatedFunctionOfArray();
            firstTabulatedFunction = tabulatedArray.getTabulatedFunction();
            firstTableModel.setFunction(firstTabulatedFunction);
            firstTableModel.fireTableDataChanged();
            jButtonResult.setEnabled(firstTabulatedFunction != null);
        });

        leftJButtonCreateOfFunction.addActionListener(args -> {
            CreatingTabulatedFunctionOfFunction tabulatedFunction = new CreatingTabulatedFunctionOfFunction();
            firstTabulatedFunction = tabulatedFunction.getTabulatedFunction();
            firstTableModel.setFunction(firstTabulatedFunction);
            firstTableModel.fireTableDataChanged();
            jButtonResult.setEnabled(firstTabulatedFunction != null);
        });


        jButtonResult.addActionListener(args -> {
            resultTabulatedFunction = diffOperator.derive(firstTabulatedFunction);
            resultTableModel.setFunction(resultTabulatedFunction);
            resultTableModel.fireTableDataChanged();
        });


        leftJButtonDownload.addActionListener(args -> {
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader outArray = new BufferedReader(new FileReader(file.getPath()))) {
                firstTabulatedFunction = FunctionsIO.readTabulatedFunction(outArray, new ArrayTabulatedFunctionFactory());
            } catch (IOException | NullPointerException e) {
                System.out.println();
            }
            if (firstTabulatedFunction != null) {
                for (int i = 0; i < firstTabulatedFunction.getCount(); i++) {
                    firstTableModel.setFunction(firstTabulatedFunction);
                    firstTableModel.fireTableDataChanged();
                    jButtonResult.setEnabled(true);
                }
            }
        });
        leftJButtonSave.addActionListener(args -> {
            if (firstTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                System.out.println(jFileChooser.getName());
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (firstTabulatedFunction instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, firstTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (firstTabulatedFunction instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, firstTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
        resultJButtonSave.addActionListener(args -> {
            if (resultTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                System.out.println(jFileChooser.getName());
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (resultTabulatedFunction instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, resultTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (resultTabulatedFunction instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, resultTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPaneLeft = new JScrollPane(firstTableXY1);
        JScrollPane tableScrollPaneResult = new JScrollPane(resultTable);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(leftJButtonCreateOfArray)
                                        .addComponent(leftJButtonCreateOfFunction)
                                        .addComponent(leftJButtonDownload)
                                        .addComponent(leftJButtonSave)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tableScrollPaneLeft)
                                )
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonResult)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(resultJButtonSave)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tableScrollPaneResult))
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(leftJButtonCreateOfArray)
                                .addComponent(leftJButtonCreateOfFunction)
                                .addComponent(leftJButtonDownload)
                                .addComponent(leftJButtonSave)
                                .addComponent(resultJButtonSave)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tableScrollPaneLeft)
                                .addComponent(tableScrollPaneResult)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonResult)
                                )
                        )
        );
    }
}
