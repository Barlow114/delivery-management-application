package org.delivery.views;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class HomeGui extends JFrame implements ActionListener {
    JLabel  welcomelabel,emptyLabel;
    JFrame frame = new JFrame();

    public HomeGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLocationRelativeTo(null);
        welcomelabel = new JLabel("Welcome to Goods Delivery Application");
        welcomelabel.setFont(new Font("Serif", Font.PLAIN, 24));
        welcomelabel.setBorder(new EmptyBorder(16, 0, 37, 0));// top,left,bottom,right
        welcomelabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyLabel = new JLabel("");
        emptyLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        MenuGui menu = new MenuGui();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        panel.add(welcomelabel);
        panel.add(emptyLabel);
        panel.setBackground(new Color(123, 104, 238));
        //set menu bar
        setJMenuBar(menu);
        this.setTitle("Delivery Application");
        this.setSize(800, 500);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub


    }

}