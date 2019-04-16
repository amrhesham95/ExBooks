package com.example.exbooks.Screens.loginScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.example.exbooks.Preseneter;

public interface LoginContract {
    interface loginViewInterface{

    }

    interface  loginPresenterInterface extends Preseneter {
        void normalLogin(String email,String password);
        void callThrowInOnActivityResult(Intent data);
        Intent getSignInIntent();
        public void checkCurrentUser();
    }
}
