package main.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.parser.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Handler {

    private static SentenceParser instance;

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    private static final String SENTENCE_REGEXP = "[A-Z].*?[.!?]";
    private final Handler lexemeParser = LexemeParser.getInstance();

    @Override
    public Component handleParsing(String text) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEXP);
        Matcher matcher = pattern.matcher(text);
        Component textComponent = new Composite();

        while(matcher.find()) {
            String sentence = matcher.group();
            Component lexemeComponent = lexemeParser.handleParsing(sentence);
            textComponent.add(lexemeComponent);
        }
        return textComponent;
    }
}
