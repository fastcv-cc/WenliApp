package com.xiaohei.auser.wenliapp.utils;

import android.content.Context;

/**
 * Created by Auser on 2018/4/13.
 */

public class Dp2Utils {

    //dp转化为px
    public static int dp2px(float dipValue, Context activity) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
