package com.example.healthcare_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "healthcaredb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USER_TABLE = "user";

    private static final String ADMIN_TABLE = "admin";

    private static final String COMMUNITY_TABLE = "community";



    //User Section
    //U = User
    // below variable is for our id column.
    private static final String U_ID_COL = "id";

    // below variable is for our course name column
    private static final String U_NAME_COL = "name";

    // below variable is for our  password column
    private static final String U_PASSWORD_COL = "password";

    private static final String U_POINT_COL = "point";

    // below variable is for our image column
    private static final String U_IMAGE_COL = "name";



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
                + U_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + U_NAME_COL + " TEXT,"
                + U_PASSWORD_COL + " TEXT,"
                + U_PASSWORD_COL + " INTEGER,"
                + U_IMAGE_COL + " BLOB DEFAULT X'00')";

        String createAdminTable = "CREATE TABLE " + ADMIN_TABLE + "("
                + A_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + A_NAME_COL + " TEXT,"
                + A_PASSWORD_COL + " TEXT)";

        String createCommunityTable = "CREATE TABLE " + COMMUNITY_TABLE + "("
                + C_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CU_NAME_COL + " TEXT,"
                + CU_ID_COL+ " TEXT,"
                + C_TYPE_COL + " TEXT)";

        db.execSQL(createUserTable);
        db.execSQL(createAdminTable);
        db.execSQL(createCommunityTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COMMUNITY_TABLE);
        onCreate(db);
    }
}
