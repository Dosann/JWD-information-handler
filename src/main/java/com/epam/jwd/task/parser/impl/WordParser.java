package main.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.composite.impl.Letter;
import main.java.com.epam.jwd.task.parser.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements Handler {

    private static WordParser instance;

    public static WordParser getInstance() {
        if (instance == null) {
            instance = new WordParser();
        }
        return instance;
    }

    private static final String WORD_REGEXP = "[A-Za-z]+-?[a-z]*";
    private static final String LETTER_REGEXP = "\\p{Alpha}";

    @Override
    public Component handleParsing(String text) {
        Pattern wordPattern = Pattern.compile(WORD_REGEXP);
        Matcher wordMatcher = wordPattern.matcher(text);
        Component textComponent = new Composite();

        while(wordMatcher.find()) {
            String word = wordMatcher.group();
            Component wordComponent = parseIntoLetters(word);
            textComponent.add(wordComponent);
        }
        return textComponent;
    }

    private Component parseIntoLetters(String text) {
        Pattern letterPattern = Pattern.compile(LETTER_REGEXP);
        Matcher letterMatcher = letterPattern.matcher(text);
        Component textComponent = new Composite();

        while(letterMatcher.find()) {
            String letter = letterMatcher.group();
            Component letterComponent = new Letter(letter.charAt(0));
            textComponent.add(letterComponent);
        }
        return textComponent;
    }
}
