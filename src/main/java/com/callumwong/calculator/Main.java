package com.callumwong.calculator;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;

public class Main {
    private static Main instance;
    private CalculatorGui gui;

    private Main() {
        FlatDarkLaf.setup();

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

    public CalculatorGui getGui() {
        return gui;
    }
}
