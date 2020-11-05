package ex.devs.exbooks.Screens.chatHistoryScreen;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ex.devs.exbooks.R;
import ex.devs.exbooks.ui.chat.view.MessageActivity;
import ex.devs.exbooks.model.User;

import java.util.concurrent.CopyOnWriteArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private  CopyOnWriteArrayList allUsers;
    private int hideTheEmail;
    public UserAdapter(Context context, CopyOnWriteArrayList<User> users) {
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
        hideTheEmail=allUsers.get(position).toString().lastIndexOf("@");
        userViewHolder.usernameonChatsHistory.setText(allUsers.get(position).toString().substring(0,hideTheEmail));
        userViewHolder.cardView.setOnClickListener((event)->{
            Intent intent=new Intent(context, MessageActivity.class);
            intent.putExtra("userID",((User)(allUsers.get(position))).getUserUID());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        userViewHolder.usernameonChatsHistory.setOnClickListener((event)->{

        });

    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameonChatsHistory;
        CardView cardView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameonChatsHistory=(TextView)itemView.findViewById(R.id.usernameonChatsHistory);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
