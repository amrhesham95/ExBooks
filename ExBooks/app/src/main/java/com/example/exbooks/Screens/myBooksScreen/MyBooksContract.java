package com.example.exbooks.Screens.myBooksScreen;

import com.example.exbooks.model.Book;

import java.util.ArrayList;

public interface MyBooksContract {
    interface MyBooksViewInterface
    {

        void setUserBooks(ArrayList<Book> myBooks);
    }
    interface MyBooksPresenterInerface
    {

        void getMyBooks();

        void setUseBooks(ArrayList<Book> myBooks);
    }
}
