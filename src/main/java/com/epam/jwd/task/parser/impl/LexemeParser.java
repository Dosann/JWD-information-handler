package main.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.composite.impl.Punctuation;
import main.java.com.epam.jwd.task.parser.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Handler {

    private static LexemeParser instance;

    public static LexemeParser getInstance() {
        if (instance == null) {
            instance = new LexemeParser();
        }
        return instance;
    }

    private static final String LEXEME_REGEXP = "[^\\s\t\n]+";
    private static final String DIGIT_REGEXP = "\\d";
    private static final String PUNCTUATION_REGEXP = "\\p{Punct}";

    private final Handler expressionParser = ExpressionParser.getInstance();
    private final Handler wordParser = WordParser.getInstance();

    @Override
    public Component handleParsing(String text) {
        Pattern pattern = Pattern.compile(LEXEME_REGEXP);
        Matcher matcher = pattern.matcher(text);
        Component textComponent = new Composite();

        while(matcher.find()) {
            String lexeme = matcher.group();
            Component lexemeComponentOfExpression;
            Component lexemeOfWordComponent;

            if (lexeme.length() > 1 &&
                    ((lexeme.endsWith(")") && String.valueOf(lexeme.charAt(lexeme.length() - 2)).matches(DIGIT_REGEXP)) ||
                       String.valueOf(lexeme.charAt(lexeme.length() - 1)).matches(DIGIT_REGEXP))) {
                   lexemeComponentOfExpression = expressionParser.handleParsing(lexeme);
                   textComponent.add(lexemeComponentOfExpression);
            } else {
                lexemeOfWordComponent = wordParser.handleParsing(lexeme);
                textComponent.add(lexemeOfWordComponent);
                resolvePunctuationParsing(textComponent, lexeme);
            }
        }
        return textComponent;
    }

    private void resolvePunctuationParsing(Component textComponent, String lexeme) {
        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_REGEXP);
        Matcher punctuationMatcher = punctuationPattern.matcher(lexeme);
        while(punctuationMatcher.find()) {
            String punctuationMark = punctuationMatcher.group();
            Component punctuationComponent = new Punctuation(punctuationMark.charAt(0));
            textComponent.add(punctuationComponent);
        }
    }
}
