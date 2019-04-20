package com.example.exbooks.model;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryContract;
import com.example.exbooks.Screens.myBooksScreen.MyBooksContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class BookDBService {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    BooksOfCategoryContract.BooksOfCategoryPresenterInterface booksOfCategoryPresenterInterface;
    MyBooksContract.MyBooksPresenterInerface myBooksPresenterInerface;
    public BookDBService() {
        myRef = database.getReference("books");

    }



    public BookDBService(BooksOfCategoryContract.BooksOfCategoryPresenterInterface booksOfCategoryPresenterInterface) {
        this.booksOfCategoryPresenterInterface = booksOfCategoryPresenterInterface;
        myRef = database.getReference("books");
    }

    public BookDBService(MyBooksContract.MyBooksPresenterInerface myBooksPresenterInerface) {
        this.myBooksPresenterInerface = myBooksPresenterInerface;
        myRef = database.getReference("books");
    }

    public void addBook(Book book){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("books");
        myRef.child(book.getCategory()).child(myRef.push().getKey()).setValue(book);
    }


    public void findByCategory(String category){
        ArrayList<Book> books=new ArrayList<>();
        //myRef=database.getReference(category);

        myRef.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            //String value = dataSnapshot.getValue(String.class);
            booksOfCategoryPresenterInterface.clearAdapter();
            Book book=new Book();
            for(DataSnapshot data:dataSnapshot.getChildren()){
                books.add(data.getValue(Book.class));
            }
            booksOfCategoryPresenterInterface.setCategoryBooks(books);
            booksOfCategoryPresenterInterface.notifyChange();
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });


}

    public  ArrayList<Book> findByLocation(String location){
        ArrayList<Book> books=new ArrayList<>();
        return books;
    }

    public  ArrayList<Book> findByName(String name){
        ArrayList<Book> books=new ArrayList<>();
        return books;
    }
    public  void findByUser(){
        ArrayList<Book> myBooks=new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                 for(DataSnapshot data:snapshot.getChildren()) {
                     Book book = data.getValue(Book.class);

                     String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                     String bookUser = book.getUser();
                     if (book.getUser().equals(fuser)) {
                         myBooks.add(book);
                     }
                 }
                }
                myBooksPresenterInerface.setUseBooks(myBooks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void deleteBook(Book book) {
       String key= myRef.child(book.getCategory()).push().getKey();
        myRef.child(book.getCategory()).child(key).removeValue();
    }

}
