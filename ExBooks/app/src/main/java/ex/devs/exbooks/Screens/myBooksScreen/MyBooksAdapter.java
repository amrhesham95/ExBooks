package ex.devs.exbooks.Screens.myBooksScreen;

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

import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.bookDetailesScreen.BookDetailesActivity;
import ex.devs.exbooks.model.Book;

import java.util.ArrayList;

public class MyBooksAdapter extends RecyclerView.Adapter<MyBooksAdapter.MyBooksViewHolder> {
    public ArrayList<Book> books;
    Context mContext;

    public MyBooksAdapter(Context mContext, ArrayList<Book> books) {
        this.books = books;
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public MyBooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_card_layout, viewGroup   , false);
        MyBooksViewHolder myBooksViewHolder = new MyBooksViewHolder(view);
        return  myBooksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBooksViewHolder myBooksViewHolder, int i) {
        if(books.size()>0) {
            myBooksViewHolder.bookName.setText(books.get(i).getTitle());
            myBooksViewHolder.bookDescription.setText(books.get(i).getDescription());
        }
        myBooksViewHolder.bookName.setOnClickListener(new View.OnClickListener() {
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

    public class MyBooksViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookDescription;
        public MyBooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage=(ImageView) itemView.findViewById(R.id.book_image_oncardLayout);
            bookName=(TextView)itemView.findViewById(R.id.bookName_oncardLayout);
            //bookDescription=(TextView)itemView.findViewById(R.id.book_description_onCardLayout);
        }
    }
}
