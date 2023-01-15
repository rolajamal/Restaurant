package com.example.restaurant;

public class User {
    private String user_name;
    private String user_img;
    private String user_email;
    private String user_password;
    private String user_id;

    public User() {
    }

    public User(String user_name, String user_img, String user_email, String user_password, String user_id) {
        this.user_name = user_name;
        this.user_img = user_img;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
