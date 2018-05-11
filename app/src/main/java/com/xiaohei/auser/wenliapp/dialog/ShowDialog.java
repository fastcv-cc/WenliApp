package com.xiaohei.auser.wenliapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.studentActivity.StudentMainActivity;

/**
 * Created by Auser on 2018/4/16.
 */

public class ShowDialog {

    public static Dialog showLoadingDialog(Context content,String mes) {
            Dialog mLoadingDialog;
            View view = LayoutInflater.from(content).inflate(R.layout.layout_loading_dialog, null);
            TextView loadingText = (TextView) view.findViewById(R.id.text);
            loadingText.setText(mes);
            mLoadingDialog = new Dialog(content, R.style.loading_dialog_style);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            return mLoadingDialog;
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
