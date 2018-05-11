package com.xiaohei.auser.wenliapp.entity.vo;

/**
 * Created by lvhaosir on 2018/4/18.
 */
public class StudentsVo {

    /**
     *  学生编号
     */
    private Integer studentId;
    /**
     *  学生学号
     */
    private String studentCardId;
    /**
     *  学生姓名
     */
    private String studentName;
    /**
     *  学生密码
     */
    private String studentPassword;
    /**
     *  系部ID
     */
    private Integer departmentId;
    /**
     *  系部名称
     */
    private String departmentName;
    /**
     *  班级ID
     */
    private Integer classId;
    /**
     *  班级名称
     */
    private String className;
    /**
     *  宿舍ID
     */
    private Integer roomId;
    /**
     *  宿舍名称
     */
    private String roomName;

    private Integer buildId;

    private String buildName;

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    @Override
    public String toString() {
        return "StudentsVo{" +
                "studentId=" + studentId +
                ", studentCardId='" + studentCardId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                '}';
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(String studentCardId) {
        this.studentCardId = studentCardId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


}
