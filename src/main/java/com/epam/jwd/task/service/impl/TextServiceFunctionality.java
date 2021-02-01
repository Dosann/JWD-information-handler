package main.java.com.epam.jwd.task.service.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.service.TextService;

import java.util.Comparator;
import java.util.List;

public class TextServiceFunctionality implements TextService {

    @Override
    public void sortParagraphsByAmountOfSentences(Component component) {
        List<Component> paragraphs = component.getComponents();
        paragraphs.sort(Comparator.comparing(Component::getSizeOfComponents));
    }

    @Override
    public void sortSentencesByLengthOfLexemes(Component component) {
    }

    @Override
    public void sortLexemesBySymbol(Component component) {
    }
}
