package com.example.youtubectask;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context) {
        super(context, "youTube_app_db11", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE USERS (UID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, password TEXT, name TEXT, phone_number TEXT);";
        String CREATE_VIDEOS_TABLE = "CREATE TABLE VIDEOS (VID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "video_id TEXT,link TEXT);";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
        sqLiteDatabase.execSQL(CREATE_VIDEOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERS;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS VIDEOS;");
        onCreate(sqLiteDatabase);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("name", user.getName());
        long row = db.insert("USERS", null, contentValues);
        db.close();
        return row;
    }
    public int getUser(String uname, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String rawQuery = "SELECT * FROM USERS WHERE username LIKE '%" + uname + "%' AND password LIKE '%" + pass + "%'";
        Cursor cursor = db.rawQuery(rawQuery, null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("UID"));
                cursor.close();
                return id;
            }
        }
        cursor.close();
        return 0;
    }
    public long addVideo(String videoID,String YURL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("video_id", videoID);
        contentValues.put("link", YURL);
        long row = db.insert("VIDEOS", null, contentValues);
        Log.v("row",String.valueOf(row));
        db.close();
        return row;
    }
    public List<Videos> getVideos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String rawQuery = "SELECT * FROM VIDEOS";
        Cursor cursor = db.rawQuery(rawQuery, null);
        List<Videos> Video = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Videos V = new Videos(cursor.getString(1),cursor.getString(2));
                Video.add(V);
                cursor.moveToNext();
            } while(!cursor.isAfterLast());
        }
        cursor.close();
        Log.v("len",String.valueOf(Video.size()));

        return Video;
    }
}
