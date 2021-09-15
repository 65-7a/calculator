package com.callumwong.calculator.util;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MathUtils {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String evaluateExpression(String expression, ScriptEngine engine) {
        String evaluation = expression;
        try {
            evaluation = String.valueOf(engine.eval(expression));
        } catch (ScriptException ignored) { }
        return evaluation;
    }

    public static boolean expressionValid(String expression, ScriptEngine engine) {
        try {
            engine.eval(expression);
            return true;
        } catch (ScriptException ignored) {
            return false;
        }
    }
}
