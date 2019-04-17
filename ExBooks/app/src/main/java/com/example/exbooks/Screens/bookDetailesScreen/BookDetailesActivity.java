package com.example.exbooks.Screens.bookDetailesScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exbooks.R;
import com.example.exbooks.model.Book;

public class BookDetailesActivity extends AppCompatActivity implements BookDetailesContract.BookDetailesViewInterface {
    BookDetailesContract.BookDetailesPresenterInterface bookDetailesPresenterInterface;
    ImageView bookImage;
    TextView bookTitle;
    TextView bookCategory;
    TextView bookDescription;
    TextView bookLocation;
    ImageView bookImgView ;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detailes);
        bookDetailesPresenterInterface=new BookDetailesPresenterImp(this);
        book = (Book)getIntent().getSerializableExtra("book");
        bookTitle=(TextView)findViewById(R.id.detailesBooktitle);
        bookCategory=(TextView)findViewById(R.id.detailesBookCategory);
        bookDescription=(TextView)findViewById(R.id.detailesBookDescription);
        bookLocation=(TextView)findViewById(R.id.detailesBookLocation);
        bookImgView = findViewById(R.id.bookImageInDetailesActivity);
        setDataOfBook();

    }

    private void setDataOfBook() {
        bookTitle.setText(book.getTitle());
        bookDescription.setText(book.getDescription());
        bookLocation.setText(book.getLocation());
        bookCategory.setText(book.getCategory());
        System.out.println("bookUrl->>>   "+book.getImgUrl());
        bookDetailesPresenterInterface.getBookImg(book.getImgUrl(),bookImgView);
    }
}
