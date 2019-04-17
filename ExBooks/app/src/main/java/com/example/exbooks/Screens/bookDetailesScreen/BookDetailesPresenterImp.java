package com.example.exbooks.Screens.bookDetailesScreen;

import android.content.Context;
import android.widget.ImageView;

import com.example.exbooks.model.ImageDownloaderService;

public class BookDetailesPresenterImp implements BookDetailesContract.BookDetailesPresenterInterface {
    BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface;
    ImageDownloaderService imageDownloaderService ;
    public BookDetailesPresenterImp(BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface) {
        this.bookDetailesViewInterface=bookDetailesViewInterface;
        imageDownloaderService = new ImageDownloaderService();
    }

    public void getBookImg(String imgUrl, ImageView bookImgView){
        imageDownloaderService.loadWithGlide(imgUrl,getContext(),bookImgView);
    }

    @Override
    public Context getContext() {
        return (Context) bookDetailesViewInterface;
    }


}
