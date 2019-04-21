package com.example.exbooks.Screens.signupScreen;

import com.example.exbooks.Presenter;

public interface SignupContract {

    interface SignupViewInterface {
        String getEmail();
        String getPassword();
    }

    interface SignupPresenterInterface extends Presenter {
        void signup (String email,String password,String phone);

        boolean checkPhone(String phone);
    }
}

