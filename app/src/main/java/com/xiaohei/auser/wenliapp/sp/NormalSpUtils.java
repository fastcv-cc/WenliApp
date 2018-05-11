package com.xiaohei.auser.wenliapp.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Auser on 2018/4/10.
 * 用于设置Sp
 */

public class NormalSpUtils {

    public static int getLoginType(Context context){
        return SetSpUtils.getSharedPreferences(context).getInt("logintype",0);
    }

    public static String getLoginNumber(Context context){
        return SetSpUtils.getSharedPreferences(context).getString("login_number","");
    }

}
