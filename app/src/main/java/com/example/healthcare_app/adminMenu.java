package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void clicked_add(View view) {
        Intent i = new Intent(adminMenu.this,addTip.class);
        startActivity(i);
    }

    public void clicked_view(View view) {
        Intent i = new Intent(adminMenu.this,tip.class);
        startActivity(i);
    }
}