package com.xiaohei.auser.wenliapp.entity.vo;

/**
 * Created by lvhaosir on 2018/4/24.
 */
public class TeachersVo {

    private Integer teacherId;

    private String teacherName;

    private String teacherCardId;

    /**
     *  系部编号
     */
    private Integer departmentId;
    /**
     *  系部名称
     */
    private String departmentName;

    @Override
    public String toString() {
        return "TeachersVo{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherCardId='" + teacherCardId + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public TeachersVo() {
    }

    public TeachersVo(Integer teacherId, String teacherName, String teacherCardId) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherCardId = teacherCardId;
    }

    public TeachersVo(Integer teacherId, String teacherName, String teacherCardId, Integer departmentId, String departmentName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherCardId = teacherCardId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherCardId() {
        return teacherCardId;
    }

    public void setTeacherCardId(String teacherCardId) {
        this.teacherCardId = teacherCardId;
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
}
