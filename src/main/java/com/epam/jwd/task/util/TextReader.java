package main.java.com.epam.jwd.task.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

    public static String readFromFile(String pathName) {
        BufferedReader reader = null;
        StringBuilder textFromFile = new StringBuilder();
        String line;
        try {
            reader = new BufferedReader(new FileReader(pathName));
            while ((line = reader.readLine()) != null) {
                textFromFile.append(line);
            }
        } catch (IOException e) {
            //todo
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                //todo
            }
        }
        return textFromFile.toString();
    }
}
