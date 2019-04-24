package com.example.exbooks.Screens.loginScreen;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.exbooks.R;
import com.example.exbooks.Screens.signupScreen.SignupScreen;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginScreen extends AppCompatActivity implements LoginContract.loginViewInterface {
    Button gmailSigninButton;
    Button  signUpButton;
    Button normalSigninButton;
    TextInputEditText emailTF;
    TextInputEditText passwordTF;
    LoginContract.loginPresenterInterface loginPresenterInterface;
    Animation fromBottom;
    Animation fromTop;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context;
        fromBottom=AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromBottom.setDuration(2000);
        fromTop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromTop.setDuration(1000);
        emailTF=findViewById(R.id.loginEmailTFID);
        passwordTF=findViewById(R.id.loginPasswordTFID);
        gmailSigninButton = findViewById(R.id.gmailLoginBtn);
        signUpButton=findViewById(R.id.signupBtnID);
        normalSigninButton=findViewById(R.id.normalLoginBtn);
        normalSigninButton.setAnimation(fromBottom);
        gmailSigninButton.setAnimation(fromBottom);
        emailTF.setAnimation(fromTop);
        passwordTF.setAnimation(fromTop);
        loginPresenterInterface = new LoginPresenterImpl(this);
        loginPresenterInterface.checkCurrentUser();

        normalSigninButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                normalSigninButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        normalSigninButton.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {

                return false;
            }
        });
        normalSigninButton.setOnClickListener((event)->{
            loginPresenterInterface.normalLogin(emailTF.getText().toString(),passwordTF.getText().toString());
        });
        gmailSigninButton.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                gmailSigninButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        gmailSigninButton.setOnClickListener((event)->{
            Intent signInIntent = loginPresenterInterface.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        signUpButton.setOnClickListener((event)->{
            Intent signupIntent=new Intent(this,SignupScreen.class);
            startActivity(signupIntent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            loginPresenterInterface.callThrowInOnActivityResult(data);
        }
    }
}
