package com.example.exbooks.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class BookDBService {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("books");
    public BookDBService() {

    }

    public void addBook(Book book){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("books");
        myRef.child(book.getCategory()).child(myRef.push().getKey()).setValue(book);
    }

    public ArrayList<Book>findByCategory(String category){
        ArrayList<Book> books=new ArrayList<>();
        //myRef=database.getReference(category);
        myRef.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            //String value = dataSnapshot.getValue(String.class);
            Book book=new Book();
            for(DataSnapshot data:dataSnapshot.getChildren()){
                books.add(data.getValue(Book.class));
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });

        return books;
}

    ArrayList<Book> findByLocation(String location){
        ArrayList<Book> books=new ArrayList<>();
        return books;
    }

    ArrayList<Book> findByName(String name){
        ArrayList<Book> books=new ArrayList<>();
        return books;
    }



}
