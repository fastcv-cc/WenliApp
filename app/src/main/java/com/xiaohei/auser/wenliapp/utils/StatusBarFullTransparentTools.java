package com.xiaohei.auser.wenliapp.utils;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarFullTransparentTools {

        /**
         * status : FullTransparent(全透明)
         */
        public static void setStatusBarFullTransparent(Activity activity){
            if(Build.VERSION.SDK_INT >= 21) {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >=19) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //虚拟键盘也透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        /**
         * status : HalfTransparent(半透明)
         */
        public static void setStatusBarHalfTransparent(Activity activity) {
            if(Build.VERSION.SDK_INT >= 21) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else if (Build.VERSION.SDK_INT >=19) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //虚拟键盘也透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }


        /**
         * 如果需要内容紧贴着StatusBar
         * 应该在对应的布局文件中设置根布局fitSystemWindows=true
         */
        private static View contentViewGroup;


        public static void setFitSystemWindow(boolean fitSystemWindow , Activity activity) {
            if (contentViewGroup == null ) {
                contentViewGroup = ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
            }
            contentViewGroup.setFitsSystemWindows(fitSystemWindow);
        }

        public static void addAll(boolean fitSystemWindow , Activity activity){
            setFitSystemWindow(fitSystemWindow,activity);
            setStatusBarFullTransparent(activity);
        }

}
