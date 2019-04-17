package com.example.exbooks.Screens.booksOfCategoryScreen;

import android.support.annotation.NonNull;

import com.example.exbooks.model.Book;
import com.example.exbooks.model.BookDBService;


import java.util.ArrayList;

public class BooksOfCategoryPresenterImp implements BooksOfCategoryContract.BooksOfCategoryPresenterInterface {
    BookDBService bookDBService;
    ArrayList<Book> catgeoryBooks;
    BooksOfCategoryContract.BooksOfCategoryViewInterface booksOfCategoryInterface;

    public BooksOfCategoryPresenterImp(BooksOfCategoryContract.BooksOfCategoryViewInterface booksOfCategoryInterface) {
        this.booksOfCategoryInterface=booksOfCategoryInterface;
        bookDBService = new BookDBService(this);
        bookDBService.findByCategory("horror");
    }
    public void setCategoryBooks(ArrayList<Book> books)
    {
        catgeoryBooks=books;
        booksOfCategoryInterface.setCategoryBookstoView(books);

    }

    @Override
    public void clearAdapter() {
        booksOfCategoryInterface.clearAdapter();
    }

    @Override
    public void notifyChange() {
        booksOfCategoryInterface.notifyChange();
    }

    @Override
    public void setCategoryName(String categoryName) {
        bookDBService.findByCategory(categoryName);
    }

}
