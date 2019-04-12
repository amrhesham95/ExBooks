package com.example.exbooks.Screens.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.exbooks.R;

public class LoginScreen extends AppCompatActivity implements LoginContract.loginViewInterface{
    Button signInButton;
    LoginContract.loginPresenterInterface loginPresenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton=findViewById(R.id.button);
        loginPresenterInterface=new LoginPresenterImpl(this);
    }
}
