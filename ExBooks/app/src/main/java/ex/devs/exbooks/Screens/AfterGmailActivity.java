package ex.devs.exbooks.Screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.homeScreen.HomeActivity;
import ex.devs.exbooks.model.User;
import ex.devs.exbooks.model.UserDBService;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AfterGmailActivity extends AppCompatActivity {
    TextInputEditText phoneTF;
    TextInputEditText verificationCodeTF;
    Button nextBtn;
    Button verifyBtn;
    Matcher matcher ;
    Pattern pattern ;
    String regex;
    String email;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;
    Context con;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_gmail);
        mAuth=FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("email");
        phoneTF=findViewById(R.id.phoneGmailTFID);
        nextBtn=findViewById(R.id.nextBtnID);
        verifyBtn=findViewById(R.id.verifyBtnID);
        verificationCodeTF = findViewById(R.id.verificationCodeTFID);
        verificationCodeTF.setVisibility(View.GONE);
        verifyBtn.setVisibility(View.GONE);
        nextBtn.setText("Send Verification Code");
        con =this;
        nextBtn.setOnClickListener((event)->{
            String phoneText = phoneTF.getText().toString();
            if(phoneText!=null){
                boolean checkPhone = !phoneText.isEmpty();

                if(checkPhone){
                    verificationCodeTF.setVisibility(View.VISIBLE);
                    verifyBtn.setVisibility(View.VISIBLE);
                    verifyBtn.setText("Next");
                    nextBtn.setVisibility(View.GONE);
                    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        @Override
                        public void onVerificationCompleted(PhoneAuthCredential credential) {
                            // This callback will be invoked in two situations:
                            // 1 - Instant verification. In some cases the phone number can be instantly
                            //     verified without needing to send or enter a verification code.
                            // 2 - Auto-retrieval. On some devices Google Play services can automatically
                            //     detect the incoming verification SMS and perform verification without
                            //     user action.
//                            Log.d(TAG, "onVerificationCompleted:" + credential);
                            if(verificationCodeTF.getText().toString().isEmpty()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verificationCodeTF.setText(credential.getSmsCode());
//                                        try {
//                                           wait(10);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
                                    }
                                });
//
                            }
                            signInWithPhoneAuthCredential(credential);
                        }

                        @Override
                        public void onVerificationFailed(FirebaseException e) {
                            // This callback is invoked in an invalid request for verification is made,
                            // for instance if the the phone number format is not valid.
//                            Log.w(TAG, "onVerificationFailed", e);
                            Toast.makeText(con, "Please enter a valid Phone Number", Toast.LENGTH_SHORT).show();

                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                // Invalid request
                                // ...
                            } else if (e instanceof FirebaseTooManyRequestsException) {
                                 // ...
                            }

                            // Show a message and update the UI
                            // ...
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId,
                                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            // The SMS verification code has been sent to the provided phone number, we
                            // now need to ask the user to enter the code and then construct a credential
                            // by combining the code with a verification ID.
//                            Log.d(TAG, "onCodeSent:" + verificationId);

                            // Save verification ID and resending token so we can use them later
//                            mVerificationId = verificationId;
//                            mResendToken = token;

                            // ...
//                            mCallbacks.onVerificationCompleted();
//                            signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(verificationId,henna el code ely gy mn l textfield ly hanzwdo));

                            //signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(verificationId,verificationCodeTF.getText().toString()));
                            mVerificationId = verificationId;
                            mResendToken = token;
                       //     Toast.makeText(AfterGmailActivity.this, verificationId, Toast.LENGTH_LONG).show();


                        }
                    };

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneTF.getText().toString(),        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            this,               // Activity (for callback binding)
                            mCallbacks);        // OnVerificationStateChangedCallbacks

                } else{
                        Toast.makeText(this, "Please enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(this, "Please Enter a Phone Number", Toast.LENGTH_SHORT).show();
            }
        });


        verifyBtn.setOnClickListener((event)->{
            String code = verificationCodeTF.getText().toString();
            if(code.isEmpty()){
                Toast.makeText(this, "Please enter the Verification Code", Toast.LENGTH_SHORT).show();
                return;
            }
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,code);
            mCallbacks.onVerificationCompleted(credential);
        //    signInWithPhoneAuthCredential(credential);
        });
    }

    public boolean checkPhone(String phone) {
     //   regex = "01\\d{9}";
        regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(phone);
        return matcher.find();
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");

//                            FirebaseUser user = task.getResult().getUser();


                            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                            User user =new User(firebaseUser.getEmail(),phoneTF.getText().toString(),firebaseUser.getUid());
                            UserDBService userDBService=new UserDBService();
                            userDBService.addUser(user);
                            Intent intent = new Intent(con, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AfterGmailActivity.this, "Invalid Verification Code", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
