package com.callumwong.calculator;

import javax.swing.*;
import java.awt.*;

public class PreferencesGui {
    private final JFrame frame;

    public PreferencesGui(Dimension size) {
        frame = new JFrame();
        frame.setPreferredSize(size);

        

        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Preferences");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
