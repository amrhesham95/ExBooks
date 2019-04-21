package com.example.exbooks.Screens.BookAddingScreen;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.exbooks.model.Book;
import com.example.exbooks.model.ImageStorageService;
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
        this.bookAddingView.setBook(url);
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
