package com.example.exbooks.Screens.booksOfCategoryScreen;

import com.example.exbooks.model.Book;

import java.util.ArrayList;

public interface BooksOfCategoryContract {
    interface BooksOfCategoryViewInterface
    {
       void setCategoryBookstoView(ArrayList<Book> books);
       void clearAdapter();
       void notifyChange();
    }
    interface BooksOfCategoryPresenterInterface
    {

        void setCategoryBooks(ArrayList<Book> books);
        void clearAdapter();
        void notifyChange();

        void setCategoryName(String categoryName);
    }
}
