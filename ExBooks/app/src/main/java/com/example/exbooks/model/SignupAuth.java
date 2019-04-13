package com.example.exbooks.model;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.exbooks.Screens.loginScreen.LoginContract;
import com.example.exbooks.Screens.signupScreen.SignupContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupAuth {
    SignupContract.SignupPresenterInterface signupPresenterInterface;
    private FirebaseAuth mAuth;
    public SignupAuth(SignupContract.SignupPresenterInterface signupPresenterInterface) {
        this.signupPresenterInterface=signupPresenterInterface;
        mAuth=FirebaseAuth.getInstance();

    }

    public void NormalSignup(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) signupPresenterInterface.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("log","Sign up successfully");
                            Toast.makeText(signupPresenterInterface.getContext(), "Sign up successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            ((Activity) signupPresenterInterface.getContext()).finish();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("log",task.getException().toString());
                            Toast.makeText(signupPresenterInterface.getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
        }

}
