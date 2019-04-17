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
import com.google.firebase.auth.PhoneAuthCredential;

public class SignupAuth {
    SignupContract.SignupPresenterInterface signupPresenterInterface;
    private FirebaseAuth mAuth;
    public SignupAuth(SignupContract.SignupPresenterInterface signupPresenterInterface) {
        this.signupPresenterInterface=signupPresenterInterface;
        mAuth=FirebaseAuth.getInstance();

    }

    public void NormalSignup(String email,String password,String phone){
        Log.i("myLog","inside NormalSignup");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) signupPresenterInterface.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("myLog","inside onComplete");
                            User user=new User(email,phone);
                            UserDBService userDBService=new UserDBService();
                            userDBService.addUser(user);
                            Log.i("myLog","Sign up successfully");
                            Toast.makeText(signupPresenterInterface.getContext(), "Sign up successfully", Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            ((Activity) signupPresenterInterface.getContext()).finish();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("myLog",task.getException().toString());
                            Toast.makeText(signupPresenterInterface.getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
        }

}
