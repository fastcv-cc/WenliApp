package com.xiaohei.auser.wenliapp.net;

/**
 * Created by machenike on 2018/10/17.
 * 所有的请求路径
 */

public interface WenLiURL {

    String BASE_URL = "http://47.92.251.210:8080/wenli-app/";

    String LOGIN_URL_FOR_TOKEN = BASE_URL + "appLogin";
    //params
    String LOGIN_USERNAME = "username";
    String LOGIN_PASSWORD = "password";
    String LOGIN_LOGINTYPE = "loginType";

    String LOGIN_URL_FOR_INFOR = BASE_URL + "student/queryByUsername/";

    String QUERYALL_DEPARTMENT = BASE_URL + "department/queryAll";

    String QUERYALL_BUILD = BASE_URL + "build/queryAll";

    String QUERYALL_ROOM = BASE_URL + "room/queryByBuildId/";

    String QUERYALL_CLASS = BASE_URL + "clazz/queryByDepartmentId/";

    String REGISTER_URL = BASE_URL + "student/updateRegister";
    //Params
    String REGISTER_ID = "id";
    String REGISTER_ROOMID = "roomId";
    String REGISTER_CLASSID = "classId";
    String REGISTER_DEP_ID = "departmentId";

    String BIND_INFOR = BASE_URL+"room/updateRegisterById";
    String REGISTER_STUDENT_ID = "studentId";
    String REGISTER_TEACHER_ID = "teacherId";

    String STUDENT_GET_WEEKTEXT = BASE_URL + "weektext/queryByRoomId/";

    String STUDENT_GET_HEADID = BASE_URL + "room/queryById/";

    String SEND_WEEKTEXT = BASE_URL + "weektext/save";
    //Params
    String SEND_WEEKTEXT_ROOMID = "roomId";
    String SEND_WEEKTEXT_STUDYSTATUS = "studyStatus";
    String SEND_WEEKTEXT_MOODSTATUS = "moodStatus";
    String SEND_WEEKTEXT_HEALTHSTATUS = "healthStatus";
    String SEND_WEEKTEXT_RETURNROOMSTATUS = "returnRoomStatus";
    String SEND_WEEKTEXT_SLEEPSTATUS = "sleepStatus";
    String SEND_WEEKTEXT_CONSUMESTATUS = "consumeStatus";
    String SEND_WEEKTEXT_LOVESTATUS = "loveStatus";
    String SEND_WEEKTEXT_CONDITIONTEXT = "conditionText";
    String SEND_WEEKTEXT_STATUS = "status";

    String STUDENT_CHANGEPASSWORD = BASE_URL + "student/updatePassword";
    //Params
    String STUDENT_CHANGEPASSWORD_STUDENT_ID = "id";
    String STUDENT_CHANGEPASSWORD_OLD_PASSWORD = "password";
    String STUDENT_CHANGEPASSWORD_NEW_PASSWORD = "newPassword";


    String LOGIN_TEACHER_INFOR = BASE_URL + "teacher/queryByUsername/";

    String TEACHER_GET_CLASS_ROOM = BASE_URL + "clazz/queryClassRooms/";

    String TEACHER_GET_ROOM_STUDENT = BASE_URL + "student/queryByRoomId/";

    String TEACHER_INIT_PASSWORD = BASE_URL + "student/resetPassword";

    String TEACHER_CHANGE_ROOM_HEAD = BASE_URL + "room/updateStudentId";

    String TEACHER_NO_READ_WEEKTEXT = BASE_URL + "weektext/queryNoReturn/";
    String TEACHER_RETURN_TEXT = BASE_URL + "weektext/updateReturnText";

    String TEACHER_CHANGE_PASSWORD = BASE_URL + "teacher/updatePassword";


    String UPDATE_APP = "http://47.92.251.210:8083/helloApk/version.json";

    String SEND_IDEA = BASE_URL + "common/sendFeedback";

    String TEACHER_WARN = BASE_URL + "common/sendUrgencyWeekText";

}
