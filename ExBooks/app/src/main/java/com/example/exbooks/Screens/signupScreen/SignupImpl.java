package com.example.exbooks.Screens.signupScreen;

import android.content.Context;

import com.example.exbooks.model.SignupAuth;

public class SignupImpl implements SignupContract.SignupPresenterInterface {
    SignupContract.SignupViewInterface signupViewInterface;
    SignupAuth normalAuth;

    public SignupImpl(SignupContract.SignupViewInterface signupViewInterface) {
        this.signupViewInterface=signupViewInterface;
        normalAuth=new SignupAuth(this);
    }


    @Override
    public void signup(String email, String password,String phone) {
        normalAuth.NormalSignup(email,password,phone);
    }

    @Override
    public Context getContext() {
        return (Context) signupViewInterface;
    }
}
