package com.example.healthcare_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.TipViewHolder> {

    Context context;
    ArrayList t_tip,t_date;

    TipAdapter(Context context,
               ArrayList t_id,
               ArrayList t_tip,
               ArrayList t_date){
        this.context = context;

        this.t_tip = t_tip;
        this.t_date = t_date;
    }



    @NonNull
    @Override
    public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tip_row,parent,false);
        return  new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {

        holder.t_tip_tv.setText(String.valueOf(t_tip.get(position)));
        holder.t_date_tv.setText(String.valueOf(t_date.get(position)));
    }

    @Override
    public int getItemCount() {
        return t_date.size();
    }

    public class TipViewHolder extends RecyclerView.ViewHolder{

        TextView t_id_tv,t_tip_tv,t_date_tv;

        public TipViewHolder(@NonNull View itemView) {
            super(itemView);

            t_tip_tv = itemView.findViewById(R.id.t_tip);
            t_date_tv = itemView.findViewById(R.id.t_date);
        }
    }
}
