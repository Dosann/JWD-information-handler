package main.java.com.epam.jwd.task.parser;

import main.java.com.epam.jwd.task.composite.Component;

public interface Handler {

    Component handleParsing(String text);

}
