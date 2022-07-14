package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    int getPoints;
    String getId;
    String getName;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getName = getIntent().getStringExtra("name");
        name = findViewById(R.id.name);
        name.setText("Hi, " + getName);
    }

    public void clicked_community(View view) {
        Intent i = new Intent(profile.this, menu.class);
        startActivity(i);
    }

    public void clicked_points(View view) {
        getPoints = getIntent().getIntExtra("points", 0);
        Toast.makeText(this,"You have " + Integer.toString(getPoints) + " points. ", Toast.LENGTH_LONG).show();
    }

    public void clicked_menu(View view) {
        Intent i = new Intent(profile.this, menu.class);
        startActivity(i);
    }

    public void clicked_edit_profile(View view) {
        getId = getIntent().getStringExtra("id");
        Intent i = new Intent(profile.this, editProfile.class);
        i.putExtra("id", getId);
        startActivity(i);
    }
}