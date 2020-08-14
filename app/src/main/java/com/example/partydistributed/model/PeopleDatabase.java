package com.example.partydistributed.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PeopleDatabase extends SQLiteOpenHelper {
    final public static String TABLE_NAME = "people";
    final public static String PEOPLE_ID = "people_id";
    final public static String PEOPLE_NAME = "people_name";

    public PeopleDatabase(Context context) {
        super(context, "people", null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(" +
                PEOPLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PEOPLE_NAME + " TEXT NOT NULL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
