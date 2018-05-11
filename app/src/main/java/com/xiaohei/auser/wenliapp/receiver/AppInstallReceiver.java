package com.xiaohei.auser.wenliapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xiaohei.auser.wenliapp.sp.NormalSpUtils;
import com.xiaohei.auser.wenliapp.sp.SetSpUtils;

/**
 * Created by Auser on 2018/4/11.
 * 用于得到App更新是否成功的广播
 */

public class AppInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        PackageManager manager = context.getPackageManager();
//        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
//            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "安装成功"+packageName, Toast.LENGTH_LONG).show();
//        }
//        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
//            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "卸载成功"+packageName, Toast.LENGTH_LONG).show();
//        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
//            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "替换成功"+packageName, Toast.LENGTH_LONG).show();
            SetSpUtils.setSharedPreferences(context,"install",true);
        }
    }
}
