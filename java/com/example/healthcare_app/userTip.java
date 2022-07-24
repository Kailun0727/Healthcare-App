package com.example.healthcare_app;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userTip extends AppCompatActivity {
    RecyclerView recyclerView;

    DBHandler dbHandler;

    ArrayList<String> t_id,t_tip,t_date;

    TipAdapter tipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_user);

        recyclerView = findViewById(R.id.recyclerView_tip_user);
        dbHandler = new DBHandler(userTip.this);
        t_id = new ArrayList<>();
        t_tip = new ArrayList<>();
        t_date = new ArrayList<>();

        storeDataInArray();

        tipAdapter = new TipAdapter(userTip.this,t_id,t_tip,t_date);

        recyclerView.setAdapter(tipAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(userTip.this));
    }


    public void storeDataInArray(){
        Cursor c = dbHandler.readAllTip();

        if(c.getCount() == 0 ){
            Toast.makeText(userTip.this,"No data.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()){
                t_id.add(c.getString(0));
                t_tip.add(c.getString(1));
                t_date.add(c.getString(2));
            }
        }
    }
}
