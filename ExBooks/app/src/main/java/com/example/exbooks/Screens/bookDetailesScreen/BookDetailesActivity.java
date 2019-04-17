package com.example.exbooks.Screens.bookDetailesScreen;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exbooks.MessageActivity;
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
    Button chatBtn;
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
        chatBtn=findViewById(R.id.chat_btn);
        setDataOfBook();
        Button callBtn = findViewById(R.id.call_btn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01121668135"));
                startActivity(intent);
            }
        });
        chatBtn.setOnClickListener((event)->{
            Intent intent=new Intent(this, MessageActivity.class);
            intent.putExtra("userEmail",book.getUser());
            startActivity(intent);
        });

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
