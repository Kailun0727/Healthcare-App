package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addTip extends AppCompatActivity {

    EditText tip_et;
    DBHandler dbHandler;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tip);
        tip_et = findViewById(R.id.tipEditText);
        dbHandler = new DBHandler(this);
        add = findViewById(R.id.addButton);
    }

    public void clicked_addTip(View view) {
        String tip = tip_et.getText().toString();

        if(!tip_et.getText().toString().isEmpty()) {
            Date currentDate = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String currentDate_str = dateFormat.format(currentDate);

            boolean insertTip = dbHandler.addTip(tip, currentDate_str);

            if (insertTip == true) {
                Toast.makeText(this, "Successful!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Tip cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }

}