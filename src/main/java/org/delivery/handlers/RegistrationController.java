package org.delivery.handlers;

import org.delivery.LoginSession;
import org.delivery.LoginSessionData;
import org.delivery.database.DBConnection;
import org.delivery.models.Registration;

import java.sql.*;

public class RegistrationController {

    //
    public static String insertData(Registration registration) {
        try {
            Connection connection = DBConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO users (fullname,email,password,phone_number,role,truck_number, truck_capacity) VALUES (?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders


                preparedStatement.setString(1, registration.getFullName());
                preparedStatement.setString(2, registration.getEmail());
                preparedStatement.setString(3, registration.getPassword());
                preparedStatement.setString(4, registration.getPhoneNumber());
                preparedStatement.setString(5, registration.getRole());
                preparedStatement.setString(6, registration.getTruckNumber());
                preparedStatement.setString(7, registration.getTruckCapacity() + "kg");

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                if (rowsAffected > 0) {
                    return "Registration Successful";
                } else {
                    return "Registration Failed\n  Try Again";
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }

    }



    public static String updateData(String fullname, String email, String phoneNumber, String role, String truckNumber, String truckCapacity, int userId) {
        try {
            Connection connection = DBConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "UPDATE users SET fullname = ?, email = ?, phone_number = ?, role = ?, truck_number = ?, truck_capacity = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, fullname);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phoneNumber);
                preparedStatement.setString(4, role);
                preparedStatement.setString(5, truckNumber);
                preparedStatement.setString(6, truckCapacity);
                preparedStatement.setInt(7, userId);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                if (rowsAffected > 0) {
                    // Prepare the SQL statement to fetch the updated data
                    String fetchSql = "SELECT * FROM users WHERE id = ?";
                    try (PreparedStatement fetchStatement = connection.prepareStatement(fetchSql)) {
                        fetchStatement.setInt(1, userId);
                        try (ResultSet resultSet = fetchStatement.executeQuery()) {
                            if (resultSet.next()) {
                                LoginSessionData sessionData = new LoginSessionData();
                                sessionData.setUserId(resultSet.getInt("id"));
                                sessionData.setEmail(resultSet.getString("email"));
                                sessionData.setFullName(resultSet.getString("fullname"));
                                sessionData.setRole(resultSet.getString("role"));
                                sessionData.setPhoneNumber(resultSet.getString("phone_number"));
                                sessionData.setTruckNumber(resultSet.getString("truck_number"));
                                sessionData.setTruckCapacity(resultSet.getString("truck_capacity"));
                                LoginSession.createSession("userInfo", sessionData);
                                return "User details updated successfully";
                            } else {
                                return "Update Failed\n Please Try Again";
                            }
                        }
                    }
                } else {
                    return "Update Failed\n Please Try Again";
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

}
