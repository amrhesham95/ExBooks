package com.example.exbooks.Screens.chatHistoryScreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.exbooks.R;
import com.example.exbooks.model.Chat;
import com.example.exbooks.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatsActivity extends AppCompatActivity {
    private RecyclerView chats_recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdapter userAdapter;
    FirebaseUser fuser;
    DatabaseReference reference;
    ArrayList<User> usersList;
    ArrayList<String> uidList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        chats_recycleView=(RecyclerView)findViewById(R.id.chats_recycler_view);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        chats_recycleView.setLayoutManager(layoutManager);
        chats_recycleView.setHasFixedSize(true);
        uidList= new ArrayList<>();
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                uidList.clear();
                for(DataSnapshot snapshot :dataSnapshot.getChildren())
                {
                    Chat chat=snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(fuser.getUid()))
                    {
                        uidList.add(chat.getSender());
                    }
                    if(chat.getSender().equals(fuser.getUid()))
                    {
                        uidList.add(chat.getReceiver());
                    }
                }
                readChatsHistory();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void readChatsHistory()
    {
        usersList=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for(DataSnapshot snapshot :dataSnapshot.getChildren())
                {
                    User userFromSnapShot = snapshot.getValue(User.class);

                    for(String uid:uidList)
                    {
                        if(userFromSnapShot.getUserUID().equals(uid))
                        {
                            if(usersList.size()!=0) {
                                for (User currUser : usersList) {
                                    if(!userFromSnapShot.getEmail().equals(currUser.getEmail())){
                                        usersList.add(userFromSnapShot);
                                    }
                                }
                            }else{
                                usersList.add(userFromSnapShot);
                            }
                        }
                    }
                }
                userAdapter = new UserAdapter(getApplicationContext(),usersList);
                chats_recycleView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
