package ex.devs.exbooks.Screens.myBooksScreen;

import android.content.Context;

import ex.devs.exbooks.model.Book;
import ex.devs.exbooks.model.BookDBService;

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

    @Override
    public Context getContext() {
        return (Context) myBooksViewInterface;
    }
}

