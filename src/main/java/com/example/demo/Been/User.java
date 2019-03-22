package com.example.demo.Been;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String user_name;
    private String password;
    private String email;
    private int telephone;
    private String role;

    public User() {
    }

    public User(int id, String user_name, String password, String email, int telephone, String role) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", role='" + role + '\'' +
                '}';
    }
}
