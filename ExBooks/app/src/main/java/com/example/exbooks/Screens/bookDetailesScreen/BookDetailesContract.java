package com.example.exbooks.Screens.bookDetailesScreen;

import android.widget.ImageView;

import com.example.exbooks.Presenter;

public interface BookDetailesContract {
    interface BookDetailesPresenterInterface extends Presenter
    {
        void getBookOwnerPhone(String bookOwnerUID);

        public void getBookImg(String imgUrl, ImageView bookImgView);
    }
    interface BookDetailesViewInterface
    {

        void setOwnerPhone(String phone);
    }
}
