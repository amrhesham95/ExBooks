package com.example.exbooks.Screens.signupScreen;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.exbooks.R;

public class SignupScreen extends AppCompatActivity implements SignupContract.SignupViewInterface {
    TextInputEditText emailTF;
    TextInputEditText passwordTF;
    Button submitButton;
    SignupContract.SignupPresenterInterface signupPresenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen_small);
        emailTF=findViewById(R.id.emailTFID);
        passwordTF=findViewById(R.id.passwordTFID);
        submitButton=findViewById(R.id.submitBtnID);
        signupPresenterInterface=new SignupImpl(this);
        submitButton.setOnClickListener((event)->{
            signupPresenterInterface.signup(getEmail(),getPassword());
        });
    }

    @Override
    public String getEmail() {
        return emailTF.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordTF.getText().toString();
    }
}
