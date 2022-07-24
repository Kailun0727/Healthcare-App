package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class editProfile extends AppCompatActivity {
    String getId;
    EditText username;
    EditText password;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        dbHandler = new DBHandler(this);
    }

    public void clicked_save_profile(View view) {
        getId = getIntent().getStringExtra("id");
        String name = username.getText().toString();
        String pass = password.getText().toString();
        boolean update = true;

        if(username.getText().toString().isEmpty() == true && password.getText().toString().isEmpty() == true){
            Toast.makeText(this,"Username and password cannot be empty. ", Toast.LENGTH_SHORT).show();

            update = false;
        }

        if (update == true) {
            if(username.getText().toString().isEmpty() == false && password.getText().toString().isEmpty() == true) {
                boolean editProfile = dbHandler.updateUsername(getId, name);

                if(editProfile == true) {
                    Toast.makeText(this,"Profile updated successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Profile failed to update. Please try again. ", Toast.LENGTH_SHORT).show();
                }
            }
            else if(username.getText().toString().isEmpty() == true && password.getText().toString().isEmpty() == false) {
                boolean editProfile = dbHandler.updatePassword(getId, pass);

                if(editProfile == true) {
                    Toast.makeText(this,"Profile updated successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Profile failed to update. Please try again. ", Toast.LENGTH_SHORT).show();
                }
            }
            else if(username.getText().toString().isEmpty() == false && password.getText().toString().isEmpty() == false) {
                boolean editProfile = dbHandler.updateUsernameAndPassword(getId, name, pass);

                if(editProfile == true) {
                    Toast.makeText(this,"Profile updated successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Profile failed to update. Please try again. ", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}