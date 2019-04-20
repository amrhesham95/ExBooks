package com.example.exbooks.Screens.myBooksScreen;

import com.example.exbooks.model.Book;
import com.example.exbooks.model.BookDBService;

import java.util.ArrayList;

public class MyBooksPresenterImp implements MyBooksContract.MyBooksPresenterInerface {
    MyBooksContract.MyBooksViewInterface myBooksViewInterface;
    BookDBService bookDBService;
    ArrayList<Book> myBooks;

    public MyBooksPresenterImp(MyBooksContract.MyBooksViewInterface myBooksViewInterface) {
        this.myBooksViewInterface = myBooksViewInterface;
        bookDBService = new BookDBService(this);
    }

    @Override
    public void getMyBooks() {
        bookDBService.findByUser();


    }

    @Override
    public void setUseBooks(ArrayList<Book> myBooks) {
        myBooksViewInterface.setUserBooks(myBooks);
    }
}

