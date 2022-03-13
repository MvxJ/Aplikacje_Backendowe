package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileWriter {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input data to file: ");
        String line = scanner.nextLine();

        try (FileOutputStream file = new FileOutputStream("file.txt")) {
            byte[] mybytes = line.getBytes();

            file.write(mybytes);
        }
    }
}
