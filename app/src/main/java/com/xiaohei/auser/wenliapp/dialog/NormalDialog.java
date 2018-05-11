package com.xiaohei.auser.wenliapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.teacherActivity.QueryClassRoomInfoActivity;

/**
 * Created by Auser on 2018/4/26.
 */

public class NormalDialog {

    public static void DialogOneListener(Context context, String title, String message, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定",listener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public static void DialogListener(final Activity context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void DialogListener(final Activity context, String title, String message,DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定",listener);
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void LongDialogListener(final Activity context,DialogInterface.OnClickListener listener){
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.student_man));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择：");
        builder.setAdapter(arrayadapter,listener);
        builder.create().show();
    }

}
