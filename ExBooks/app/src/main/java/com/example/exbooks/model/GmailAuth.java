package com.example.exbooks.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.exbooks.R;
import com.example.exbooks.Screens.loginScreen.LoginContract;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GmailAuth {
    LoginContract.loginPresenterInterface loginPresenterInterface;
    private static final String FAILED_TAG="failed";
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    public GmailAuth(LoginContract.loginPresenterInterface loginPresenterInterface) {

        this.loginPresenterInterface=loginPresenterInterface;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken((loginPresenterInterface.getContext()).getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(loginPresenterInterface.getContext(), gso);
        mAuth=FirebaseAuth.getInstance();
    }



    public void throwInOnActivityResult(Intent data){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            Log.i(FAILED_TAG, "Google sign in failed", e);
            // ...
        }
    }




    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) loginPresenterInterface.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Intent intent = new Intent(loginPresenterInterface.getContext(), TestActivity.class);
                            loginPresenterInterface.getContext().startActivity(intent);
                            //FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginPresenterInterface.getContext(), "Google sign in failed", Toast.LENGTH_SHORT).show();
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
    }

    public Intent getSignInIntent(){
        return mGoogleSignInClient.getSignInIntent();
    }
}


