package org.delivery.views;

import org.delivery.LoginSession;
import org.delivery.LoginSessionData;
import org.delivery.database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ListUserOrders extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    LoginSessionData sessionData = LoginSession.getSession("userInfo");

    public ListUserOrders() {
        super("List Orders Page");

        // Create an instance of the Menu class
        MenuGui menu = new MenuGui();

        // Create a panel to hold the label and table
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Order Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create columns for the table
        String[] columns = {"ID", "Order Number", "Product Name", "Quantity", "Address of Delivery", "Delivery Date", "Order Status"};
        tableModel = new DefaultTableModel(null, columns);
        userTable = new JTable(tableModel);

        // Add the table to the panel
        mainPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Add the panel to the frame's content pane
        getContentPane().add(mainPanel);

        setJMenuBar(menu);
        // Set frame properties
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add static data to the table
        userOrderData();
    }

    private void userOrderData() {

        Vector<Vector<Object>> data = getUserOrders(sessionData.getUserId());
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }


    public static Vector<Vector<Object>> getUserOrders(int userId) {
        Vector<Vector<Object>> orderData = new Vector<>();

        try (Connection connection = DBConnection.getConnection()) {
            // Prepare the SQL statement with placeholders

            String selectOrdersSql =  "SELECT o.*, p.product_name " +
                    "FROM orderlist o " +
                    "INNER JOIN products p ON o.product_id = p.id " +
                    "WHERE o.user_id = ? " + "ORDER BY o.created_at DESC";

            PreparedStatement selectStatement = connection.prepareStatement(selectOrdersSql);
            try (selectStatement) {
                // Set values for the placeholders
                selectStatement.setInt(1, userId);
                ResultSet resultSetUserOrders = selectStatement.executeQuery();
                try (resultSetUserOrders) {
                    while (resultSetUserOrders.next()) {
                        // Extract data from the result set
                        int orderId = resultSetUserOrders.getInt("id");
                        String orderNumber = resultSetUserOrders.getString("order_number");
                        String deliveryAddress = resultSetUserOrders.getString("delivery_address");
                        String deliveryDate = resultSetUserOrders.getString("delivery_date");
                        String orderStatus = resultSetUserOrders.getString("order_status");
                        String productName = resultSetUserOrders.getString("product_name");
                        int quantity = resultSetUserOrders.getInt("quantity");
                        Timestamp dateCreated = resultSetUserOrders.getTimestamp("created_at");

                        // Add the data to the collection
                        Vector<Object> row = new Vector<>();
                        row.add(orderId);
                        row.add(orderNumber);
                        row.add(productName);
                        row.add(quantity);
                        row.add(deliveryAddress);
                        row.add(deliveryDate);
                        row.add(orderStatus);
                        row.add(dateCreated);
                        orderData.add(row);
                    }

                    if (orderData.isEmpty()) {
                        System.out.println("No Order found");
                    }
                    return orderData;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }

}
