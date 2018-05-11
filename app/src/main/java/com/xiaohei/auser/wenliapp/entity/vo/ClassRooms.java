package com.xiaohei.auser.wenliapp.entity.vo;

import java.util.List;

/**
 * Created by lvhaosir on 2018/4/24.
 */
public class ClassRooms {

    private Integer classId;

    private String className;

    private Integer teacherId;

    private List<RoomsVo> roomsVoList;


    public ClassRooms() {
    }

    public ClassRooms(Integer classId, String className, Integer teacherId, List<RoomsVo> roomsVoList) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.roomsVoList = roomsVoList;
    }

    @Override
    public String toString() {
        return "ClassRooms{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", teacherId=" + teacherId +
                ", roomsVoList=" + roomsVoList +
                '}';
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public List<RoomsVo> getRoomsVoList() {
        return roomsVoList;
    }

    public void setRoomsVoList(List<RoomsVo> roomsList) {
        this.roomsVoList = roomsList;
    }
}
