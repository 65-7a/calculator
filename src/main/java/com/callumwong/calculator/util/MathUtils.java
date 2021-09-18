package com.callumwong.calculator.util;

import parser.MathExpression;
import parser.Parser_Result;

public class MathUtils {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static String evaluateExpression(String expression) {
        MathExpression expr = new MathExpression(expression.replace("x", "*").trim());
        if (expr.parser_Result == Parser_Result.VALID) {
            return expr.solve().replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1");
        } else {
            return expr.parser_Result.name();
        }
    }

    public static boolean expressionValid(String expression) {
        MathExpression expr = new MathExpression(expression.replace("x", "*").trim());
        return expr.parser_Result == Parser_Result.VALID;
    }
}
