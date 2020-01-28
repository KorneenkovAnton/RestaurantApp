package com.example.myapplication.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "restaurant.db";
    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS token(name VARCHAR(30) UNIQUE,token VARCHAR(255) UNIQUE)";
    public static final String SQL_DROP = "DROP TABLE IF EXISTS token";

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        onCreate(db);
    }
}
