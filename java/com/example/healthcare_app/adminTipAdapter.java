package com.example.healthcare_app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adminTipAdapter extends RecyclerView.Adapter<adminTipAdapter.adminTipViewHolder> {

    Context context;
    ArrayList t_id,t_tip,t_date;
    Activity activity;

    int position;

    adminTipAdapter(Activity activity,
                    Context context,
                   ArrayList t_id,
                   ArrayList t_tip,
                   ArrayList t_date){
        this.activity = activity;
        this.context = context;
        this.t_id = t_id;
        this.t_tip = t_tip;
        this.t_date = t_date;
    }



    @NonNull
    @Override
    public adminTipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tip_row,parent,false);
        return  new adminTipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminTipViewHolder holder, int position) {
        this.position = position;
        holder.t_tip_tv.setText(String.valueOf(t_tip.get(position)));
        holder.t_date_tv.setText(String.valueOf(t_date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,updateTip.class);
                i.putExtra("id",String.valueOf(t_id.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return t_date.size();
    }

    public class adminTipViewHolder extends RecyclerView.ViewHolder{

        TextView t_id_tv,t_tip_tv,t_date_tv;

        LinearLayout mainLayout;

        public adminTipViewHolder(@NonNull View itemView) {
            super(itemView);
            t_tip_tv = itemView.findViewById(R.id.t_tip);
            t_date_tv = itemView.findViewById(R.id.t_date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
