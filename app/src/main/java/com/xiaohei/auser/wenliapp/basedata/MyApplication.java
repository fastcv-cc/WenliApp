package com.xiaohei.auser.wenliapp.basedata;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by machenike on 2018/10/16.
 * 用于获得全局Context
 */

public class MyApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){
        return context;
    }

}
