package main.java.com.epam.jwd.task.interpreter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum ExpressionTokenParser {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ExpressionTokenParser.class);

    private final Context context = Context.getInstance();

    public int parseAndCalculate(String expression) {
        LOGGER.log(Level.INFO, "Parsing mathematical expression");

        List<String> rpnFormatExpression = PolishParser.INSTANCE.convertToReversePolishNotation(expression);
        for (String token : rpnFormatExpression) {
            context.pushValue(parseToken(token));
        }

        LOGGER.log(Level.INFO, "Calculating mathematical expression");
        return context.popValue().interpret(context);
    }

    private MathExpression parseToken(String token) {
        MathExpression mathExpression;
        MathExpression left;
        MathExpression right;

        switch(token) {
            case "+":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.plus(left, right);
                break;
            case "-":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.minus(left, right);
                break;
            case "*":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.multiply(left, right);
                break;
            case "/":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.divide(left, right);
                break;
            case "<<":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.leftShift(left, right);
                break;
            case ">>":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.rightShift(left, right);
                break;
            case "~":
                left = context.popValue();
                mathExpression = MathExpression.bitwiseComplement(left);
                break;
            case "&":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.bitwiseAnd(left, right);
                break;
            case "|":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.bitwiseOr(left, right);
                break;
            case "^":
                right = context.popValue();
                left = context.popValue();
                mathExpression = MathExpression.bitwiseExclusiveOr(left, right);
                break;
            default:
                mathExpression = MathExpression.number(Integer.parseInt(token));
        }
        return mathExpression;
    }

}
