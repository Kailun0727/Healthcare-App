package com.example.healthcare_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class communityAdapter extends RecyclerView.Adapter<communityAdapter.CommunityViewHolder> {

    Context context;
    DBHandler dbHandler;
    ArrayList t_id, t_tip,t_date;
    String user_id, username;
    int points;

    communityAdapter(Context context,
               ArrayList t_id,
               ArrayList t_tip,
               ArrayList t_date,
                     String user_id, String username, int points, DBHandler dbHandler){
        this.context = context;
        this.t_id = t_id;
        this.t_tip = t_tip;
        this.t_date = t_date;
        this.user_id = user_id;
        this.username = username;
        this.points = points;
        this.dbHandler = dbHandler;
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_community_row,parent,false);
        return  new CommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        holder.t_tip_tv.setText(String.valueOf(t_tip.get(position)));
        holder.t_date_tv.setText(String.valueOf(t_date.get(position)));

        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, commentPage.class);
                i.putExtra("tip_id", String.valueOf(t_id.get(holder.getAdapterPosition())));
                i.putExtra("tip", String.valueOf(t_tip.get(holder.getAdapterPosition())));
                i.putExtra("date", String.valueOf(t_date.get(holder.getAdapterPosition())));
                i.putExtra("user_id", user_id);
                i.putExtra("username", username);
                i.putExtra("points", points);
                context.startActivity(i);
            }
        });

        holder.supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.addTipSupport(String.valueOf(t_id.get(holder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return t_date.size();
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {

        TextView t_tip_tv, t_date_tv;
        ImageButton commentBtn, supportBtn;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            t_tip_tv = itemView.findViewById(R.id.t_tip);
            t_date_tv = itemView.findViewById(R.id.t_date);
            commentBtn = itemView.findViewById(R.id.commentPageButton);
            supportBtn = itemView.findViewById(R.id.supportButton);
        }


    }


}
