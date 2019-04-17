package com.example.exbooks.Screens.BookAddingScreen;

import com.example.exbooks.model.Book;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookAddingPresenterImpl  implements BookAddingContract.BookAddingPresenter {

    private FirebaseDatabase mFirebaseDatabase  ;
    private DatabaseReference mDatabaseReference ;

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
        mDatabaseReference.child(book.getCategory()).child(mDatabaseReference.push().getKey()).setValue(book);



    }
}
