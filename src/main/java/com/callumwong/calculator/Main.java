package com.callumwong.calculator;

import com.callumwong.calculator.gui.CalculatorGui;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import java.awt.*;

public class Main {
    private static Main instance;
    private CalculatorGui gui;

    private Main() {
        FlatCarbonIJTheme.setup();

        gui = new CalculatorGui("Calculator", new Dimension(400, 600));
    }

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }

    public void restartGui(Dimension size) {
        gui = new CalculatorGui("Calculator", new Dimension(size));
    }

    public CalculatorGui getGui() {
        return gui;
    }
}
