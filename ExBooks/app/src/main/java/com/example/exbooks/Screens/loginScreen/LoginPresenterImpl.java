package com.example.exbooks.Screens.loginScreen;

public class LoginPresenterImpl implements LoginContract.loginPresenterInterface {
    LoginContract.loginViewInterface loginViewInterface;

    public LoginPresenterImpl(LoginContract.loginViewInterface loginViewInterface) {
        this.loginViewInterface=loginViewInterface;
    }
}
