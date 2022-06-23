package com.sahland;

import javax.swing.*;
import java.io.PrintStream;

public class Main {

    public static class InputArgs {
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static InputArgs parseArgs(String[] args) {
        InputArgs params = new InputArgs();

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowFrame();
            }
        });
    } 

    public static void main(String[] args) throws RuntimeException {

        InputArgs params = parseArgs(args);

        if (params.help) {
            PrintStream out = params.error ?  System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            winMain();
        } else {

            try {

                /*
                Консольная реализация
                 */

                MyList list = new MyList(); // Создаём список
                DataFile.readFile(list, params.inputFile); // Парсим данные из файла в спиок
                list.printList(); // Выводим список
                System.out.println();

                for (int i = 1; i < list.size(); i+= 2) { // Индексация списка начинается с 1
                    list.swap(i); // Меняем пару элементов через i+2
                }

                list.printList(); // Выводим свапнутый список


            } catch (Exception exc) {
                exc.getMessage();
            }

        }

    }
}
