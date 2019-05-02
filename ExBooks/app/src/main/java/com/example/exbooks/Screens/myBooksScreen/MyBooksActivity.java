package com.example.exbooks.Screens.myBooksScreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.exbooks.R;
import com.example.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryAdapter;
import com.example.exbooks.Screens.homeScreen.CategoryAdapter;
import com.example.exbooks.model.Book;

import java.util.ArrayList;

public class MyBooksActivity extends AppCompatActivity implements MyBooksContract.MyBooksViewInterface{
    MyBooksContract.MyBooksPresenterInerface myBooksPresenterInerface;
    private RecyclerView myBooksRecyleView;
    private RecyclerView.Adapter categoryBooksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Book>myBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mybooktoolbar);
        toolbar.setTitle("My books");
        setSupportActionBar(toolbar);
        myBooksPresenterInerface= new MyBooksPresenterImp(this);
        myBooksRecyleView=(RecyclerView)findViewById(R.id.category_books_recycleView);
        myBooksRecyleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myBooksRecyleView.setLayoutManager(layoutManager);
        myBooks=new ArrayList<>();
        myBooksPresenterInerface.getMyBooks();



    }

    @Override
    public void setUserBooks(ArrayList<Book> myBooks) {
        categoryBooksAdapter=new BooksOfCategoryAdapter(this,myBooks,1);
        myBooksRecyleView.setAdapter(categoryBooksAdapter);
    }
}
