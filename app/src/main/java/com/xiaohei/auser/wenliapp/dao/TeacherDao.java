package com.xiaohei.auser.wenliapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaohei.auser.wenliapp.db.DbOpenHelper;
import com.xiaohei.auser.wenliapp.entity.vo.TeachersVo;

/**
 * Created by Auser on 2018/4/27.
 */

public class TeacherDao {

    private static OperateDB operateDB = new OperateDB();

    public static TeachersVo getTeacher(Context context, int teacherid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getTeacher(db,teacherid);
    }

    public static String getTeacherCardId(Context context, int teacherid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getTeacherCardId(db,teacherid);
    }

    public static String getTeacherName(Context context, int teacherid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getTeacherName(db,teacherid);
    }

    public static void addTeacher(Context context, TeachersVo teachers){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.addTeacher(teachers,db);
    }

    public static void delTeacher(Context context, int teacherid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.deleteTeacher(db,teacherid);
    }



}
