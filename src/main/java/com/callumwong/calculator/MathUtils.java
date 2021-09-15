package com.callumwong.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.*;

public class MathUtils {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String evaluateExpression(String expression, ScriptEngine engine) {
        String evaluation = expression;
        try {
            evaluation = String.valueOf(engine.eval(expression));
            Main.getInstance().getGui().getTextField().setBackground(Color.white);
        } catch (ScriptException ignored) {
            Main.getInstance().getGui().getTextField().setBackground(new Color(255, 200, 200));
        }
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
