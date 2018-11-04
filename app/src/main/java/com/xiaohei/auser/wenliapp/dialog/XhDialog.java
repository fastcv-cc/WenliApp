package com.xiaohei.auser.wenliapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;

/**
 * Created by machenike on 2018/10/16.
 * 用于各种dialog的布局实现
 */

public class XhDialog {

    public static Dialog showLoadingDialog(Context content, String mes) {
        Dialog mLoadingDialog;
        View view = LayoutInflater.from(content).inflate(R.layout.layout_loading_dialog, null);
        TextView loadingText = (TextView) view.findViewById(R.id.text);
        loadingText.setText(mes);
        mLoadingDialog = new Dialog(content, R.style.loading_dialog_style);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return mLoadingDialog;
    }

    public static void CallPhoneDialogWithListener(final Activity context, DialogInterface.OnClickListener listener){
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.call_xl));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择：");
        builder.setAdapter(arrayadapter,listener);
        builder.create().show();
    }

    public static void DialogNoCancleWithListener(final Activity context, String title, String message,DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定",listener);
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void DialogWithListener(Context context, String title, String message, DialogInterface.OnClickListener listener){
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

    public static void showStudentFunctionDialogListener(final Activity context,DialogInterface.OnClickListener listener){
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.student_man));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择：");
        builder.setAdapter(arrayadapter,listener);
        builder.create().show();
    }

    public static void showErweimaDialog(Context content) {
        Dialog mErweimaDialog;
        View view = LayoutInflater.from(content).inflate(R.layout.layout_erweima_dialog, null);
        mErweimaDialog = new Dialog(content, R.style.loading_dialog_style);
        mErweimaDialog.setCancelable(true);
        mErweimaDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mErweimaDialog.show();
    }

}
