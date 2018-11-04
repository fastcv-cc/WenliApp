package com.xiaohei.auser.wenliapp.wenlientity;

/**
 * Created by machenike on 2018/10/17.
 * 新版学生实体类
 */

public class NewStudent {

    private String id;
    private int status;
    private String cardId;
    private String name;
    private String password;
    private String departmentId;
    private String departmentName;
    private String classId;
    private String className;
    private String roomId;
    private String roomName;
    private String buildName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public NewStudent() {
    }

    public NewStudent(String id, int status, String cardId, String name, String password, String departmentId, String departmentName, String classId, String className, String roomId, String roomName, String buildName) {
        this.id = id;
        this.status = status;
        this.cardId = cardId;
        this.name = name;
        this.password = password;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.classId = classId;
        this.className = className;
        this.roomId = roomId;
        this.roomName = roomName;
        this.buildName = buildName;
    }

    @Override
    public String toString() {
        return "NewStudent{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", buildName='" + buildName + '\'' +
                '}';
    }
}
