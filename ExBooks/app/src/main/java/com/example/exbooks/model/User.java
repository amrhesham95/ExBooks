package com.example.exbooks.model;

import java.io.Serializable;

public class User implements Serializable {
    String email;
    String phone;

    public User(){}
    public User(String email,String phone){
        this.email=email;
        this.phone=phone;
    }

    @Override
    public String toString() {
        return email + " , " + phone ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
