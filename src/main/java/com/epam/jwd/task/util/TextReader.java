package main.java.com.epam.jwd.task.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

    private static final Logger LOGGER = LogManager.getLogger(TextReader.class);

    public static String readFromFile(String pathName) {
        LOGGER.log(Level.INFO, "Reading text to work with from file...");

        BufferedReader reader = null;
        StringBuilder textFromFile = new StringBuilder();
        String line;
        try {
            reader = new BufferedReader(new FileReader(pathName));
            while ((line = reader.readLine()) != null) {
                textFromFile.append(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Exception has been occurred {}", e.toString());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Exception has been occurred {}", e.toString());
            }
        }
        LOGGER.log(Level.INFO, "Reading was successful");
        return textFromFile.toString();
    }
}
