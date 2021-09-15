package com.callumwong.calculator;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class AboutGui {
    public AboutGui(Dimension size) {
        JDialog dialog = new JDialog(Main.getInstance().getGui().getFrame(), "About");

        JEditorPane editor = new JEditorPane("text/html", "");
        editor.setEditable(false);
        editor.setText(
                "<br><h1>Calculator</h1><br>" +
                "Developed by Callum Wong<br>" +
                "Uses Java Swing<br>" +
                "Uses FlatLaf by FormDev<br><br>" +
                "<a href=\"openLicense\">License</a>"
        );
        StyledDocument doc = (StyledDocument) editor.getDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        editor.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && e.getDescription().equals("openLicense")) {
                new LicenseGui(new Dimension(800, 600));
            }
        });

        dialog.add(editor);

        dialog.setSize(size);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setResizable(false);
    }
}
