package com.example.exbooks.Screens;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.exbooks.R;
import com.example.exbooks.Screens.homeScreen.HomeActivity;
import com.example.exbooks.model.User;
import com.example.exbooks.model.UserDBService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AfterGmailActivity extends AppCompatActivity {
    TextInputEditText phoneTF;
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_gmail);
        phoneTF=findViewById(R.id.phoneGmailTFID);
        nextBtn=findViewById(R.id.nextBtnID);
        nextBtn.setOnClickListener((event)->{
            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            User user=new User(firebaseUser.getEmail(),phoneTF.getText().toString());
            UserDBService userDBService=new UserDBService();
            userDBService.addUser(user);
            Intent intent =new Intent (this,HomeActivity.class);
            startActivity(intent);
        });
    }
}
