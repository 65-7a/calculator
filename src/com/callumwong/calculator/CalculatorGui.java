package com.callumwong.calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorGui {
    private final JFrame frame;
    private final ArrayList<JButton> numberButtons = new ArrayList<>();

    public CalculatorGui(String title, Dimension size) {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(size.width + 15, size.height + 39));

        JTextField textField = new JTextField();
        textField.setBounds(0, 0, size.width, size.height / 5);
        textField.setHorizontalAlignment(JTextField.CENTER);

        frame.add(textField);

        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            numberButtons.add(button);
            button.setFocusable(false);
        }

        int yOffset = size.height / 5;
        int buttonWidth = size.width / 3;
        int buttonHeight = (size.height - yOffset) / 4;

        numberButtons.get(0).setBounds(buttonWidth, size.height - buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(1).setBounds(0, yOffset, buttonWidth, buttonHeight);
        numberButtons.get(2).setBounds(buttonWidth, yOffset, buttonWidth, buttonHeight);
        numberButtons.get(3).setBounds(buttonWidth * 2, yOffset, buttonWidth, buttonHeight);
        numberButtons.get(4).setBounds(0, yOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(5).setBounds(buttonWidth, yOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(6).setBounds(buttonWidth * 2, yOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(7).setBounds(0, yOffset + buttonHeight * 2, buttonWidth, buttonHeight);
        numberButtons.get(8).setBounds(buttonWidth, yOffset + buttonHeight * 2, buttonWidth, buttonHeight);
        numberButtons.get(9).setBounds(buttonWidth * 2, yOffset + buttonHeight * 2, buttonWidth, buttonHeight);

        numberButtons.forEach(button -> frame.getContentPane().add(button));

        // TODO: Use a layout manager
        frame.setLayout(null);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
