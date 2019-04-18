package com.example.exbooks.model;

import java.io.Serializable;

public class User implements Serializable {

    String email;
    String phone;
    String userUID;
    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public User(){}
    public User(String email,String phone,String userUID){
        this.email=email;
        this.phone=phone;
        this.userUID=userUID;
    }

    @Override
    public String toString() {
        return email;
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
