package com.example.healthcare_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "healthcaredb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USER_TABLE = "User";

    private static final String TIP_TABLE = "Tip";

    private static final String ADMIN_TABLE = "Admin";

    private static final String COMMUNITY_TABLE = "Community";



    //User Section
    //U = User
    // below variable is for our id column.
    private static final String U_ID_COL = "id";

    // below variable is for our course name column
    private static final String U_NAME_COL = "name";

    // below variable is for our  password column
    private static final String U_PASSWORD_COL = "password";

    private static final String U_POINT_COL = "point";

    private static final String U_LASTCHECKEDIN_COL = "lastCheckedIn";


    //Tip Section
    //T = Tip
    private static final String T_ID_COL = "_id";

    private static final String T_TIP_COL = "tip";

    private static final String T_DATE_COL = "date";


    //Admin Section
    // A = Admin
    private static final String A_ID_COL = "id";

    // below variable is for our course name column
    private static final String A_NAME_COL = "name";

    // below variable is for our password column
    private static final String A_PASSWORD_COL = "password";


    //Community Section
    // C = Community, CU = Community User
    private static final String C_ID_COL = "id";

    private static final String CU_NAME_COL = "userName";

    private static final String CU_ID_COL = "userID";

    private static final String C_TYPE_COL = "type";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + "("
                + U_ID_COL + " TEXT, "
                + U_NAME_COL + " TEXT,"
                + U_PASSWORD_COL + " TEXT)";

        String createAdminTable = "CREATE TABLE " + ADMIN_TABLE + "("
                + A_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + A_NAME_COL + " TEXT,"
                + A_PASSWORD_COL + " TEXT)";

        String createCommunityTable = "CREATE TABLE " + COMMUNITY_TABLE + "("
                + C_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CU_NAME_COL + " TEXT,"
                + CU_ID_COL+ " TEXT,"
                + C_TYPE_COL + " TEXT)";

        db.execSQL("create Table User(_id INTEGER primary key ,name TEXT, password TEXT,point INTEGER,lastCheckedIn TEXT)");
        db.execSQL("create Table Admin(_id INTEGER primary key ,name TEXT, password TEXT)");
        db.execSQL("create Table Tip(_id INTEGER primary key ,tip TEXT, date TEXT)");
        db.execSQL("create Table Community(id INTEGER primary key AUTOINCREMENT,cu_name TEXT, cu_id TEXT, cu_type TEXT)");

    }



    //Handle user data
    public boolean addUser(String username,String password,int point,String lastCheckedIn){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(U_NAME_COL, username);
        values.put(U_PASSWORD_COL, password);
        values.put(U_POINT_COL,point);
        values.put(U_LASTCHECKEDIN_COL,lastCheckedIn);

        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(USER_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readUser(String username, String password){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM User WHERE name = ? AND password =?", new String[]{username,password});
//        User u = new User();

//        if(c.getCount()>0){
//            c.moveToFirst();
//            u.setId(c.getInt(c.getColumnIndex(U_ID_COL)));
//            u.setUsername(c.getString(c.getColumnIndex(U_NAME_COL)));
//            u.setPassword(c.getString(c.getColumnIndex(U_PASSWORD_COL)));
//
//        }

        return c;
    }

    public Cursor readPoints(String id){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM User WHERE _id =?", new String[]{id});

        return c;
    }

    boolean updateUser(String id,String lastCheckedIn,int point){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_LASTCHECKEDIN_COL, lastCheckedIn);

        values.put(U_POINT_COL,point);


        long result = db.update(USER_TABLE,values,"_id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateUsername(String id, String username){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_NAME_COL, username);

        long result = db.update(USER_TABLE,values,"_id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updatePassword(String id, String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_PASSWORD_COL, password);

        long result = db.update(USER_TABLE,values,"_id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateUsernameAndPassword(String id, String username, String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_NAME_COL, username);

        values.put(U_PASSWORD_COL, password);

        long result = db.update(USER_TABLE,values,"_id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean addAmin(String username,String password){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(A_NAME_COL, username);
        values.put(A_PASSWORD_COL, password);


        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(ADMIN_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readAdmin(String username, String password){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Admin WHERE name = ? AND password =?", new String[]{username,password});

        return c;
    }

    public boolean addTip(String tip,String date){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(T_TIP_COL, tip);
        values.put(T_DATE_COL, date);


        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(TIP_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateTip(String id,String tip){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String currentDate_str = dateFormat.format(currentDate);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();



        values.put(T_TIP_COL, tip);


        long result = db.update(TIP_TABLE,values,"_id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readAllTip(){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Tip ", null);

        return c;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COMMUNITY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TIP_TABLE);
        onCreate(db);
    }
}
