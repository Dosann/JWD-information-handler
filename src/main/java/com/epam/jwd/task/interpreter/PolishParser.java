package main.java.com.epam.jwd.task.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public enum PolishParser {
    INSTANCE;

    private final static String OPERATOR_REGEXP = "[~&^|/*\\-+]|([<>]){2}";

    public List<String> convertToReversePolishNotation(String expression) {
        List<String> rpnFormat = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        String[] inputTokens = addSpacesAndSplitExpression(expression);

        for (String token : inputTokens) {
            if (isOperator(token)) {
                while (!operators.empty() && isOperator(operators.peek())) {
                    if ((comparePrecedence(token, operators.peek()) <= 0) ||
                            (comparePrecedence(token, operators.peek()) < 0)) {
                        rpnFormat.add(operators.pop());
                        continue;
                    }
                    break;
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.empty() && !operators.peek().equals("(")) {
                    rpnFormat.add(operators.pop());
                }
                operators.pop();
            }
            else {
                rpnFormat.add(token);
            }
        }
        while (!operators.empty()) {
            rpnFormat.add(operators.pop());
        }
        return rpnFormat;
    }

    private String[] addSpacesAndSplitExpression(String expression) {
        String digitRegExp = "\\d";

        StringBuilder resultExpression = new StringBuilder();
        String[] tokens = expression.split("");
        int i = 0;
        while(i < tokens.length) {
            if (tokens[i].equals(">") || tokens[i].equals("<")) {
                resultExpression.append(tokens[i]).append(tokens[++i]).append(" ");
                i++;
            } else if (tokens[i].matches(digitRegExp)) {
                while(i < tokens.length && tokens[i].matches(digitRegExp)) {
                    resultExpression.append(tokens[i]);
                    i++;
                }
                resultExpression.append(" ");
            } else {
                resultExpression.append(tokens[i]).append(" ");
                i++;
            }
        }
        return resultExpression.toString().split(" ");
    }

    private boolean isOperator(String token) {
        return token.matches(OPERATOR_REGEXP);
    }

    private int comparePrecedence(String token, String peek) {
        return Integer.compare(operatorPrecedence(token), operatorPrecedence(peek));
    }

    private int operatorPrecedence(String operator) {
        switch (operator) {
            case "~":
                return 7;
            case "/":
            case "*":
                return 6;
            case "+":
            case "-":
                return 5;
            case "<<":
            case ">>":
                return 4;
            case "&":
                return 3;
            case "^":
                return 2;
            case "|":
                return 1;
            default:
                throw new IllegalStateException(operator + " is invalid operator");
        }
    }
}
