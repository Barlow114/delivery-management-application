package org.delivery;

public class LoginSessionData {



    private String fullName;

    private  String email;

    private int userId;

    private String role;


    private String phoneNumber;
    private String truckNumber;
    private String truckCapacity;

    public LoginSessionData(){

    }

    public LoginSessionData(String fullName, String email, int userId, String role, String phoneNumber, String truckNumber, String truckCapacity) {
        this.fullName = fullName;
        this.email = email;
        this.userId = userId;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.truckNumber = truckNumber;
        this.truckCapacity = truckCapacity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckCapacity() {
        return truckCapacity;
    }

    public void setTruckCapacity(String truckCapacity) {
        this.truckCapacity = truckCapacity;
    }


    @Override
    public String toString() {
        return "LoginSessionData{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", truckNumber='" + truckNumber + '\'' +
                ", truckCapacity='" + truckCapacity + '\'' +
                '}';
    }
}
