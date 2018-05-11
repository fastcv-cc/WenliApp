package com.xiaohei.auser.wenliapp.net;

import com.xiaohei.auser.jni.WenliNet;

/**
 * Created by Auser on 2018/4/11.
 * 普通请求的接口
 */

public class NormalUrl {

    private static final String BASE_URL = WenliNet.GetString();

    public static String getVersionUrl(){
        return "http://47.94.144.183:8080/hello/version.json";
    }

    public static String getQueryGradeUrl() {
        return "http://221.233.24.23/eams/home.action";
    }

    public static String sendIdeal() {
        return BASE_URL+"common/sendEmail.action";
    }


}
