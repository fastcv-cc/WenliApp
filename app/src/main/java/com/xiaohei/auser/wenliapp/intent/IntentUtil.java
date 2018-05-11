package com.xiaohei.auser.wenliapp.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.xiaohei.auser.wenliapp.net.NormalUrl;

/**
 * Created by Auser on 2018/3/20.
 * 用于界面跳转
 */

public class IntentUtil {

    public static void MystartActivity(Activity activity, Class cls){
        Intent intent = new Intent(activity,
                cls);
        activity.startActivity(intent);
    }

    public static void MystartActivity(Activity activity, Class cls,int requestcode){
        Intent intent = new Intent(activity,
                cls);
        activity.startActivityForResult(intent,requestcode);
    }

    public static void startActivtyWithFinish(Activity activity, Class cls){
        Intent intent = new Intent(activity,
                cls);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void MystartActivity(Activity activity, Class cls, Bundle bundle){
        Intent intent = new Intent(activity,
                cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void MystartActivity(Activity activity, Class cls, Bundle bundle , int requestcode){
        Intent intent = new Intent(activity,
                cls);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,requestcode);
    }

    public static void CallPhone(String number, Activity activity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        activity.startActivity(intent);
    }

    public static void startWeb(Activity activity){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(NormalUrl.getQueryGradeUrl()));
        activity.startActivity(intent);
    }
}
