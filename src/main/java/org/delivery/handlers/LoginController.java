package org.delivery.handlers;

import org.delivery.LoginSession;
import org.delivery.LoginSessionData;
import org.delivery.database.DBConnection;
import org.delivery.models.AuthModel;


import java.sql.*;

public class LoginController {

    public static String Login(AuthModel loginModel){


        try {
            Connection connection = DBConnection.getConnection();
            // Prepare the SQL statement with placeholders
            String sql = "SELECT * FROM users WHERE email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the placeholders
                preparedStatement.setString(1, loginModel.getEmail());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Step 2: Hash the entered password
                        String storedHashedPassword = resultSet.getString("password");
                        boolean passwordCheck =  storedHashedPassword.equals(loginModel.getPassword());
                        // Step 3: Compare hashes for authentication
                        if (passwordCheck) {
                            LoginSessionData sessionData = new LoginSessionData();
                            sessionData.setUserId(resultSet.getInt("id"));
                            sessionData.setEmail(resultSet.getString("email"));
                            sessionData.setFullName(resultSet.getString("fullname"));
                            sessionData.setRole(resultSet.getString("role"));
                            sessionData.setPhoneNumber(resultSet.getString("phone_number"));
                            sessionData.setTruckNumber(resultSet.getString("truck_number"));
                            sessionData.setTruckCapacity(resultSet.getString("truck_capacity"));
                            LoginSession.createSession("userInfo", sessionData);
                            return "Authentication Successful";
                        } else {
                            return "Login Failed incorrect credientials";
                        }
                    } else {
                        System.out.println("User not found");
                        return "Login Failed User";
                    }
                }
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

}
