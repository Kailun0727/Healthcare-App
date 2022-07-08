package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clicked_profile(View view) {
        Intent i = new Intent(menu.this, userTip.class);
        startActivity(i);
    }

    public void clicked_community(View view) {
        Intent i = new Intent(menu.this, userTip.class);
        startActivity(i);
    }

    public void clicked_home(View view) {
        Intent i = new Intent(menu.this, userTip.class);
        startActivity(i);
    }
}