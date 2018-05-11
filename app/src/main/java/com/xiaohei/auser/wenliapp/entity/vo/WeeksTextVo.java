package com.xiaohei.auser.wenliapp.entity.vo;


import java.io.Serializable;

/**
 * Created by lvhaosir on 2018/4/27.
 */
public class WeeksTextVo implements Serializable {

    private String buildName;

    private String roomName;

    // WeeksText下的内容

    private Integer weekTextId;

    private Integer roomId;

    private Byte study;

    private Byte health;

    private Byte returnHome;

    private Byte sleepCondition;

    private Byte mood;

    private Byte consume;

    private Byte loveLose;

    private String conditionText;

    private String teachersReturnText;

    private String createTime;

    private String returnTime;

    @Override
    public String toString() {
        return "WeeksTextVo{" +
                "buildName='" + buildName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", weekTextId=" + weekTextId +
                ", roomId=" + roomId +
                ", study=" + study +
                ", health=" + health +
                ", returnHome=" + returnHome +
                ", sleepCondition=" + sleepCondition +
                ", mood=" + mood +
                ", consume=" + consume +
                ", loveLose=" + loveLose +
                ", conditionText='" + conditionText + '\'' +
                ", teachersReturnText='" + teachersReturnText + '\'' +
                ", createTime='" + createTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                '}';
    }


    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getWeekTextId() {
        return weekTextId;
    }

    public void setWeekTextId(Integer weekTextId) {
        this.weekTextId = weekTextId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Byte getStudy() {
        return study;
    }

    public void setStudy(Byte study) {
        this.study = study;
    }

    public Byte getHealth() {
        return health;
    }

    public void setHealth(Byte health) {
        this.health = health;
    }

    public Byte getReturnHome() {
        return returnHome;
    }

    public void setReturnHome(Byte returnHome) {
        this.returnHome = returnHome;
    }

    public Byte getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(Byte sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    public Byte getMood() {
        return mood;
    }

    public void setMood(Byte mood) {
        this.mood = mood;
    }

    public Byte getConsume() {
        return consume;
    }

    public void setConsume(Byte consume) {
        this.consume = consume;
    }

    public Byte getLoveLose() {
        return loveLose;
    }

    public void setLoveLose(Byte loveLose) {
        this.loveLose = loveLose;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public String getTeachersReturnText() {
        return teachersReturnText;
    }

    public void setTeachersReturnText(String teachersReturnText) {
        this.teachersReturnText = teachersReturnText;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
