package com.xiaohei.auser.wenliapp.wenlientity.dbentity;

import org.litepal.crud.DataSupport;

public class DbWeeksText extends DataSupport{
    private int id;
    private String roomId;
    private int studyStatus;
    private int moodStatus;
    private int healthStatus;
    private int returnRoomStatus;
    private int sleepStatus;
    private int consumeStatus;
    private int loveStatus;
    private String conditionText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}