package main.java.com.epam.jwd.task.interpreter;

import java.util.ArrayDeque;

public class Context {

    private static class ContextHolder {
        private final static Context INSTANCE = new Context();
    }

    public static Context getInstance() {
        return ContextHolder.INSTANCE;
    }

    private final ArrayDeque<MathExpression> contextValues = new ArrayDeque<>();

    public MathExpression popValue() {
        return contextValues.pop();
    }

    public void pushValue(MathExpression value) {
        this.contextValues.push(value);
    }
}
