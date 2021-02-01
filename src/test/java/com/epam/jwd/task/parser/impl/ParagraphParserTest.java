package test.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.composite.impl.Letter;
import main.java.com.epam.jwd.task.composite.impl.Punctuation;
import main.java.com.epam.jwd.task.parser.impl.ParagraphParser;
import org.junit.Assert;
import org.junit.Test;

public class ParagraphParserTest {

    @Test
    public void handleParsing_shouldReturnParagraphComponent_whenMatchesPatternParagraph() {
        String sample = "\tI am.";

        Letter firstLetter = new Letter('I');
        Letter secondLetter = new Letter('a');
        Letter thirdLetter = new Letter('m');
        Punctuation punctuationMark = new Punctuation('.');

        Component firstWord = new Composite();
        firstWord.add(firstLetter);
        Component secondWord = new Composite();
        secondWord.add(secondLetter);
        secondWord.add(thirdLetter);

        Component firstLexeme = new Composite();
        firstLexeme.add(firstWord);
        Component secondLexeme = new Composite();
        secondLexeme.add(secondWord);
        secondLexeme.add(punctuationMark);
//        Component thirdLexeme = new Composite();
//        thirdLexeme.add(punctuationMark);

        Component sentence = new Composite();
        sentence.add(firstLexeme);
        sentence.add(secondLexeme);
//        sentence.add(punctuationMark);

        Component paragraph = new Composite();
        paragraph.add(sentence);

        Component text = new Composite();
        text.add(paragraph);

        Assert.assertEquals(paragraph, ParagraphParser.getInstance().handleParsing(sample));

    }
}
