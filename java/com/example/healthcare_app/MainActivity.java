package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        boolean insertTip1 = dbHandler.addTip("Tip1","2022-06-18");

        boolean addAdmin = dbHandler.addAmin("admin","admin");

        if (addAdmin == true){
            Toast.makeText(MainActivity.this," Admin Inserted",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(MainActivity.this,"Admin Insert failed",Toast.LENGTH_SHORT).show();
        }


        // creating a new dbhandler class
        // and passing our context to it.



    }

    public void clicked_login(View view) {
        String name = username.getText().toString();
        String ps = password.getText().toString();
        String lastCheckedIn= "";
        int id= 0;
        int point=0;
        String id_str="";

        //Read user part
        Cursor c = dbHandler.readUser(name,ps);

        if(name.equalsIgnoreCase("admin") && ps.equalsIgnoreCase("admin")){
            Intent i = new Intent(MainActivity.this, adminMenu.class);
            startActivity(i);
            return;
        }

        if(c.getCount() == 1){
            while (c.moveToNext()){
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

            if(currentTime.compareTo(compareTime) > 0){

                String str_today = dateFormat.format(currentTime);
                point+=1;
                boolean updateDate = dbHandler.updateUser(id_str,str_today,point);
                //connect to database compare time

                if(updateDate == true){
                    Toast.makeText(this, "Checked in successfully. Point + 1", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Checked in unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
            else if(currentTime.compareTo(compareTime) == 0){
                Toast.makeText(this, "You already checked in today. Please come back tomorrow. ", Toast.LENGTH_SHORT).show();
            }






            Intent i = new Intent(MainActivity.this, menu.class);
            startActivity(i);
            return;
        }else{
            Toast.makeText(MainActivity.this,"Invalid Admin Username or Password!\nPlease try again!",Toast.LENGTH_SHORT).show();
        }


        //Read admin part
//        Cursor c = dbHandler.readAdmin(name,ps);
//
//        if(c.getCount() ==0){
//            Toast.makeText(MainActivity.this,"Invalid Admin Username or Password!\nPlease try again!",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else{
//            Intent i = new Intent(MainActivity.this,menu.class);
//            startActivity(i);
//        }


//        StringBuffer buffer = new StringBuffer();
//
//        while (c.moveToNext()){
//            buffer.append("Name :"+c.getString(1)+"\n");
//            buffer.append("Password :"+c.getString(2)+"\n");
//        }
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setCancelable(true);
//        builder.setTitle("User Entries");
//        builder.setMessage(buffer.toString());
//        builder.show();


//        String name = username.getText().toString();
//        String ps = password.getText().toString();
//
//        dbHandler.addUser(0,"kailun","kailun");
//
//        User u = dbHandler.readUser(name,ps);
//
//
//        if (u.getUsername() == name  && u.getPassword() == ps){
//            setContentView(R.layout.menu);
//        }
//        else{
//            Toast.makeText(MainActivity.this,"Invalid Account",Toast.LENGTH_SHORT);
//        }



    }




    public void clicked_signup(View view) {
        Intent i = new Intent(MainActivity.this,signup.class);
        startActivity(i);
    }
}