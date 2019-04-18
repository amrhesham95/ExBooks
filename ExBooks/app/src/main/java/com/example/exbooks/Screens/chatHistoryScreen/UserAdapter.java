package com.example.exbooks.Screens.chatHistoryScreen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exbooks.R;
import com.example.exbooks.Screens.ChatScreen.MessageActivity;
import com.example.exbooks.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private  ArrayList<User> allUsers;
    public UserAdapter(Context context, ArrayList<User> users) {
        this.context=context;
        allUsers=users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        userViewHolder.usernameonChatsHistory.setText(allUsers.get(position).toString());
        userViewHolder.usernameonChatsHistory.setOnClickListener((event)->{
            Intent intent=new Intent(context, MessageActivity.class);
            intent.putExtra("userID",allUsers.get(position).getUserUID());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameonChatsHistory;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameonChatsHistory=(TextView)itemView.findViewById(R.id.usernameonChatsHistory);
        }
    }
}