package com.xiaohei.auser.wenliapp.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Auser on 2018/4/23.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context) {
        super(context, "wenli", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE students" +
                "(" +
                "student_id INT ," +
                "student_card_id VARCHAR(10) ," +
                "student_name VARCHAR(10) ," +
                "student_password VARCHAR(50) ," +
                "department_id INT ," +
                "department_name VARCHAR(30) ," +
                "class_id INT ," +
                "class_name VARCHAR(30) ," +
                "builds_name VARCHAR(30) ," +
                "room_id INT ," +
                "room_name VARCHAR(30) "+
                ");";
        db.execSQL(sql);

        String sql_week="CREATE TABLE weeks" +
                "(" +
                "roomid INT ," +
                "study INT ," +
                "health INT ," +
                "return_home INT ," +
                "sleep_condition INT ," +
                "mood INT ," +
                "consume INT ," +
                "love_lose INT ," +
                "condition_text NVARCHAR(500) " +
                ");";
        db.execSQL(sql_week);

        String sql_tea="CREATE TABLE teachers" +
                "(" +
                "teacher_id INT ," +
                "teacher_name VARCHAR(30) ," +
                "teacher_cardid VARCHAR(30) ," +
                "teacher_password VARCHAR(30) ," +
                "department_name VARCHAR(30) " +
                ");";
        db.execSQL(sql_tea);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase getWriteConnection(){
        SQLiteDatabase db=getWritableDatabase();
        return db;
    }

    public SQLiteDatabase getRedeConnection(){
        SQLiteDatabase db=getReadableDatabase();
        return db;
    }

    public void close(SQLiteDatabase db){
        db.close();
    }
}
