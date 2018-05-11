package com.xiaohei.auser.wenliapp.entity;


public class Rooms {
    private Integer roomId;

    private String roomName;

    private Integer buildId;

    private Byte buildLayer;

    private Integer departmentId;

    private Integer classId;

    private Integer teacherId;

    private Integer studentId;

    /**
     * @return room_id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * @return room_name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return build_id
     */
    public Integer getBuildId() {
        return buildId;
    }

    /**
     * @param buildId
     */
    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    /**
     * @return build_layer
     */
    public Byte getBuildLayer() {
        return buildLayer;
    }

    /**
     * @param buildLayer
     */
    public void setBuildLayer(Byte buildLayer) {
        this.buildLayer = buildLayer;
    }

    /**
     * @return department_id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return student_id
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}