package com.xiaohei.auser.wenliapp.wenlientity;

import java.io.Serializable;

/**
 * Created by machenike on 2018/10/17.
 * 周记
 */

public class NewWeektext implements Serializable {

    private String id;
    private String roomId;
    private int studyStatus;
    private int moodStatus;
    private int healthStatus;
    private int returnRoomStatus;
    private int sleepStatus;
    private int consumeStatus;
    private int loveStatus;
    private String conditionText;
    private String teacherReturnText;
    private int status;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(int studyStatus) {
        this.studyStatus = studyStatus;
    }

    public int getMoodStatus() {
        return moodStatus;
    }

    public void setMoodStatus(int moodStatus) {
        this.moodStatus = moodStatus;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    public int getReturnRoomStatus() {
        return returnRoomStatus;
    }

    public void setReturnRoomStatus(int returnRoomStatus) {
        this.returnRoomStatus = returnRoomStatus;
    }

    public int getSleepStatus() {
        return sleepStatus;
    }

    public void setSleepStatus(int sleepStatus) {
        this.sleepStatus = sleepStatus;
    }

    public int getConsumeStatus() {
        return consumeStatus;
    }

    public void setConsumeStatus(int consumeStatus) {
        this.consumeStatus = consumeStatus;
    }

    public int getLoveStatus() {
        return loveStatus;
    }

    public void setLoveStatus(int loveStatus) {
        this.loveStatus = loveStatus;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public String getTeacherReturnText() {
        return teacherReturnText;
    }

    public void setTeacherReturnText(String teacherReturnText) {
        this.teacherReturnText = teacherReturnText;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
