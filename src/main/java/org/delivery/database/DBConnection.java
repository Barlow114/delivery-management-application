package org.delivery.database;


import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {

            // Provide the database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/deliverymanagement";
            String username = "root";
            String password = "root";


            // Create the connection
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
