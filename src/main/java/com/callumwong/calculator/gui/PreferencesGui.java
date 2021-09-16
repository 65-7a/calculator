package com.callumwong.calculator.gui;

import com.callumwong.calculator.Main;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import javafx.scene.shape.Arc;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PreferencesGui {
    private final JFrame frame;
    private final String[] lookAndFeelStrings = {
            "Metal",
            "System",
            "Motif",
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

        JComboBox<String> lookAndFeelComboBox = new JComboBox<>(lookAndFeelStrings);
        lookAndFeelComboBox.setSelectedIndex(6);
        lookAndFeelComboBox.addActionListener(e -> {
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

        JLabel themeLabel = new JLabel("Theme ");
        frame.getContentPane().add(themeLabel);
        frame.getContentPane().add(lookAndFeelComboBox);

        frame.setLayout(new MigLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Preferences");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
