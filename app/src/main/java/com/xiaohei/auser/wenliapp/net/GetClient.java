package com.xiaohei.auser.wenliapp.net;

import android.app.Activity;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by Auser on 2018/4/11.
 */

public class GetClient {

    public static OkHttpClient GetOkHttpClient(Activity activity){
        File sdcache = activity.getExternalCacheDir();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(),10*1024*1024));
        return builder.build();
    }

}
