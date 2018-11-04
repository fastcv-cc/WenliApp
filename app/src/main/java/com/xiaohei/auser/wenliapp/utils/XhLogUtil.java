package com.xiaohei.auser.wenliapp.utils;

import android.util.Log;

/**
 * Created by machenike on 2018/9/17.
 * 说明 ： 此方法用于安卓调式
 * @param mswitch  用于发行时关闭输出信息
 * @param  TAG 调试的TAG
 */

public class XhLogUtil {

    private static boolean mswitch = false;
    private static String TAG = "HeiBug";

    public static void i(String mes){
        if(mswitch)
        Log.i(TAG,mes);
    }

    public static void w(String mes){
        if(mswitch)
        Log.w(TAG,mes);
    }

    public static void d(String mes){
        if(mswitch)
        Log.d(TAG,mes);
    }

    public static void e(String mes){
        if(mswitch)
        Log.e(TAG,mes);
    }

    public static void v(String mes){
        if(mswitch)
        Log.v(TAG,mes);
    }



}
