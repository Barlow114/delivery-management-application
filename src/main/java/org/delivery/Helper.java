package org.delivery;

import javax.swing.*;
import java.awt.*;

public class Helper {



    public static JLabel createClickableLinkLabel(String text) {
        JLabel linkLabel = new JLabel();
        linkLabel.setText("<html><u>" + text + "</u></html>");
        linkLabel.setForeground(Color.BLUE);
        linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return linkLabel;
    }











}
