package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateTip extends AppCompatActivity {

    EditText tip_et;
    Button update_btn;
    String id;
    String tip;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tip);

        tip_et = findViewById(R.id.updateTipEditText);
        update_btn = findViewById(R.id.updateButton);

        dbHandler = new DBHandler(this);


    }

    public void clicked_updateTip(View view) {
        id = getIntent().getStringExtra("id");

        tip = tip_et.getText().toString();

        boolean updateTip = dbHandler.updateTip(id,tip);

        if(updateTip == true){
            Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Failed to update",Toast.LENGTH_SHORT).show();
        }
    }

}