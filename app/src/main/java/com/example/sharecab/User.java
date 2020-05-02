package com.example.sharecab;

public class User {
    private String name,email,phone_no;

    public User(){

    }

    public User(String name, String email, String phone_no) {
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }
}
