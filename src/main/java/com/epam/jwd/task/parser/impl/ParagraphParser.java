package main.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.parser.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Handler {

    private static ParagraphParser instance;

    public static ParagraphParser getInstance() {
        if (instance == null) {
            instance = new ParagraphParser();
        }
        return instance;
    }

    private static final String PARAGRAPH_REGEXP = "[\\s{4}\\t].*";
    private final Handler sentenceParser = SentenceParser.getInstance();

    @Override
    public Component handleParsing(String text) {
        Pattern pattern = Pattern.compile(PARAGRAPH_REGEXP);
        Matcher matcher = pattern.matcher(text);
        Component textComponent = new Composite();

        while(matcher.find()) {
            String paragraph = matcher.group();
            Component sentenceComponent = sentenceParser.handleParsing(paragraph);
            textComponent.add(sentenceComponent);
        }
        return textComponent;
    }
}
