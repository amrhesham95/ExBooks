package com.example.exbooks.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDBService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

    public void addUser(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef.child(firebaseUser.getUid()).setValue(user);
    }


}
