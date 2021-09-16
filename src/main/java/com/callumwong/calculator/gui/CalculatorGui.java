package com.callumwong.calculator.gui;

import com.callumwong.calculator.Main;
import com.callumwong.calculator.util.DigitFilter;
import com.callumwong.calculator.util.MathUtils;
import net.miginfocom.swing.MigLayout;

import javax.script.ScriptEngineManager;
import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CalculatorGui {
    private final JFrame frame;
    private final ScriptEngineManager scriptEngineManager;
    private final ArrayList<JButton> numberButtons = new ArrayList<>();
    private final ArrayList<JButton> otherButtons = new ArrayList<>();
    private JMenuBar menuBar;
    private JTextField textField;
    private Timer recalculateTimer = new Timer(400, null);

    private int yOffset;
    private int buttonWidth;
    private int buttonHeight;

    public CalculatorGui(String title, Dimension size) {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(size.width + 15, size.height + 39));
        scriptEngineManager = new ScriptEngineManager();

        yOffset = size.height / 5;
        buttonWidth = size.width / 4;
        buttonHeight = (size.height - yOffset) / 5;

        addMenuBar();
        addTextField(size);
        addNumberButtons(size);
        addOtherButtons(size);

        recalculateTimer.setRepeats(false);
        recalculateTimer.addActionListener(e -> {
            Main.getInstance().restartGui(new Dimension(frame.getWidth(), frame.getHeight()));
            frame.dispose();
        });

        frame.setLayout(null);
        // TODO: Use MigLayout
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getRootPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (recalculateTimer.isRunning()) {
                    recalculateTimer.restart();
                } else {
                    recalculateTimer.start();
                }
            }
        });
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem preferencesItem = new JMenuItem("Preferences", KeyEvent.VK_P);
        preferencesItem.addActionListener(e -> new PreferencesGui(new Dimension(400, 300)));

        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> new AboutGui(new Dimension(200, 300)));

        fileMenu.add(preferencesItem);
        fileMenu.add(aboutItem);

        frame.setJMenuBar(menuBar);
    }

    private void addTextField(Dimension size) {
        textField = new JTextField();
        textField.setBounds(0, 0, size.width - size.width / 8, size.height / 5);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font(UIManager.getDefaults().getFont("TextField.font").getName(), Font.PLAIN, 24));
        frame.getContentPane().add(textField);

        JButton backspaceButton = new JButton("⌫");
        backspaceButton.setFocusable(false);
        backspaceButton.setBounds(size.width - size.width / 8, 0, size.width / 8, size.height / 5);
        backspaceButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        backspaceButton.addActionListener(e -> {
            String str = textField.getText();
            if (str.length() <= 0) return;
            textField.setText(str.substring(0, str.length() - 1));
        });
        frame.getContentPane().add(backspaceButton);

        ((PlainDocument) textField.getDocument()).setDocumentFilter(new DigitFilter());
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_EQUALS || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setText(MathUtils.evaluateExpression(textField.getText(), scriptEngineManager.getEngineByName("JavaScript")));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void addOtherButtons(Dimension size) {
        otherButtons.add(addOtherButton(".", 0, size.height - buttonHeight));
        otherButtons.add(addOtherButton("%", buttonWidth * 2, size.height - buttonHeight));
        otherButtons.add(addOtherButton("C", 0, yOffset));
        otherButtons.add(addOtherButton("±", buttonWidth, yOffset));
        otherButtons.add(addOtherButton("π", buttonWidth * 2, yOffset));
        otherButtons.add(addOtherButton("÷", buttonWidth * 3, yOffset));
        otherButtons.add(addOtherButton("*", buttonWidth * 3, yOffset + buttonHeight));
        otherButtons.add(addOtherButton("-", buttonWidth * 3, yOffset + buttonHeight * 2));
        otherButtons.add(addOtherButton("+", buttonWidth * 3, yOffset + buttonHeight * 3));
        otherButtons.add(addOtherButton("=", buttonWidth * 3, size.height - buttonHeight));

        otherButtons.get(0).addActionListener(e -> textField.setText(textField.getText() + ".")); // .
        otherButtons.get(1).addActionListener(e -> textField.setText(textField.getText() + "%")); // %
        otherButtons.get(2).addActionListener(e -> textField.setText("")); // C
        otherButtons.get(3).addActionListener(e -> {
            if (textField.getText().isEmpty()) return;
            if (MathUtils.isNumeric(textField.getText())) {
                textField.setText(String.valueOf(new BigDecimal(textField.getText()).multiply(BigDecimal.valueOf(-1))));
            } else if (MathUtils.expressionValid(textField.getText(), scriptEngineManager.getEngineByName("JavaScript"))) {
                String str = textField.getText();
                if (str.length() > 3 && str.startsWith("-(") && str.endsWith(")")) {
                    textField.setText(str.substring(0, str.length() - 1).substring(2));
                } else {
                    textField.setText("-(" + str + ")");
                }
            }
        }); // ±
        otherButtons.get(4).addActionListener(e -> textField.setText(textField.getText() + "3.14159265359")); // π
        otherButtons.get(5).addActionListener(e -> textField.setText(textField.getText() + "/")); // ÷
        otherButtons.get(6).addActionListener(e -> textField.setText(textField.getText() + "*")); // /
        otherButtons.get(7).addActionListener(e -> textField.setText(textField.getText() + "-")); // -
        otherButtons.get(8).addActionListener(e -> textField.setText(textField.getText() + "+")); // +
        otherButtons.get(9).addActionListener(e -> textField.setText(MathUtils.evaluateExpression(textField.getText(), scriptEngineManager.getEngineByName("JavaScript")))); // =

        otherButtons.forEach(button -> button.setFont(new Font(UIManager.getDefaults().getFont("Button.font").getName(), Font.PLAIN, 24)));
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

        int numberButtonYOffset = yOffset + buttonHeight;
        numberButtons.get(0).setBounds(buttonWidth, size.height - buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(1).setBounds(0, numberButtonYOffset, buttonWidth, buttonHeight);
        numberButtons.get(2).setBounds(buttonWidth, numberButtonYOffset, buttonWidth, buttonHeight);
        numberButtons.get(3).setBounds(buttonWidth * 2, numberButtonYOffset, buttonWidth, buttonHeight);
        numberButtons.get(4).setBounds(0, numberButtonYOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(5).setBounds(buttonWidth, numberButtonYOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(6).setBounds(buttonWidth * 2, numberButtonYOffset + buttonHeight, buttonWidth, buttonHeight);
        numberButtons.get(7).setBounds(0, numberButtonYOffset + buttonHeight * 2, buttonWidth, buttonHeight);
        numberButtons.get(8).setBounds(buttonWidth, numberButtonYOffset + buttonHeight * 2, buttonWidth, buttonHeight);
        numberButtons.get(9).setBounds(buttonWidth * 2, numberButtonYOffset + buttonHeight * 2, buttonWidth, buttonHeight);

        numberButtons.forEach(button -> frame.getContentPane().add(button));
        numberButtons.forEach(button -> button.setFont(new Font(UIManager.getDefaults().getFont("Button.font").getName(), Font.PLAIN, 24)));
    }

    public JFrame getFrame() {
        return frame;
    }
}
