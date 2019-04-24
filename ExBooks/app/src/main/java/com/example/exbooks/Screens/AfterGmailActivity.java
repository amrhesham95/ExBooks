package com.example.exbooks.Screens;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.exbooks.R;
import com.example.exbooks.Screens.homeScreen.HomeActivity;
import com.example.exbooks.Screens.loginScreen.LoginScreen;
import com.example.exbooks.model.User;
import com.example.exbooks.model.UserDBService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AfterGmailActivity extends AppCompatActivity {
    TextInputEditText phoneTF;
    Button nextBtn;
    Matcher matcher ;
    Pattern pattern ;
    String regex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_gmail);
        phoneTF=findViewById(R.id.phoneGmailTFID);
        nextBtn=findViewById(R.id.nextBtnID);
        nextBtn.setOnClickListener((event)->{
            String phoneText = phoneTF.getText().toString();
            if(phoneText!=null){
                    if(checkPhone(phoneText)){
                    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                    User user=new User(firebaseUser.getEmail(),phoneTF.getText().toString(),firebaseUser.getUid());
                    UserDBService userDBService=new UserDBService();
                    userDBService.addUser(user);
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else{
                        Toast.makeText(this, "Please enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(this, "Please Enter a Phone Number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkPhone(String phone) {
        regex = "01\\d{9}";

        // Create a Pattern object
        this.pattern = Pattern.compile(regex);
        // Now create matcher object.
        matcher = pattern.matcher(phone);
        return matcher.find() && phone.length() == 11;
    }

}
