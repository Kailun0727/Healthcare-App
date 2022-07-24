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

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommunityViewHolder> {

    Context context;
    ArrayList commentList;

    CommentAdapter(Context context){
        this.context = context;
    }

    public void updateCommentList(ArrayList commentList){
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.comment_row,parent,false);
        return  new CommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        holder.tv_comment.setText(String.valueOf(commentList.get(position)));
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comment;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }


    }


}
