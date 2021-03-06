package com.callumwong.calculator.gui;

import com.callumwong.calculator.Main;

import javax.swing.*;
import java.awt.*;

public class LicenseGui {
    public LicenseGui(Dimension size) {
        JDialog dialog = new JDialog(Main.getInstance().getGui().getFrame(), "License");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(
                "This is free and unencumbered software released into the public domain.\n" +
                "\n" +
                "Anyone is free to copy, modify, publish, use, compile, sell, or\n" +
                "distribute this software, either in source code form or as a compiled\n" +
                "binary, for any purpose, commercial or non-commercial, and by any\n" +
                "means.\n" +
                "\n" +
                "In jurisdictions that recognize copyright laws, the author or authors\n" +
                "of this software dedicate any and all copyright interest in the\n" +
                "software to the public domain. We make this dedication for the benefit\n" +
                "of the public at large and to the detriment of our heirs and\n" +
                "successors. We intend this dedication to be an overt act of\n" +
                "relinquishment in perpetuity of all present and future rights to this\n" +
                "software under copyright law.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,\n" +
                "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF\n" +
                "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.\n" +
                "IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR\n" +
                "OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,\n" +
                "ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR\n" +
                "OTHER DEALINGS IN THE SOFTWARE.\n" +
                "\n" +
                "For more information, please refer to <https://unlicense.org>"
        );

        dialog.add(textArea);
        dialog.setSize(size);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
