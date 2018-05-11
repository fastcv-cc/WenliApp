package com.xiaohei.auser.wenliapp.sp;

import android.content.Context;

/**
 * Created by Auser on 2018/4/10.
 */

public class TeacherSpUtils {

    public static int getTeacherId(Context context){
        return SetSpUtils.getSharedPreferences(context).getInt("teacherid",-1);
    }
}
