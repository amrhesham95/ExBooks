package com.example.exbooks.Screens.signupScreen;

import android.content.Intent;

import com.example.exbooks.Preseneter;

public interface SignupContract {

    interface SignupViewInterface {
        String getEmail();
        String getPassword();
    }

    interface SignupPresenterInterface extends Preseneter {
        void signup (String email,String password,String phone);
    }
}

