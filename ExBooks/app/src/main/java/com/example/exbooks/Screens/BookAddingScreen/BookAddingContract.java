package com.example.exbooks.Screens.BookAddingScreen;

import com.example.exbooks.model.Book;

public interface BookAddingContract {
    interface  BookAddingView{

    }

    interface  BookAddingPresenter{

        public void addBook(Book book);
    }

}
