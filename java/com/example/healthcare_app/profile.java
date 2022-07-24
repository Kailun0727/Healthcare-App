package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    int points;
    String getId;
    String name;
    String id;
    String getName;
    TextView name_tv;
    DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        name = sh.getString("name","");
        id = sh.getString("id","");

        getId = getIntent().getStringExtra("id");
        getName = getIntent().getStringExtra("name");
        name_tv = findViewById(R.id.name);
        name_tv.setText("Hi, " + name);
        dbhandler = new DBHandler(this);
    }

    public void clicked_community(View view) {
        Intent i = new Intent(profile.this, Community.class);
        startActivity(i);
    }

    public void clicked_points(View view) {

        Cursor c = dbhandler.readPoints(id);
        if (c.getCount() == 1){
            while (c.moveToNext()) {
                points = c.getInt(3);
            }
        }

        Toast.makeText(this,"You have " + Integer.toString(points) + " points. ", Toast.LENGTH_LONG).show();
    }

    public void clicked_menu(View view) {
        Intent i = new Intent(profile.this, menu.class);
        startActivity(i);
    }

    public void clicked_edit_profile(View view) {
        getId = getIntent().getStringExtra("id");
        Intent i = new Intent(profile.this, editProfile.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    public void clicked_sign_out(View view){
        Intent i = new Intent(profile.this, MainActivity.class);
        startActivity(i);
    }
}