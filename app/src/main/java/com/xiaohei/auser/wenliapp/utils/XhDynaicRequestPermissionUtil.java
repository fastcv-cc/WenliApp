package com.xiaohei.auser.wenliapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by machenike on 2018/10/16.
 * 用于动态获取权限
 */

public class XhDynaicRequestPermissionUtil {

    //动态获取拨打电话的权限
    private static final int REQUEST_CALL_PHONE = 2;
    private static String[] PERMISSIONS_CALLPHONE= {
            Manifest.permission.CALL_PHONE,
    };

    public static boolean callPhonePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CALL_PHONE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_CALLPHONE,
                    REQUEST_CALL_PHONE);
            return true;
        } else {
            return true;
        }
    }

}
