package org.delivery.views;

import org.delivery.Helper;
import org.delivery.handlers.LoginController;
import org.delivery.models.AuthModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPageGui extends JFrame implements ActionListener {

    JTextField email;
    JPasswordField passwordField;

    JLabel  emailLabel, passwordLabel,registrationButtonLabel;



    JButton loginButton;

    public LoginPageGui(){
        super("Login Page");

        // Add the components to the frame
        JPanel contentPane = new JPanel();

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 120, 30);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 150, 140, 30);


        contentPane.add(emailLabel);
        contentPane.add(passwordLabel);



        email = new JTextField();
        email.setBounds(120, 100, 400, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 400, 30);



        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(97, 119, 70));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBounds(120, 230, 150, 30);
        loginButton.addActionListener(this);

        registrationButtonLabel = Helper.createClickableLinkLabel("Yet to registered? Sign up");
        registrationButtonLabel.setBounds(120, 300, 150, 30);

        registrationButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                dispose();
                new RegistrationGui();
                dispose();
            }
        });

        contentPane.add(email);
        contentPane.add(passwordField);

        contentPane.add(loginButton);
        contentPane.add(registrationButtonLabel);

        contentPane.setBackground(new Color(65, 105, 225));
        setContentPane(contentPane);

        contentPane.setLayout(null);

        setVisible(true);
        pack();
        setSize(600, 600);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            if (email.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: " + "All Fields Are Required", "Error", JOptionPane.ERROR_MESSAGE);
            }


            // Get the email and password
            String emailText = email.getText();
            String passwordText = String.valueOf(passwordField.getPassword());

            // Create a login model object
            AuthModel loginModel = new AuthModel(emailText, passwordText);

            // Call the login controller
            String response =  LoginController.Login(loginModel);

            // Check the result and show appropriate message
            if (response.equals("Authentication Successful")) {
                // Show success message
                JOptionPane.showMessageDialog(null, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                // Go to the next screen
                new HomeGui();
                // Close the current screen
                dispose();
            } else {
                // Show error message
                JOptionPane.showMessageDialog(null, "Error: " + response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }




}
