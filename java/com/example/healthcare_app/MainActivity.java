package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity{
    EditText username;
    EditText password;
    DBHandler dbHandler;
    Button insert,login;


//    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here is the important part
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
//        insert = findViewById(R.id.insertButton);
        login = findViewById(R.id.loginButton);
        dbHandler = new DBHandler(this);

//        boolean insertAmin = dbHandler.addAmin("admin","admin");
//        Toast.makeText(MainActivity.this,"Admin Inserted",Toast.LENGTH_SHORT).show();

        boolean addAdmin = dbHandler.addAmin("admin","admin");


        // creating a new dbhandler class
        // and passing our context to it.
    }

    public void clicked_login(View view) {

        String name = username.getText().toString();
        String ps = password.getText().toString();
        String lastCheckedIn = "";
        int id = 0;
        int point = 0;
        String id_str = "";

        //Read user part
        Cursor c = dbHandler.readUser(name, ps);

        if (name.equalsIgnoreCase("admin") && ps.equalsIgnoreCase("admin")) {
            Intent i = new Intent(MainActivity.this, adminMenu.class);
            startActivity(i);
            return;
        }

        if (c.getCount() == 1) {
            while (c.moveToNext()) {
                id = c.getInt(0);
                id_str = Integer.toString(id);
                point = c.getInt(3);
                lastCheckedIn = c.getString(4);//check in date column
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String lastCheckedInTime = lastCheckedIn;

            Date compareTime = new Date();

            {
                try {
                    compareTime = dateFormat.parse(lastCheckedInTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            Date currentTime = new Date();

            if (currentTime.compareTo(compareTime) > 0) {

                String str_today = dateFormat.format(currentTime);
                point += 1;
                boolean updateDate = dbHandler.updateUser(id_str, str_today, point);
                //connect to database compare time

                if (updateDate == true) {
                    Toast.makeText(this, "Checked in successfully. Point + 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Checked in unsuccessful", Toast.LENGTH_SHORT).show();
                }

            } else if (currentTime.compareTo(compareTime) == 0) {
                Toast.makeText(this, "You already checked in today. Please come back tomorrow. ", Toast.LENGTH_SHORT).show();
            }

            // Storing data into SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            myEdit.putString("name",name);

            myEdit.putString("id",id_str);

            myEdit.commit();


            Intent i = new Intent(MainActivity.this, menu.class);
            i.putExtra("name", name);
            i.putExtra("points", point);
            i.putExtra("id", id_str);
            startActivity(i);
            return;
        } else {
            Toast.makeText(MainActivity.this, "Invalid Username or Password!\nPlease try again!", Toast.LENGTH_SHORT).show();
        }

    }

    public void clicked_signup(View view) {
        Intent i = new Intent(MainActivity.this,signup.class);
        startActivity(i);
    }
}