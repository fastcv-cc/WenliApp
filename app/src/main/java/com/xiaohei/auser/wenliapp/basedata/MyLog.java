package com.xiaohei.auser.wenliapp.basedata;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Created by Auser on 2018/5/6.
 * 用于打印log
 */

public class MyLog {
    public static void Log(Context context,String TAG,String mes){
        if(isApkDebugable(context)){
            Log.d(TAG, "Log: "+mes);
        }
    }

    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }

}
