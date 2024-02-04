package org.delivery.views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;



public class WelcomePageGui extends JFrame {


    public WelcomePageGui() {
        JFrame frame = new JFrame("Delivery Management System");

        // Create a JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Add a label to the JPanel
//        JLabel label = new JLabel("");
        String text = "Welcome to the Delivery Management System";
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");

        panel.add(label);

        // Add two buttons to the JPanel
        JButton registrationButton = new JButton("Registration");
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration page
                frame.dispose();
                new RegistrationGui();
            }
        });
        panel.add(registrationButton);
        panel.setBackground(new Color(65, 105, 225));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override


            public void actionPerformed(ActionEvent e) {
                // Open the login page
                frame.dispose();
                new LoginPageGui();
//                dispose();
            }
        });
        panel.add(loginButton);

        // Add the JPanel to the JFrame
        frame.add(panel);

        // Set the size and location of the JFrame
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Make the JFrame visible
        frame.setVisible(true);
    }


}
