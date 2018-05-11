package com.xiaohei.auser.wenliapp.entity.vo;

/**
 * Created by lvhaosir on 2018/4/24.
 */
public class RoomsVo {

    private Integer roomId;

    private String roomName;

    private Integer buildId;
    /**
     *  楼栋名称
     */
    private String buildName;

    private Byte buildLayer;

    private Integer departmentId;

    private Integer classId;

    private Integer teacherId;

    private Integer studentId;

    public RoomsVo() {
    }

    public RoomsVo(Integer roomId, String roomName, Integer buildId, String buildName, Byte buildLayer, Integer departmentId, Integer classId, Integer teacherId, Integer studentId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.buildId = buildId;
        this.buildName = buildName;
        this.buildLayer = buildLayer;
        this.departmentId = departmentId;
        this.classId = classId;
        this.teacherId = teacherId;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "RoomsVo{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", buildId=" + buildId +
                ", buildName='" + buildName + '\'' +
                ", buildLayer=" + buildLayer +
                ", departmentId=" + departmentId +
                ", classId=" + classId +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                '}';
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

    public Byte getBuildLayer() {
        return buildLayer;
    }

    public void setBuildLayer(Byte buildLayer) {
        this.buildLayer = buildLayer;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
