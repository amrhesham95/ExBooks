package ex.devs.exbooks.Screens.bookDetailesScreen;

import android.widget.ImageView;

import ex.devs.exbooks.Presenter;

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
