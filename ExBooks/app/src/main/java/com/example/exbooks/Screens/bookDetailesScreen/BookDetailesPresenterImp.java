package com.example.exbooks.Screens.bookDetailesScreen;

public class BookDetailesPresenterImp implements BookDetailesContract.BookDetailesPresenterInterface {
    BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface;
    public BookDetailesPresenterImp(BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface) {
        this.bookDetailesViewInterface=bookDetailesViewInterface;
    }
}
