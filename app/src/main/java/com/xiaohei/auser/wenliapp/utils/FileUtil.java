package com.xiaohei.auser.wenliapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Auser on 2017/12/22.
 * 用于文件操作
 */

public class FileUtil {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void CheckFile(String filename){
        File file = new File(filename);
        // 判断文件目录是否存在
        if (!file.exists())
        {
            file.mkdir();
        }
    }

    public static String getFilePath(Activity activity){
        String filepath ="";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }else{
            filepath = activity.getFilesDir().getAbsolutePath();
        }
        return filepath;
    }

    public static void installApk(Activity activity)
    {
        File file = new File(getFilePath(activity),"download.apk");
        if (!file.exists())
        {
            return;
        }
        // 通过Intent安装APK文件
        Intent intent = new Intent("android.intent.action.VIEW");
        // 添加默认分类
        intent.addCategory("android.intent.category.DEFAULT");
        // 设置数据和类型 在文件中
        intent.setDataAndType(
                Uri.fromFile(file),
                "application/vnd.android.package-archive");
        // 如果开启的activity 退出的时候 会回调当前activity的onActivityResult
        activity.startActivityForResult(intent, 0);
    }

    //动态获取内存存储权限
    public static boolean verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
            return false;
        }else{
            return true;
        }
    }

    public static void delDownLaodFile(Activity activity){
        File file = new File(FileUtil.getFilePath(activity),"download.apk");
        if (file.exists()){
            file.delete();
            activity.finish();
        }
    }
}
