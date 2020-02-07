package ex.devs.exbooks.Screens.loginScreen;

import android.content.Intent;

import ex.devs.exbooks.Presenter;

public interface LoginContract {
    interface loginViewInterface{

    }

    interface  loginPresenterInterface extends Presenter {
        void normalLogin(String email,String password);
        void callThrowInOnActivityResult(Intent data);
        Intent getSignInIntent();
        public void checkCurrentUser();
    }
}
