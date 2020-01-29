package com.example.myapplication.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TokenService extends Service {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public TokenService() {
        dbHelper = DBHelper.getInstance(this);
        database = dbHelper.getReadableDatabase();
    }

    public void deleteTokens(){
        database.delete("token",null,null);
    }

    public void saveTokens(String refreshToken, String accessToken){
        ContentValues row1 = new ContentValues();
        ContentValues row2 = new ContentValues();
        row1.put("name","accessToken");
        row1.put("token",accessToken);
        row2.put("name","refreshToken");
        row2.put("token",refreshToken);
        int id = (int) database.insertWithOnConflict("token",null,row1,SQLiteDatabase.CONFLICT_IGNORE);
        if(id == -1){
            database.update("token",row1,"name=?",new String[]{"accessToken"});
            database.update("token",row2,"name=?",new String[]{"refreshToken"});
            System.out.println("Tokens updated");
        }else {
            database.insertWithOnConflict("token",null,row2,SQLiteDatabase.CONFLICT_IGNORE);
            System.out.println("New token add");
        }
    }

    public String getAccessToken(){
        String token = "";
        Cursor cursor = database.rawQuery("SELECT token FROM token WHERE name =?",new String[]{"accessToken"});
        if(cursor.moveToNext()){
            token = cursor.getString(0); 
        }
        cursor.close();
        return token;
    }

    public String getRefreshToken(){
        String token = "";
        Cursor cursor = database.rawQuery("SELECT token FROM token WHERE name =?",new String[]{"refreshToken"});
        if(cursor.moveToNext()) {
            token = cursor.getString(0);
        }
        cursor.close();
        return token;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        database.close();
    }
}
