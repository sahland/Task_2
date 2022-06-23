package com.sahland;

import java.io.*;

// Класс реализующий ввод/вывод в файл
public class DataFile {

    public static void writeToFile(MyList list, String file) throws Exception {

        String str = "[";
        for (int i = 0; i < list.size(); i++) {
            str += " " + (char) list.getAt(i) + " ";
        }
        str += "]";


        try (PrintWriter writer = new PrintWriter(file,"UTF-8")){
            writer.print(str);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Ошибка");
        }
    } // Запись список в файл

    public static void readFile(MyList list, String file) throws Exception {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            int c;
            while ((c = bufferedReader.read()) != -1) {
                if (c == 32) continue;
                list.addLast((char) c);
            }

        } catch (FileNotFoundException e) {
            System.err.print("Error");
        }

    }   // Чтение данных из файла в список

    public static void readString(MyList list, String[] data) throws Exception {

        String line = "";

        for (int i = 0; i < data.length; i++) {
           line += data[i];
        }

        for (int i = 0; i < line.length(); i++) {
            list.addLast(line.charAt(i));
        }

    } // Чтение данных из строки в список

    public static void writeString(MyList list, String[] data) throws Exception {

        for (int i = 0; i < list.size(); i++) {
           data[i] = String.valueOf(list.getAt(i));
        }

    } // Запись списка в строку

    public static void writeToFileString(String[] data, String file) throws Exception {



        try (PrintWriter writer = new PrintWriter(file,"UTF-8")){
            for (int i = 0; i < data.length; i++) {
                writer.print(data[i]);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Ошибка");
        }
    } // Запись строки в файл

}
