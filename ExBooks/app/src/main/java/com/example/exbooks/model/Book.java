package com.example.exbooks.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Book implements Serializable {

    private String title ;
    private String category;
    private String description;
    private String location ;
    private String imgUrl ;
    private User user ;
   // FirebaseUser owner = null;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
/*    public FirebaseUser getOwner() {
        return owner;
    }

    public void setOwner(FirebaseUser owner) {
        this.owner = owner;
    } */
}
