package ex.devs.exbooks.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import ex.devs.exbooks.Screens.AfterGmailActivity;
import ex.devs.exbooks.Screens.homeScreen.HomeActivity;
import ex.devs.exbooks.Screens.homeScreen.HomeContract;
import ex.devs.exbooks.Screens.loginScreen.LoginContract;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAuth {
    LoginContract.loginPresenterInterface loginPresenterInterface;
    HomeContract.HomePresenterInterface homePresenterInterface;
    private FirebaseAuth mAuth;
    public LoginAuth(HomeContract.HomePresenterInterface homePresenterInterface) {
        this.homePresenterInterface=homePresenterInterface;
        mAuth=FirebaseAuth.getInstance();

    }

    public LoginAuth(LoginContract.loginPresenterInterface loginPresenterInterface) {
        this.loginPresenterInterface=loginPresenterInterface;
        mAuth=FirebaseAuth.getInstance();

    }

    public void normalSignin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) loginPresenterInterface.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(loginPresenterInterface.getContext(), AfterGmailActivity.class);
                            intent.putExtra("email",email);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            loginPresenterInterface.getContext().startActivity(intent);
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("log", task.getException().toString(), task.getException());
                            Toast.makeText(loginPresenterInterface.getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
    public void signOut(){
        mAuth.signOut();

    }
}
