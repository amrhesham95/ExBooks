package com.example.exbooks.Screens.BookAddingScreen;

import android.graphics.Bitmap;

import com.example.exbooks.Presenter;
import com.example.exbooks.model.Book;

public interface BookAddingContract {
    interface  BookAddingView{
        public void setBook(String url);
    }

    interface  BookAddingPresenter extends Presenter {

        public void addBook(Book book);

        public void storeImageBitmap(Bitmap image_bitmap, String title, String title1);

        public void setBook(String url);

    }

}
