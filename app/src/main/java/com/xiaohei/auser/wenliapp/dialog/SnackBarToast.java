package com.xiaohei.auser.wenliapp.dialog;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Auser on 2018/4/17.
 */

public class SnackBarToast {

    public static void showResultWithAction(RelativeLayout layout, String mes, final Action action) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_LONG)
                .setAction("点击设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.actionMethod();
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();
    }

    public interface Action{
        public void actionMethod();
    }

}
