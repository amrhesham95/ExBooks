package com.example.exbooks.Screens.loginScreen;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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
        if(email!=null && password!=null) {
            boolean checkEmail    = email.equals("")    || email.contains(" ");
            boolean checkPassword = password.equals("") || password.contains(" ");

            if(!checkEmail && !checkPassword) {
                loginAuth.normalSignin(email, password);
            }else{
                Toast.makeText(getContext(), "Invalid email and password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
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
