package com.xiaohei.auser.wenliapp.net;

import com.xiaohei.auser.jni.WenliNet;

/**
 * Created by Auser on 2018/4/11.
 * 学生请求的接口
 */

public class StudentUrl {

    private static final String BASE_URL = WenliNet.GetString();

    public static String getLoginUrl(){
        return BASE_URL+"students/login.action";
    }

    public static String getmWeekUrl(){
        return BASE_URL+"weekstext/queryByRoomId.action";
    }

    public static String getDepUrl(){
        return BASE_URL+"departments/queryAll.action";
    }

    public static String getBuildUrl(){
        return BASE_URL+"builds/queryAll.action";
    }

    public static String getRoomsUrl(){
        return BASE_URL+"rooms/queryByBuildId.action";
    }

    public static String getClassesUrl(){
        return BASE_URL+"classes/queryByDepartmentId.action";
    }

    public static String sendInfoUrl(){
        return BASE_URL+"students/updateStudentByRegister.action";
    }

    public static String sendMesUrl(){
        return BASE_URL+"weekstext/save.action";
    }

    public static String getHeadUrl() {
        return BASE_URL+"rooms/loadRoom.action";
    }

    public static String changePsw(){
        return BASE_URL+"students/updatePassword.action";
    }

    public static String bindTeacher(){
        return BASE_URL+"rooms/updateRoom.action";
    }


}
