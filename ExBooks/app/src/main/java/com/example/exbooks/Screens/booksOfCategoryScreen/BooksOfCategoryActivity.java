package com.example.exbooks.Screens.booksOfCategoryScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exbooks.R;
import com.example.exbooks.model.Book;

import java.util.ArrayList;

public class BooksOfCategoryActivity extends AppCompatActivity implements BooksOfCategoryContract.BooksOfCategoryViewInterface {
    private BooksOfCategoryContract.BooksOfCategoryPresenterInterface booksOfCategoryPresenterInterface;
    private RecyclerView categoryBooksRecyleView;
    private BooksOfCategoryAdapter categoryBooksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText filterET;
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
        //allBook=new ArrayList<>();
        //categoryBooksAdapter = new BooksOfCategoryAdapter(this,allBook,0);
        //categoryBooksRecyleView.setAdapter(categoryBooksAdapter);
        String categoryName=getIntent().getStringExtra("categoryName");
        booksOfCategoryPresenterInterface.setCategoryName(categoryName);
        filterET=(EditText)findViewById(R.id.filterET);
        filterET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    filter(s.toString());
            }

        });



    }

    private void filter(String text) {
        ArrayList<Book> filteredBooks=new ArrayList<>();
        if(allBook.size()==0)
            Toast.makeText(this, "The Books aren't loaded yet please wait", Toast.LENGTH_SHORT).show();
        else {
            for (Book book : allBook) {
                if (book.getTitle().toLowerCase().contains(text.toLowerCase()))
                    filteredBooks.add(book);
            }
            categoryBooksAdapter.filterList(filteredBooks);
        }

    }

    @Override
    public void setCategoryBookstoView(ArrayList<Book> books) {
        allBook=books;
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
