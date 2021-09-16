package com.callumwong.calculator.gui;

import com.callumwong.calculator.Main;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.ui.JBRCustomDecorations;
import com.formdev.flatlaf.util.SystemInfo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PreferencesGui {
    private final JFrame frame;
    private final String[] lookAndFeelStrings = {
            "Metal",
            "System",
            "Flat Light",
            "Flat Dark",
            "Flat IntelliJ",
            "Flat Darcula",
            "Arc",
            "Arc Dark",
            "Carbon",
            "Cyan Light",
            "Dark Purple",
            "Gruvbox",
            "Nord",
            "One Dark",
            "Spacegray"
    };

    public PreferencesGui(Dimension size) {
        frame = new JFrame();
        frame.setPreferredSize(size);

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        JCheckBox windowDecorationsCheckBox = new JCheckBox();
        windowDecorationsCheckBox.setSelected(FlatLaf.isUseNativeWindowDecorations());
        windowDecorationsCheckBox.addActionListener(e -> FlatLaf.setUseNativeWindowDecorations(windowDecorationsCheckBox.isSelected()));
        JCheckBox underlineMenuSelectionCheckBox = new JCheckBox();
        underlineMenuSelectionCheckBox.setSelected(UIManager.get("MenuItem.selectionType") != null);
        underlineMenuSelectionCheckBox.addActionListener(e -> UIManager.put("MenuItem.selectionType", underlineMenuSelectionCheckBox.isSelected() ? "underline" : null));

        panel.add(new JLabel("Theme: "));
        panel.add(themeComboBox(), "wrap");
        panel.add(new JLabel("Window Decoratons: "));
        panel.add(windowDecorationsCheckBox, "wrap");
        panel.add(new JLabel("Underline Menu Selection: "));
        panel.add(underlineMenuSelectionCheckBox, "wrap");

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Preferences");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        if (FlatLaf.supportsNativeWindowDecorations()) {
            if (JBRCustomDecorations.isSupported()) {
                windowDecorationsCheckBox.setEnabled(false);
            }
        } else {
            unsupported(windowDecorationsCheckBox);
        }

        if (SystemInfo.isMacOS) {
            unsupported(underlineMenuSelectionCheckBox);
        }
    }

    private void unsupported(JCheckBox menuItem) {
        menuItem.setEnabled(false);
        menuItem.setSelected(false);
        menuItem.setToolTipText("Not supported on your system.");
    }

    private JComboBox<String> themeComboBox() {
        JComboBox<String> themeComboBox = new JComboBox<>(lookAndFeelStrings);
        switch (UIManager.getLookAndFeel().getName()) {
            case "Metal":
                themeComboBox.setSelectedIndex(0);
                break;
            case "CDE/Motif":
                themeComboBox.setSelectedIndex(1);
                break;
            case "FlatLaf Light":
                themeComboBox.setSelectedIndex(2);
                break;
            case "FlatLaf Dark":
                themeComboBox.setSelectedIndex(3);
                break;
            case "FlatLaf IntelliJ":
                themeComboBox.setSelectedIndex(4);
                break;
            case "FlatLaf Darcula":
                themeComboBox.setSelectedIndex(5);
                break;
            case "Cyan light":
                themeComboBox.setSelectedItem("Cyan Light");
                break;
            case "Dark purple":
                themeComboBox.setSelectedItem("Dark Purple");
                break;
            case "Gruvbox Dark Hard":
                themeComboBox.setSelectedItem("Gruvbox");
                break;
            default:
                themeComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
        }
        themeComboBox.addActionListener(e -> {
            try {
                switch ((String) Objects.requireNonNull(((JComboBox<?>) e.getSource()).getSelectedItem())) {
                    case "Metal":
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        break;
                    case "System":
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    case "Motif":
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        break;
                    case "Flat Light":
                        FlatLightLaf.setup();
                        break;
                    case "Flat Dark":
                        FlatDarkLaf.setup();
                        break;
                    case "Flat IntelliJ":
                        FlatIntelliJLaf.setup();
                        break;
                    case "Flat Darcula":
                        FlatDarculaLaf.setup();
                        break;
                    case "Arc":
                        FlatArcIJTheme.setup();
                        break;
                    case "Arc Dark":
                        FlatArcDarkIJTheme.setup();
                        break;
                    case "Carbon":
                        FlatCarbonIJTheme.setup();
                        break;
                    case "Cyan Light":
                        FlatCyanLightIJTheme.setup();
                        break;
                    case "Dark Purple":
                        FlatDarkPurpleIJTheme.setup();
                        break;
                    case "Gruvbox":
                        FlatGruvboxDarkHardIJTheme.setup();
                        break;
                    case "Nord":
                        FlatNordIJTheme.setup();
                        break;
                    case "One Dark":
                        FlatOneDarkIJTheme.setup();
                        break;
                    case "Spacegray":
                        FlatSpacegrayIJTheme.setup();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid look and feel specified!");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            SwingUtilities.updateComponentTreeUI(Main.getInstance().getGui().getFrame());
            Main.getInstance().getGui().getFrame().pack();

            SwingUtilities.updateComponentTreeUI(frame);
            frame.pack();
        });

        return themeComboBox;
    }
}
