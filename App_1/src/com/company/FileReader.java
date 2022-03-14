package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {

    public static void main(String[] args) throws IOException {

        try (FileInputStream inputStream = new FileInputStream("test.txt")) {
            File file = new File("test.txt");
            int size = (int)file.length();
            InputStreamReader reader = new InputStreamReader(inputStream);
            char data[] = new char[size];

            reader.read(data, 0, size);
            inputStream.close();

            System.out.println(data);
        }
    }
}
