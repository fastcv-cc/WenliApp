package com.xiaohei.auser.wenliapp.wenlientity.dbentity;

import org.litepal.crud.DataSupport;

/**
 * Created by machenike on 2018/10/17.
 * 教师实体类
 */

public class DbTeacher extends DataSupport {

    private int id;
    private String teacherid;
    private String username;
    private String password;
    private String nickName;
    private int status;
    private String departmentId;
    private String departmentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public DbTeacher(int id, String teacherid, String username, String password, String nickName, int status, String departmentId, String departmentName) {
        this.id = id;
        this.teacherid = teacherid;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.status = status;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DbTeacher() {
    }

    @Override
    public String toString() {
        return "DbTeacher{" +
                "id=" + id +
                ", teacherid='" + teacherid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
