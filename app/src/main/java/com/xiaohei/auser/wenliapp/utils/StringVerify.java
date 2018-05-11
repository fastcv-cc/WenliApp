package com.xiaohei.auser.wenliapp.utils;

/**
 * Created by Auser on 2018/4/13.
 */

public class StringVerify {

    public static boolean validatePassword(String password){
        return password.length() > 0;
    }

    public static boolean validateUserName(String username){
        return username.length() > 0 ;
    }

}
