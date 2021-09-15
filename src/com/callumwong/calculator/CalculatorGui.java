package com.callumwong.calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CalculatorGui {
    private final JFrame frame;
    private final ScriptEngineManager scriptEngineManager;
    private final ArrayList<JButton> numberButtons = new ArrayList<>();
    private final ArrayList<JButton> otherButtons = new ArrayList<>();
    private final JTextField textField;

    private int yOffset;
    private int buttonWidth;
    private int buttonHeight;

    public CalculatorGui(String title, Dimension size) {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(size.width + 15, size.height + 39));

        scriptEngineManager = new ScriptEngineManager();

        textField = new JTextField();
        textField.setBounds(0, 0, size.width, size.height / 5);
        textField.setHorizontalAlignment(JTextField.CENTER);
        frame.getContentPane().add(textField);
        ((PlainDocument) textField.getDocument()).setDocumentFilter(new DigitFilter());

        yOffset = size.height / 5;
        buttonWidth = size.width / 3;
        buttonHeight = (size.height - yOffset) / 4;

        addNumberButtons(size);
        addOtherButtons(size);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_EQUALS || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setText(evaluateExpression(textField.getText()));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        frame.setLayout(null);
//        frame.setLayout(new GridBagLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addOtherButtons(Dimension size) {
        otherButtons.add(addOtherButton(".", 0, size.height - buttonHeight));
        otherButtons.add(addOtherButton("=", buttonWidth * 2, size.height - buttonHeight));
        otherButtons.get(0).addActionListener(e -> textField.setText(textField.getText() + "."));
        otherButtons.get(1).addActionListener(e -> textField.setText(evaluateExpression(textField.getText())));
    }

    private String evaluateExpression(String expression) {
        String evaluation = expression;
        try {
            evaluation = String.valueOf(scriptEngineManager.getEngineByName("JavaScript").eval(textField.getText()));
            textField.setBackground(Color.white);
        } catch (ScriptException ignored) {
            textField.setBackground(new Color(255, 200, 200));
        }
        return evaluation;
    }

    private JButton addOtherButton(String string, int x, int y) {
        JButton button = new JButton(string);
        button.setFocusable(false);
        button.setBounds(x, y, buttonWidth, buttonHeight);
        frame.getContentPane().add(button);
        return button;
    }

    private void addNumberButtons(Dimension size) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            JButton button = new JButton(String.valueOf(i));
            numberButtons.add(button);
            button.setFocusable(false);
            button.addActionListener(e -> textField.setText(textField.getText() + finalI));
        }

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
    }
}
