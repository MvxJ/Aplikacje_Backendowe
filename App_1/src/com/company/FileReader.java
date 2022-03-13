package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {

    public static void main(String[] args) throws IOException {
        File f = new File("test.txt");
        int size = (int)f.length();
        FileInputStream file = null;
        file = new FileInputStream("test.txt");
        InputStreamReader reader = new InputStreamReader(file);

        char data[] = new char[size];
        reader.read(data, 0, size);
        file.close();

        System.out.println(data);
    }
}
