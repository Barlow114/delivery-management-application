package org.delivery.views;

import org.delivery.LoginSession;
import org.delivery.LoginSessionData;
import org.delivery.handlers.RegistrationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfile extends JFrame  implements ActionListener {

    
    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JLabel fullNameLabel, emailLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel;


    JComboBox<String> roles;

    JButton updateProfileButton;
    LoginSessionData sessionData = LoginSession.getSession("userInfo");

    public EditProfile(){
        super("Edit Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

        LoginSessionData sessionData = LoginSession.getSession("userInfo");


        // Add the components to the frame
        JPanel panel = new JPanel();


        // Create the text fields
        fullNameLabel = new JLabel("FullName:");
        fullNameLabel.setBounds(10, 50, 100, 50);


        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 120, 50);


        phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(10, 150, 160, 50);



        roleFieldLabel = new JLabel("Role: ");
        roleFieldLabel.setBounds(10, 200, 170, 50);



        truckNumberLabel = new JLabel("Truck Number: ");
        truckNumberLabel.setBounds(10, 250, 170, 50);
        truckNumberLabel.setVisible(false);


        truckCapacityLabel = new JLabel("Truck Capacity: ");
        truckCapacityLabel.setBounds(10, 300, 170, 50);
        truckCapacityLabel.setVisible(false);


        panel.add(fullNameLabel);
        panel.add(emailLabel);
        panel.add(phoneNumberLabel);
        panel.add(roleFieldLabel);
        panel.add(truckNumberLabel);
        panel.add(truckCapacityLabel);


        fullName = new JTextField();
        fullName.setBounds(120, 50, 250, 50);
        fullName.setText(sessionData.getFullName());


        email = new JTextField();
        email.setBounds(120, 100, 250, 50);
        email.setText(sessionData.getEmail());

        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 150, 250, 50);
        phoneNumber.setText(sessionData.getPhoneNumber());
        String[] rolesList = { "customer", "scheduler", "driver" };

        roles = new JComboBox<>(rolesList);
        roles.setBounds(120, 200, 250, 50);
        System.out.println(sessionData.getRole());
        roles.setSelectedItem(sessionData.getRole());
        roles.setEnabled(false);

        // Set a custom renderer to display the selected item
        roles.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value.toString());
                return this;
            }
        });

        truckNumber = new JTextField();
        truckNumber.setBounds(120, 250, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 300, 250, 50);
        truckCapacity.setVisible(false);


        if (sessionData.getRole().equals("driver")) {
            truckNumberLabel.setVisible(true);
            truckNumber = new JTextField();
            truckNumber.setBounds(120, 250, 250, 50);
            truckNumber.setText(sessionData.getTruckNumber());
            truckNumber.setVisible(true);
//            truckNumber.setEditable(false);

            truckCapacityLabel.setVisible(true);
            truckCapacity = new JTextField();
            truckCapacity.setBounds(120, 300, 250, 50);
            truckCapacity.setText(String.valueOf(sessionData.getTruckCapacity()));
            truckCapacity.setVisible(true);
//            truckCapacity.setEditable(false);
            panel.add(truckNumber);
            panel.add(truckCapacity);
        }
        // Create a new ImageIcon with the desired size
        updateProfileButton = new JButton("Update Profile");
        updateProfileButton.setBackground(new Color(107, 142, 35));
        updateProfileButton.setOpaque(true);
        updateProfileButton.setBorderPainted(false);
        updateProfileButton.setBounds(120, 350, 150, 40);
        updateProfileButton.addActionListener(this);

        panel.add(fullName);
        panel.add(email);
        panel.add(phoneNumber);
        panel.add(roles);
        panel.add(truckNumber);
        panel.add(truckCapacity);
        panel.add(updateProfileButton);


        setContentPane(panel);
        panel.setLayout(null);
        setJMenuBar(menu);

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
        if (e.getSource() == updateProfileButton) {

            String response = RegistrationController
                                .updateData( fullName.getText(), email.getText(),phoneNumber.getText(), roles.getSelectedItem().toString(), truckNumber.getText(),
                                        truckCapacity.getText(),   sessionData.getUserId());

            // Check the result and show appropriate message
            if (response.equals("User details updated successfully")) {
                JOptionPane.showMessageDialog(null, response, "Success", JOptionPane.INFORMATION_MESSAGE);
                new Profile();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,  response, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}
