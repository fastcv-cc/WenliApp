package com.xiaohei.auser.wenliapp.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.xiaohei.auser.wenliapp.entity.Version;

/**
 * Created by Auser on 2018/4/12.
 * 用于退出时
 */

public class ExistDialog {

    public static void showUpDate(Activity activity , String mes, DialogInterface.OnClickListener listener_ok) {

        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("退出提示");
        builder.setMessage(mes);
        builder.setPositiveButton("确定", listener_ok);
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void showExist(Activity activity, DialogInterface.OnClickListener listener_ok) {

        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("退出提示");
        builder.setMessage("是否确认退出？");
        builder.setPositiveButton("确定", listener_ok);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
