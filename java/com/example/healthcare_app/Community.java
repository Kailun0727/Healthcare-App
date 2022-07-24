package com.example.healthcare_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Community extends AppCompatActivity {

    RecyclerView recyclerView;

    DBHandler dbHandler;

    ArrayList<String> t_id,t_tip,t_date;

    communityAdapter communityAdapter;

    String user_id, username;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = findViewById(R.id.recyclerView_tip);
        dbHandler = new DBHandler(Community.this);
        t_id = new ArrayList<>();
        t_tip = new ArrayList<>();
        t_date = new ArrayList<>();

        storeDataInArray();

        Intent i = getIntent();
        user_id = i.getStringExtra("id");
        username = i.getStringExtra("name");
        points = i.getIntExtra("points", 0);

        communityAdapter = new communityAdapter(Community.this,t_id,t_tip,t_date, user_id, username, points, dbHandler);

        recyclerView.setLayoutManager(new LinearLayoutManager(Community.this));
        recyclerView.setAdapter(communityAdapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }

    public void storeDataInArray(){
        Cursor c = dbHandler.readAllTip();

        if(c.getCount() == 0 ){
            Toast.makeText(Community.this,"No data.",Toast.LENGTH_SHORT).show();
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