package com.example.exbooks.Screens.homeScreen;

import android.content.Context;
import android.content.Intent;

import com.example.exbooks.Screens.loginScreen.LoginScreen;
import com.example.exbooks.model.BookDBService;
import com.example.exbooks.model.GmailAuth;
import com.example.exbooks.model.LoginAuth;

public class HomePresenterImp implements HomeContract.HomePresenterInterface {
   HomeContract.HomeViewInterface homeViewInterface;
    LoginAuth loginAuth;
    public HomePresenterImp(HomeContract.HomeViewInterface homeViewInterface) {
        this.homeViewInterface = homeViewInterface;
        loginAuth=new LoginAuth(this);

    }

    @Override
    public void signOut() {
        loginAuth.signOut();
        Intent intent = new Intent(this.getContext(), LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.getContext().startActivity(intent);
    }

    @Override
    public Context getContext() {
        return (Context) homeViewInterface;
    }
}
