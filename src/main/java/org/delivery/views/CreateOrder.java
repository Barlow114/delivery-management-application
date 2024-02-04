package org.delivery.views;

import com.toedter.calendar.JDateChooser;
import org.delivery.LoginSession;
import org.delivery.LoginSessionData;
import org.delivery.handlers.UserController;
import org.delivery.database.DBConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class CreateOrder extends JFrame implements ActionListener {

    private final JPanel firstSection;
    private final JPanel secondSection;


    JTextField quantity;
    JTextArea  address;
    JLabel productsLabel, quantityLabel, addressLabel,deliveryDateLabel ;


    JComboBox<String> products;

    JDateChooser dateChooser;

    JButton orderButton;

    LoginSessionData sessionData = LoginSession.getSession("userInfo");


    public CreateOrder(){
        setTitle("Create Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        firstSection = createFormFirstSectionPanel();
        secondSection = createFormSecondSectionPanel();
        orderButton = new JButton("Order");
        orderButton.setBackground(new Color(0, 0, 128));
        orderButton.setOpaque(true);
        orderButton.setBorderPainted(false);
        orderButton.setBounds(120, 230, 150, 40);
        orderButton.addActionListener(this);

        MenuGui menu = new MenuGui();


        JPanel secondSectionContainer = new JPanel(new BorderLayout());
        secondSectionContainer.add(secondSection, BorderLayout.CENTER);

        mainPanel.add(firstSection, BorderLayout.PAGE_START);
        mainPanel.add(secondSectionContainer, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(orderButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10)); // Add some padding


        add(mainPanel);
        setJMenuBar(menu);
        setVisible(true);
        setSize(600, 700);

    }


    private JPanel createFormFirstSectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        productsLabel = new JLabel("Products:");

        Map<Integer, String> productMap =  UserController.getProducts();

        String[] productNames = productMap.values().toArray(new String[0]);

        products = new JComboBox<>(productNames);

        quantityLabel = new JLabel("Quanity:");
        quantity = new JTextField();



        panel.add(productsLabel);
        panel.add(products);
        panel.add(quantityLabel);
        panel.add(quantity);

        Border border = BorderFactory.createTitledBorder("Products");
        panel.setBorder(border);

        return panel;

    }


    private JPanel createFormSecondSectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        // Add form components to the panel
        addressLabel = new JLabel("Address: ");
        address = new JTextArea();
        address.setSize(100, 100);

        deliveryDateLabel = new JLabel("Delivery Date: ");

        dateChooser = new JDateChooser();

        dateChooser.setDateFormatString("yyyy-MM-dd");

        panel.add(addressLabel);
        panel.add(address);
        panel.add(deliveryDateLabel);
        panel.add(dateChooser);

        // Add a titled border to the panel
        Border border = BorderFactory.createTitledBorder("Delivery Information");
        panel.setBorder(border);

        return panel;

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {

            int productId = getProductName(String.valueOf(products.getSelectedItem()));
            if (productId == 0) {
                JOptionPane.showMessageDialog(this, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String orderNumber = generateOrderNumber(5);
            String date = getDateFormatted(dateChooser.getDate());
            String createOrder = createOrder(orderNumber, address.getText(), date, sessionData.getUserId(), productId, Integer.parseInt(quantity.getText()));

            if (createOrder.equals("Order created successfully")) {
                JOptionPane.showMessageDialog(this, createOrder, "Success", JOptionPane.INFORMATION_MESSAGE);
                new ListUserOrders();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,  createOrder, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }



    public static Integer getProductName(String productName) {
        int productId = 0;
        try (Connection connection = DBConnection.getConnection()) {
            // Prepare the SQL statement with placeholders
            String sql = "SELECT * FROM products WHERE product_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Set values for the placeholders
                preparedStatement.setString(1, productName);

                try (ResultSet resultSetProducts = preparedStatement.executeQuery()) {
                    if (resultSetProducts.next()) {
                        productId = resultSetProducts.getInt("id");
                    }
                    return productId;
                }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // or return an empty Vector if you prefer
        }
    }


    public static String generateOrderNumber(int length) {
        StringBuilder randomStringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = 0;
            if (Math.random() < 0.5) {
                // Generate a random digit (0-9)
                randomChar = (char) ('0' + Math.random() * ('9' - '0' + 1));
            }
            randomStringBuilder.append(randomChar);
        }

        return randomStringBuilder.toString();
    }


    public static String getDateFormatted(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(formatter);
    }



    public static String createOrder(String orderNumber, String deliveryAdd, String deliveryDate, int userId, int productId, int quantity) {
        Connection connection = DBConnection.getConnection();
        try {
                String insertOrderSql = "INSERT INTO orderlist (order_number,delivery_address,delivery_date,order_status,user_id, product_id, quantity, created_at) VALUES (?, ?, ?, ?, ?,?, ?, ?)";
                PreparedStatement orderStatement = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
                orderStatement.setString(1, orderNumber);
                orderStatement.setString(2, deliveryAdd);
                orderStatement.setString(3, deliveryDate);
                orderStatement.setString(4, "order_created");
                orderStatement.setInt(5, userId);
                orderStatement.setInt(6, productId);
                orderStatement.setInt(7, quantity);
                orderStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                int orderRowsAffected = orderStatement.executeUpdate();
                if (orderRowsAffected <= 0) {
                    throw new SQLException("Failed to insert into the 'orders' table");
                }
                // Provide a success message
                return "Order created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


}
