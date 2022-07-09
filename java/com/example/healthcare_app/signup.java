package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class signup extends AppCompatActivity {
    EditText username,password,confirmPassword;
    boolean signup = true;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        dbHandler = new DBHandler(this);
    }

    public void clicked_signupButton(View view) {
        String name = username.getText().toString();
        String ps = password.getText().toString();
        String confirmPs = confirmPassword.getText().toString();

        //Restrict user cannot register an admin account
        if (username.getText().toString().equalsIgnoreCase("admin") || password.getText().toString().equalsIgnoreCase("admin")
         || confirmPassword.getText().toString().equalsIgnoreCase("admin")){
            Toast.makeText(signup.this,"Username or Password cannot be admin!",Toast.LENGTH_SHORT).show();
            signup = false;
        }

        //validate input
        if (username.getText().toString().isEmpty()){
            Toast.makeText(signup.this,"Username cannot be empty!",Toast.LENGTH_SHORT).show();
            signup = false;
        }

        if(password.getText().toString().isEmpty()){
            Toast.makeText(signup.this,"Password cannot be empty!",Toast.LENGTH_SHORT).show();
            signup = false;
        }

        if(confirmPassword.getText().toString().isEmpty()){
            Toast.makeText(signup.this,"Confirm Password cannot be empty!",Toast.LENGTH_SHORT).show();
            signup = false;
        }

        if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty()){
            Toast.makeText(signup.this,"All fields cannot be empty!",Toast.LENGTH_SHORT).show();
            signup = false;
        }

        if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            Toast.makeText(signup.this,"Password and confirm password must be same!",Toast.LENGTH_SHORT).show();
            signup = false;
        }



        if (signup == true){
            String newUsername = username.getText().toString();
            String newPs = password.getText().toString();

            Date currentDate = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String currentDate_str = dateFormat.format(currentDate);

            boolean checkInsert = dbHandler.addUser(newUsername,newPs,1,currentDate_str);

            if (checkInsert == true){
                Toast.makeText(signup.this,"Your account has been successfully created!",Toast.LENGTH_SHORT).show();
            }
        }

        Intent i = new Intent(signup.this,MainActivity.class);
        startActivity(i);

    }

    public void cliked_signin(View view) {
        Intent i = new Intent(signup.this,MainActivity.class);
        startActivity(i);
    }
}