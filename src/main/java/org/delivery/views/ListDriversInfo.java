package org.delivery.views;

import org.delivery.handlers.UserController;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class ListDriversInfo extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ListDriversInfo(){
        super("List of Drivers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Drivers Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"ID", "Name", "Email", "Truck Number", "Truck Capacity", "Assign Orders"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);

        //add menu bar
        MenuGui menu = new MenuGui();

        // Set the custom renderer and editor for the button column
        userTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        userTable.getColumnModel().getColumn(5).setCellEditor(new ButtonColumn());

        // Set the table header
        JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font for header
        tableHeader.setBackground(Color.LIGHT_GRAY); // Adjust background color for header

        userTable.setGridColor(Color.BLACK);
        userTable.setShowGrid(true);
        userTable.setIntercellSpacing(new Dimension(1, 1)); // Adjust spacing between cells

        JScrollPane scrollPane = new JScrollPane(userTable);

        // Add the table to the panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Add the panel to the frame
        add(mainPanel);

        //set menu bar
        setJMenuBar(menu);

        // Add static data to the table
        addStaticUserData();

        // Set frame properties
        setSize(700, 500);
        setVisible(true);


    }


    private void addStaticUserData() {
        // Add data to the table from controller
        Vector<Vector<Object>> data = UserController.getUserInfoWithUserType("driver");
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }

    }




    // Custom renderer to display a button in the table cell
    private static class ButtonRenderer extends DefaultTableCellRenderer {
        JButton button = new JButton("Assign Orders");

        ButtonRenderer() {

            Font boldFont = new Font(button.getFont().getFamily(), Font.BOLD, button.getFont().getSize());
            button.setFont(boldFont);
            button.setBackground(new Color(97, 119, 70));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener(e -> {
                // Retrieve the orderId from the button's text
                int orderId = Integer.parseInt(button.getText());
//                assignOrder(orderId,button);
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }


    // ...

    // Create a custom renderer for the column containing the button
    private static class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JButton button;
        private int orderId;

        ButtonColumn() {
            this.button = new JButton("Assign Orders");
//            this.button.addActionListener(e -> );
//                    assignOrder(orderId, this.button));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.orderId = (int) value;
            return button;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.orderId = (int) value;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return orderId;
        }
    }






}
