package com.xiaohei.auser.wenliapp.sp;

import android.content.Context;

/**
 * Created by Auser on 2018/4/10.
 */

public class StudentSpUtils {

    public static int getStudentId(Context context){
        return SetSpUtils.getSharedPreferences(context).getInt("studentid",-1);
    }

}
