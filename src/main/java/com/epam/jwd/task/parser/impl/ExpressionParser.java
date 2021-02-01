package main.java.com.epam.jwd.task.parser.impl;

import main.java.com.epam.jwd.task.composite.Component;
import main.java.com.epam.jwd.task.composite.impl.CalculatedNumber;
import main.java.com.epam.jwd.task.composite.impl.Composite;
import main.java.com.epam.jwd.task.interpreter.ExpressionTokenParser;
import main.java.com.epam.jwd.task.parser.Handler;

public class ExpressionParser implements Handler {

    private static ExpressionParser instance;

    public static ExpressionParser getInstance() {
        if (instance == null) {
            instance = new ExpressionParser();
        }
        return instance;
    }

    @Override
    public Component handleParsing(String text) {
        Component textComponent = new Composite();
        int calculatedMathExpression = ExpressionTokenParser.INSTANCE.parseAndCalculate(text);

        Component numberComponent = new CalculatedNumber(String.valueOf(calculatedMathExpression));
        textComponent.add(numberComponent);
        return textComponent;
    }
}
