package com.example.exbooks.Screens.loginScreen;

import android.content.Context;
import android.content.Intent;

import com.example.exbooks.model.GmailAuth;
import com.example.exbooks.model.LoginAuth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImpl implements LoginContract.loginPresenterInterface {
    LoginContract.loginViewInterface loginViewInterface;
    GmailAuth gmailAuth;
    LoginAuth loginAuth;
    public LoginPresenterImpl(LoginContract.loginViewInterface loginViewInterface) {
        this.loginViewInterface=loginViewInterface;
        gmailAuth=new GmailAuth(this);
        loginAuth=new LoginAuth(this);


    }

//    @Override
//    public void loginGmail() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        GoogleSignInClient mGoogleSignInClient;
//        mGoogleSignInClient = GoogleSignIn.getClient((Context) loginViewInterface, gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount((Context) loginViewInterface);
//    }


    @Override
    public void normalLogin(String email, String password) {
        loginAuth.normalSignin(email,password);
    }

    @Override
    public void callThrowInOnActivityResult(Intent data) {
        gmailAuth.throwInOnActivityResult(data);
    }

    @Override
    public Intent getSignInIntent() {
        return gmailAuth.getSignInIntent();
    }

    @Override
    public void checkCurrentUser() {
        gmailAuth.checkCurrentUser();
    }

    @Override
    public Context getContext() {
        return (Context) loginViewInterface;
    }

}
