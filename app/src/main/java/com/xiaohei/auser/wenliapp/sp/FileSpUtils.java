package com.xiaohei.auser.wenliapp.sp;

import android.app.Activity;

/**
 * Created by Auser on 2018/4/11.
 */

public class FileSpUtils {

    public static boolean getInstallResult(Activity activity){
        return SetSpUtils.getSharedPreferences(activity).getBoolean("install",false);
    }

}
