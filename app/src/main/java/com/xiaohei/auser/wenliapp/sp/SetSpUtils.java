package com.xiaohei.auser.wenliapp.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Auser on 2018/4/14.
 */

public class SetSpUtils {

    private static SharedPreferences msharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void setSharedPreferences(Context activity, String key, int value){
        msharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        editor = msharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static void setSharedPreferences(Context activity, String key, String value){
        msharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        editor = msharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void setSharedPreferences(Context activity, String key, Boolean value){
        msharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        editor = msharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static SharedPreferences getSharedPreferences(Context activity){
        msharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        return msharedPreferences;
    }

}
