package com.xiaohei.auser.wenliapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaohei.auser.wenliapp.db.DbOpenHelper;
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;

/**
 * Created by Auser on 2018/4/24.
 * 用于操作数据库SQLite
 */

public class StudentDao {

    private static OperateDB operateDB = new OperateDB();

    public static String getStudentName(Context context,int studentid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getStudentName(db, studentid);
    }

    public static int getStudentRoomId(Context context,int studentid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getStudentRoomId(db, studentid);
    }

    public static StudentsVo getStudent(Context context,int studentid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getStudent(db,studentid);
    }

    public static void addStuden(Context context, StudentsVo complete){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.addStudent(complete,db);
    }

    public static void delStuden(Context context, int studentid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.deleteStudent(db,studentid);
    }

    public static void updateStuden(Context context, StudentsVo complete){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.updataStudent(db,complete);
    }

    public static void addWeek(Context context, WeeksText weeksText){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.addWeek(weeksText,db);
    }

    public static void delWeek(Context context,int roomid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.deleteWeek(db,roomid);
    }

    public static void updateWeek(Context context, WeeksText weeksText){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        operateDB.updataWeek(db,weeksText);
    }

    public static WeeksText getWeek(Context context,int roomid){
        DbOpenHelper helper = new DbOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        return operateDB.getWeek(db,roomid);
    }

}
