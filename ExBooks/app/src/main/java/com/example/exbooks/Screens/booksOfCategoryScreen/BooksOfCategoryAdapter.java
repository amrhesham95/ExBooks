package com.example.exbooks.Screens.booksOfCategoryScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exbooks.R;
import com.example.exbooks.Screens.bookDetailesScreen.BookDetailesActivity;
import com.example.exbooks.model.Book;

import java.util.ArrayList;

public class BooksOfCategoryAdapter extends RecyclerView.Adapter<BooksOfCategoryAdapter.BooksCategoryViewHolder> {
    public ArrayList<Book> books;
    Context mContext;

    public BooksOfCategoryAdapter(Context mContext, ArrayList<Book> books) {
        this.books = books;
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public BooksCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_card_layout, viewGroup   , false);
        BooksCategoryViewHolder booksCategoryViewHolder = new BooksCategoryViewHolder(view);
        return  booksCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksCategoryViewHolder booksCategoryViewHolder, int i) {
        if(books.size()>0) {
            booksCategoryViewHolder.bookName.setText(books.get(i).getTitle());
            booksCategoryViewHolder.bookDescription.setText(books.get(i).getDescription());
        }
        booksCategoryViewHolder.bookName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity=(Activity)mContext;
                Intent intent = new Intent(activity, BookDetailesActivity.class);
                intent.putExtra("book",books.get(i));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BooksCategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookDescription;
        public BooksCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage=(ImageView) itemView.findViewById(R.id.book_image_oncardLayout);
            bookName=(TextView)itemView.findViewById(R.id.bookName_oncardLayout);
            bookDescription=(TextView)itemView.findViewById(R.id.book_description_onCardLayout);
        }
    }
}
