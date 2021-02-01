package main.java.com.epam.jwd.task.interpreter;

@FunctionalInterface
public interface MathExpression {

    int interpret(Context context);

    static MathExpression number(int number) {
        return context -> number;
    }

    static MathExpression plus(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static MathExpression minus(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static MathExpression multiply(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static MathExpression divide(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) / right.interpret(context);
    }

    static MathExpression leftShift(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) << right.interpret(context);
    }

    static MathExpression rightShift(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) >> right.interpret(context);
    }

    static MathExpression bitwiseComplement(MathExpression number) {
        return context -> ~number.interpret(context);
    }

    static MathExpression bitwiseAnd(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) & right.interpret(context);
    }

    static MathExpression bitwiseOr(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) | right.interpret(context);
    }

    static MathExpression bitwiseExclusiveOr(MathExpression left, MathExpression right) {
        return context -> left.interpret(context) ^ right.interpret(context);
    }
}
