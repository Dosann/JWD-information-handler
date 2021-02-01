package main.java.com.epam.jwd.task.service;

import main.java.com.epam.jwd.task.composite.Component;

public interface TextService {

    void sortParagraphsByAmountOfSentences(Component component);

    void sortSentencesByLengthOfLexemes(Component component);

    void sortLexemesBySymbol(Component component);

}
