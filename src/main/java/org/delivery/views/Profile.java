package org.delivery.views;

import org.delivery.LoginSession;
import org.delivery.LoginSessionData;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {


    JTextField fullName,email, phoneNumber, truckNumber, truckCapacity;
    JLabel fullNameLabel, emailLabel,phoneNumberLabel, roleFieldLabel, truckNumberLabel, truckCapacityLabel;

    JComboBox<String> roles;


    public Profile(){
        super("Profile");

        MenuGui menu = new MenuGui();

        JPanel panel = new JPanel();


        LoginSessionData sessionData = LoginSession.getSession("userInfo");

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
        fullName.setEditable(false);

        email = new JTextField();
        email.setBounds(120, 100, 250, 50);
        email.setText(sessionData.getEmail());
        email.setEditable(false);


        phoneNumber = new JTextField();
        phoneNumber.setBounds(120, 150, 250, 50);
        phoneNumber.setText(sessionData.getPhoneNumber());
        phoneNumber.setEditable(false);
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


        if (sessionData.getRole().equals("driver")) {
            truckNumberLabel.setVisible(true);
            truckNumber = new JTextField();
            truckNumber.setBounds(120, 250, 250, 50);
            truckNumber.setText(sessionData.getTruckNumber());
            truckNumber.setVisible(true);
            truckNumber.setEditable(false);

            truckCapacityLabel.setVisible(true);
            truckCapacity = new JTextField();
            truckCapacity.setBounds(120, 300, 250, 50);
            truckCapacity.setText(String.valueOf(sessionData.getTruckCapacity()));
            truckCapacity.setVisible(true);
            truckCapacity.setEditable(false);
            panel.add(truckNumber);
            panel.add(truckCapacity);
        }



        truckNumber = new JTextField();
        truckNumber.setBounds(120, 250, 250, 50);
        truckNumber.setVisible(false);

        truckCapacity = new JTextField();
        truckCapacity.setBounds(120, 250, 250, 50);
        truckCapacity.setVisible(false);



        panel.add(fullName);
        panel.add(email);
        panel.add(phoneNumber);
        panel.add(roles);
        panel.add(truckNumber);
        panel.add(truckCapacity);

        setContentPane(panel);
        panel.setLayout(null);
        setJMenuBar(menu);

        setVisible(true);
        pack();
        setSize(600, 600);
    }
}
