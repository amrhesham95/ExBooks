package com.example.exbooks.Screens.loginScreen;

import android.content.Intent;

import com.example.exbooks.Presenter;

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
