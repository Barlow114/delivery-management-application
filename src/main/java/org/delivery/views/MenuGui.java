package org.delivery.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGui extends JMenuBar implements ActionListener {

    JMenu homeMenu,profileMenu,ordersMenu,missionOverviewMenu, driversMenu, logoutMenu;
    JMenuItem viewProfile, editProfile,viewOrders, createOrders, viewDeliverables, completeDeliverable, generateReport, logoutItem;
    Box horizontalBox;

    public MenuGui(){

        homeMenu = new JMenu("Home");
        profileMenu = new JMenu("Profile");


        ordersMenu = new JMenu("Orders");

        missionOverviewMenu = new JMenu("Mission Overview");
        driversMenu = new JMenu("Drivers");

        createOrders = new JMenuItem("Create Orders");
        viewOrders = new JMenuItem("View Orders");

        createOrders.addActionListener(this);
        viewOrders.addActionListener(this);


        viewDeliverables = new JMenuItem("View Deliverables");

        completeDeliverable = new JMenuItem("Completed Deliverables");

        viewProfile = new JMenuItem("View Profile");
        editProfile = new JMenuItem("Edit Profile");
        generateReport = new JMenuItem("Generate Report");

        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);
        profileMenu.add(viewProfile);
        profileMenu.add(editProfile);



        ordersMenu.add(createOrders);
        ordersMenu.add(viewOrders);
        missionOverviewMenu.add(generateReport);
        driversMenu.add(viewDeliverables);
        driversMenu.add(completeDeliverable);



        logoutMenu = new JMenu("Logout");
        logoutItem = new JMenuItem("Logout");
        logoutMenu.add(logoutItem);

        logoutItem.addActionListener(this);

        add(homeMenu);
        add(profileMenu);
        add(ordersMenu);
        add(missionOverviewMenu);
        add(driversMenu);

        add(logoutMenu);


    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//
        if (e.getSource() == viewProfile) {
            System.out.println("viewProfile");
            disposeCurrentFrame();
            new Profile();
        } else if (e.getSource() == editProfile) {
            System.out.println("editProfile");
            disposeCurrentFrame();
            new EditProfile();
        } else if (e.getSource() == createOrders) {
            System.out.println("createOrders");
            disposeCurrentFrame();
            new CreateOrder();
        } else if (e.getSource() == viewOrders) {
            System.out.println("viewOrders");
            disposeCurrentFrame();
            new ListUserOrders();
        } else if(e.getSource() == logoutItem) {
                JOptionPane.showMessageDialog(this, "User logged out", "Success", JOptionPane.INFORMATION_MESSAGE);
                disposeCurrentFrame();
                new LoginPageGui();
        }


    }

    private void disposeCurrentFrame() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }


}
