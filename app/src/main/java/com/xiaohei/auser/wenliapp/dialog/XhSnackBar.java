package com.xiaohei.auser.wenliapp.dialog;

import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by machenike on 2018/10/16.
 * 用于snackbar功能
 */

public class XhSnackBar {

    public static void showResultWithAction(RelativeLayout layout, String mes, final Action action) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_LONG)
                .setAction("点击设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.actionMethod();
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();
    }

    public static void showResultWithAction(RelativeLayout layout, String mes,String actionMes, final Action action) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_LONG)
                .setAction(actionMes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.actionMethod();
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();
    }

    public static void showResultWithAction(LinearLayout layout, String mes, final Action action) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_LONG)
                .setAction("点击设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.actionMethod();
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();
    }

    public static void showResultWithAction(LinearLayout layout, String mes,String actionMes, final Action action) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_LONG)
                .setAction(actionMes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.actionMethod();
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();
    }

    public interface Action{
        void actionMethod();
    }

    public static void showResult(RelativeLayout layout, String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    public static void showResultWithNetERROR(RelativeLayout layout) {
        Snackbar.make(layout, "连接服务器失败", Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    public static void showResultWithNetERROR(LinearLayout layout) {
        Snackbar.make(layout, "服务器离家出走了", Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    public static void showResult(LinearLayout layout, String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }
}
