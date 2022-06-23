package com.sahland;

import util.ArrayUtils;
import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class WindowFrame {


    private JPanel panelMain;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonExecute;
    private JButton buttonLoadInputFromFile1;
    private JButton buttonSave;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public WindowFrame() {

        JFrame jfrm = new JFrame();

        jfrm.setTitle("Task_2");
        jfrm.setContentPane(panelMain);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        JTableUtils.initJTableForArray(tableInput, 75, true, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 75, false, false, false, false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        jfrm.setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        jfrm.pack();
        jfrm.setVisible(true);

        buttonLoadInputFromFile1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            String[] dataArr = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());

                            JTableUtils.writeArrayToJTable(tableInput, dataArr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });


        // Кнопка выполнить
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MyList list = new MyList();
                    String[] dataArr = JTableUtils.readStringArrayFromJTable(tableInput); // Загружаем данные из таблицы в массив

                    DataFile.readString(list, dataArr); // Преобразовываем массив в список
                    System.out.println();
                    System.out.print("Before: ");
                    list.printList(); // Выводим список до изменений

                    for (int i = 1; i < list.size(); i+= 2) { // Индексация списка начинается с 1
                        list.swap(i); // Меняем пару элементов через i+2
                    }

                    System.out.println();
                    System.out.print("After: ");
                    list.printList(); // Выводим после изменений

                    String[] out = new String[list.size()];
                    DataFile.writeString(list, out); // Преобразовываем список в массив

                    JTableUtils.writeArrayToJTable(tableOutput, out); // Загружаем массив в таблицу



                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });


        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] dataArr = JTableUtils.readStringArrayFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();

                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }

                       DataFile.writeToFileString(dataArr, file);
                    }
                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }

            }
        });
    }
}
