package com.example.exbooks.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title ;
    private String category;
    private String description;
    private String location ;
    //private img ;
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

/*    public FirebaseUser getOwner() {
        return owner;
    }

    public void setOwner(FirebaseUser owner) {
        this.owner = owner;
    } */
}
