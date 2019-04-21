package com.example.exbooks.Screens.BookAddingScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.exbooks.model.Book;
import com.example.exbooks.model.ImageStorageService;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookAddingPresenterImpl  implements BookAddingContract.BookAddingPresenter {

    private FirebaseDatabase mFirebaseDatabase  ;
    private DatabaseReference mDatabaseReference ;
    ImageStorageService imageStorageService ;

    BookAddingContract.BookAddingView bookAddingView ;

    public BookAddingPresenterImpl(){

    }
    public BookAddingPresenterImpl(BookAddingContract.BookAddingView bookAddingView){
        this.bookAddingView = bookAddingView ;

    }

    @Override
    public void addBook(Book book){
        mFirebaseDatabase  = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("books");
        System.out.println("--------"+book.getImgUrl());
        String bookKey=mDatabaseReference.child(book.getCategory()).child(mDatabaseReference.push().getKey()).getKey();
        book.setBookID(bookKey);
        mDatabaseReference.child(book.getCategory()).child(bookKey).setValue(book);




    }

    @Override
    public void storeImageBitmap(Bitmap image_bitmap, String title, String title1) {
        imageStorageService = new ImageStorageService(this,title,title1);
        imageStorageService.execute(image_bitmap);

    }

    @Override
    public void setBook(String url ) {

        String titleText = bookAddingView.getBookTitle();
        String descText = bookAddingView.getDescription();
        String returnedPlaceName = bookAddingView.getReturnedPlaceName();
        LatLng returnedPlaceLatLng = bookAddingView.getReturnedPlaceLatLng();
        String categ = bookAddingView.getCategory();

        if(!titleText.trim().equals("") && !descText.trim().equals("")) {
            Book book = new Book();
            book.setTitle(titleText);
            book.setDescription(descText);
            book.setCategory(categ);
            book.setLocation(returnedPlaceName);
            String userUid = getUser();
            book.setUser(userUid);
            book.setImgUrl(url);
            book.setReturnedPlaceLat(returnedPlaceLatLng.latitude);
            book.setReturnedPlaceLong(returnedPlaceLatLng.longitude);
            System.out.println("imgurl inside adding :\n" + url);
            addBook(book);
        }else{
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getUid();

    }

    @Override
    public Context getContext() {
        return (Context) bookAddingView;
    }

}
