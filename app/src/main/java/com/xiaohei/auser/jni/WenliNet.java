package com.xiaohei.auser.jni;

/**
 * Created by Auser on 2018/5/6.
 */

public class WenliNet {

    static {
        System.loadLibrary("native-lib");
    }

    public static native String GetString();

}
