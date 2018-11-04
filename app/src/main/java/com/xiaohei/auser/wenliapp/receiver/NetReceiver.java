package com.xiaohei.auser.wenliapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.xiaohei.auser.wenliapp.wenlievent.NetEvent;
import com.xiaohei.auser.wenliapp.utils.NetStateUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Auser on 2018/4/17.
 */

public class NetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            boolean isConnected = NetStateUtils.isNetworkConnected(context);
            if (isConnected) {
                EventBus.getDefault().post(new NetEvent(true));
            } else {
                EventBus.getDefault().post(new NetEvent(false));
            }
        }
    }

}
