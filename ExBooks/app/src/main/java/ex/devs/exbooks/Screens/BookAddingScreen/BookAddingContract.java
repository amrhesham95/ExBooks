package ex.devs.exbooks.Screens.BookAddingScreen;

import android.graphics.Bitmap;

import ex.devs.exbooks.Presenter;
import ex.devs.exbooks.model.Book;
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
