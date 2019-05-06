package ex.devs.exbooks.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title ;
    private String category;
    private String description;
    private String location ;
    private String imgUrl ;
    private String user ;
    private double returnedPlaceLat;
    private double returnedPlaceLong;
    private String bookID;
   // FirebaseUser owner = null;

    public Book() {
    }

    public double getReturnedPlaceLat() {
        return returnedPlaceLat;
    }

    public void setReturnedPlaceLat(double returnedPlaceLat) {
        this.returnedPlaceLat = returnedPlaceLat;
    }

    public double getReturnedPlaceLong() {
        return returnedPlaceLong;
    }

    public void setReturnedPlaceLong(double returnedPlaceLong) {
        this.returnedPlaceLong = returnedPlaceLong;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    /*    public FirebaseUser getOwner() {
        return owner;
    }

    public void setOwner(FirebaseUser owner) {
        this.owner = owner;
    } */
}