package com.company;

import java.io.*;
import java.io.FileReader;

public class ReadFileLines
{
    public static void main(String[] args) throws IOException {
        File file = new File("test.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(reader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        int counter = 1;

        while((line=bufferReader.readLine())!=null) {
            stringBuffer.append(counter + ". ");
            stringBuffer.append(line);
            stringBuffer.append("\n");
            counter++;
        }

        reader.close();

        System.out.println("FILE CONTENT LINE BY LINE: ");
        System.out.println(stringBuffer.toString());
    }
}
