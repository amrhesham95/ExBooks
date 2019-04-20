package com.example.exbooks.Screens.booksOfCategoryScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.exbooks.R;
import com.example.exbooks.model.Book;

import java.util.ArrayList;

public class BooksOfCategoryActivity extends AppCompatActivity implements BooksOfCategoryContract.BooksOfCategoryViewInterface {
    private BooksOfCategoryContract.BooksOfCategoryPresenterInterface booksOfCategoryPresenterInterface;
    private RecyclerView categoryBooksRecyleView;
    private RecyclerView.Adapter categoryBooksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Book>allBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_of_category_screen);

        booksOfCategoryPresenterInterface= new BooksOfCategoryPresenterImp(this);
        categoryBooksRecyleView=(RecyclerView)findViewById(R.id.category_books_recycleView);
        categoryBooksRecyleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        categoryBooksRecyleView.setLayoutManager(layoutManager);
        allBook=new ArrayList<>();
        categoryBooksAdapter = new BooksOfCategoryAdapter(this,allBook,0);
        categoryBooksRecyleView.setAdapter(categoryBooksAdapter);
        String categoryName=getIntent().getStringExtra("categoryName");
        booksOfCategoryPresenterInterface.setCategoryName(categoryName);



    }

    @Override
    public void setCategoryBookstoView(ArrayList<Book> books) {
        categoryBooksAdapter = new BooksOfCategoryAdapter(this,books,0);
        categoryBooksRecyleView.setAdapter(categoryBooksAdapter);
    }

    @Override
    public void clearAdapter() {


    }

    @Override
    public void notifyChange() {

    }
}
