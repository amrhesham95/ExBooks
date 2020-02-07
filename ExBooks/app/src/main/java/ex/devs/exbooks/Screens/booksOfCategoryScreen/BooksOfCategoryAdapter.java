package ex.devs.exbooks.Screens.booksOfCategoryScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.bookDetailesScreen.BookDetailesActivity;
import ex.devs.exbooks.model.Book;
import ex.devs.exbooks.model.BookDBService;
import ex.devs.exbooks.model.ImageDownloaderService;

import java.util.ArrayList;

public class BooksOfCategoryAdapter extends RecyclerView.Adapter<BooksOfCategoryAdapter.BooksCategoryViewHolder> {
    public ArrayList<Book> books;
    Context mContext;
    int type;
    BookDBService bookDBService;
    public BooksOfCategoryAdapter(Context mContext, ArrayList<Book> books,int type) {
        this.books = books;
        this.mContext=mContext;
        this.type=type;
        bookDBService = new BookDBService();



    }


    @NonNull
    @Override
    public BooksCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_card_layout, viewGroup   , false);
        view.setOnClickListener((event)->{

        });
        BooksCategoryViewHolder booksCategoryViewHolder = new BooksCategoryViewHolder(view);
        return  booksCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksCategoryViewHolder booksCategoryViewHolder, int i) {
        if(books.size()>0) {
            booksCategoryViewHolder.bookName.setText(books.get(i).getTitle());
            //booksCategoryViewHolder.bookDescription.setText(books.get(i).getDescription());
            System.out.println(books.get(i).getTitle());
            ImageDownloaderService imageDownloaderService = new ImageDownloaderService();
            imageDownloaderService.loadWithGlide(books.get(i).getImgUrl(),mContext,booksCategoryViewHolder.bookImage);
        }

        booksCategoryViewHolder.bookName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        booksCategoryViewHolder.booksOfCatCard.setOnClickListener(event->
        {
            Activity activity=(Activity)mContext;
            Intent intent = new Intent(activity, BookDetailesActivity.class);
            intent.putExtra("book",books.get(i));
            activity.startActivity(intent);
        });

        booksCategoryViewHolder.bookName.setOnClickListener(event->
        {
            Activity activity=(Activity)mContext;
            Intent intent = new Intent(activity, BookDetailesActivity.class);
            intent.putExtra("book",books.get(i));
            activity.startActivity(intent);
        });

        booksCategoryViewHolder.bookImage.setOnClickListener(event->
        {
            Activity activity=(Activity)mContext;
            Intent intent = new Intent(activity, BookDetailesActivity.class);
            intent.putExtra("book",books.get(i));
            activity.startActivity(intent);
        });


        booksCategoryViewHolder.deletBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDBService.deleteBook(books.get(i));
                books.remove(i);

                notifyItemRemoved(i);
                notifyItemRangeChanged(i, books.size());

            }
        });
    }


    @Override
    public int getItemCount() {
        return books.size();
    }
    public void filterList(ArrayList<Book> filteredBooks)
    {
        books=filteredBooks;
        notifyDataSetChanged();
    }

    public class BooksCategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookDescription;
        Button deletBookBtn;
        CardView booksOfCatCard;

        public BooksCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage=(ImageView) itemView.findViewById(R.id.book_image_oncardLayout);
            bookName=(TextView)itemView.findViewById(R.id.bookName_oncardLayout);
            //bookDescription=(TextView)itemView.findViewById(R.id.book_description_onCardLayout);
            deletBookBtn=(Button)itemView.findViewById(R.id.deleteBookBtn);
            booksOfCatCard=(CardView)itemView.findViewById(R.id.book_card);
            if(type==0){
                deletBookBtn.setVisibility(View.GONE);
            }
        }
    }

}
