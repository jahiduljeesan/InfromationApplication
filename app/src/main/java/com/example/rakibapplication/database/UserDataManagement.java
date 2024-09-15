package com.example.rakibapplication.database;

public class UserDataManagement {

    //entities
    private String fullName = "",firstName = "", lastName = "",password = "", firstPassword = "",confirmPassword = "",email = "";

    public UserDataManagement(String fullName, String firstName, String lastName, String password, String firstPassword, String confirmPassword, String email) {
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.firstPassword = firstPassword;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }



}
