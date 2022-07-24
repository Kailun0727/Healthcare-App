package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {
    String id_str;
    String name;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clicked_profile(View view) {
        id_str = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        points = getIntent().getIntExtra("points",0);
        Intent i = new Intent(menu.this, profile.class);
        i.putExtra("id", id_str);
        i.putExtra("name", name);
        i.putExtra("points", points);
        startActivity(i);
    }

    public void clicked_community(View view) {
        id_str = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        points = getIntent().getIntExtra("points",0);
        Intent i = new Intent(menu.this,Community.class);
        i.putExtra("id", id_str);
        i.putExtra("name", name);
        i.putExtra("points", points);
        startActivity(i);
    }

    public void clicked_home(View view) {
        Intent i = new Intent(menu.this, userTip.class);
        startActivity(i);
    }
}