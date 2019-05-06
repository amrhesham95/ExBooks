package ex.devs.exbooks.Screens.booksOfCategoryScreen;

import ex.devs.exbooks.model.Book;
import ex.devs.exbooks.model.BookDBService;


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
