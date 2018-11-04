package com.xiaohei.auser.wenliapp.wenlientity;

import java.util.List;

/**
 * Created by lvhaosir on 2018/4/24.
 * 班级所居住的寝室
 */
public class NewClassRooms {

    private String classId;
    private String className;
    private String teacherId;
    private List<RoomWIthBuildName> roomVoList;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<RoomWIthBuildName> getRoomVoList() {
        return roomVoList;
    }

    public void setRoomVoList(List<RoomWIthBuildName> roomVoList) {
        this.roomVoList = roomVoList;
    }
}
