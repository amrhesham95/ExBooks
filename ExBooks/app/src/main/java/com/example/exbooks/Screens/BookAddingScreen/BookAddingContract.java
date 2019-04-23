package com.example.exbooks.Screens.BookAddingScreen;

import android.graphics.Bitmap;

import com.example.exbooks.Presenter;
import com.example.exbooks.model.Book;
import com.google.android.gms.maps.model.LatLng;

public interface BookAddingContract {
    interface  BookAddingView{
        String getBookTitle();

        String getDescription();

        String getCategory();

        LatLng getReturnedPlaceLatLng();

        String getReturnedPlaceName();

    }

    interface  BookAddingPresenter extends Presenter {

        public void addBook(Book book);

        public void storeImageBitmap(Bitmap image_bitmap, String title, String title1);

        public void setBook(String url);
        public String getUser();

    }

}
