package com.xiaohei.auser.wenliapp.wenlientity.dbentity;

import org.litepal.crud.DataSupport;

/**
 * Created by machenike on 2018/10/17.
 * 新版学生实体类(数据库)
 */

public class DbStudent extends DataSupport {

    private int id;
    private String studentid;
    private int status;
    private String cardId;
    private String name;
    private String password;
    private String departmentId;
    private String departmentName;
    private String classesId;
    private String classesName;
    private String roomId;
    private String roomName;
    private String buildName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public DbStudent(int id, String studentid, int status, String cardId, String name, String password, String departmentId, String departmentName, String classId, String className, String roomId, String roomName, String buildName) {
        this.id = id;
        this.studentid = studentid;
        this.status = status;
        this.cardId = cardId;
        this.name = name;
        this.password = password;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.classesId = classId;
        this.classesName = className;
        this.roomId = roomId;
        this.roomName = roomName;
        this.buildName = buildName;
    }

    public DbStudent() {
    }

    @Override
    public String toString() {
        return "DbStudent{" +
                "id=" + id +
                ", studentid='" + studentid + '\'' +
                ", status=" + status +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", classId='" + classesId + '\'' +
                ", className='" + classesName + '\'' +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", buildName='" + buildName + '\'' +
                '}';
    }
}
