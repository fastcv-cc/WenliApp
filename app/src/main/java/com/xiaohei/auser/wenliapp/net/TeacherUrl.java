package com.xiaohei.auser.wenliapp.net;

import com.xiaohei.auser.jni.WenliNet;

/**
 * Created by Auser on 2018/4/11.
 * 教师请求的接口
 */

public class TeacherUrl {

    private static final String BASE_URL = WenliNet.GetString();

    public static String getLoginUrl(){
        return BASE_URL+"teachers/login.action";
    }

    public static String getDepRoom(){
        return BASE_URL+"classes/queryClassRooms.action";
    }

    public static String getStudents(){
        return BASE_URL+"students/queryByRoomId.action";
    }

    public static String setHead(){
        return BASE_URL+"rooms/updateStudentId.action";
    }

    public static String initPsw(){
        return BASE_URL+"students/resetPassword.action";
    }

    public static String sendReturnText(){
        return BASE_URL+"weekstext/updateReturnText.action";
    }

    public static String readWeekNoRead(){
        return BASE_URL+"teachers/queryNoReturn.action";
    }

    public static String changePsw(){
        return BASE_URL+"teachers/updatePassword.action";
    }
}
