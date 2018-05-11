package com.xiaohei.auser.wenliapp.entity;

import java.io.Serializable;

public class WeeksText implements Serializable{
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
        return "WeeksText{" +
                "weekTextId=" + weekTextId +
                ", roomId=" + roomId +
                ", study=" + study +
                ", health=" + health +
                ", returnHome=" + returnHome +
                ", sleepCondition=" + sleepCondition +
                ", mood=" + mood +
                ", consume=" + consume +
                ", loveLose=" + loveLose +
                ", conditionText=" + conditionText +
                ", teachersReturnText='" + teachersReturnText + '\'' +
                ", createTime='" + createTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                '}';
    }

    /**
     * @return week_text_id
     */
    public Integer getWeekTextId() {
        return weekTextId;
    }

    /**
     * @param weekTextId
     */
    public void setWeekTextId(Integer weekTextId) {
        this.weekTextId = weekTextId;
    }

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
     * @return study
     */
    public Byte getStudy() {
        return study;
    }

    /**
     * @param study
     */
    public void setStudy(Byte study) {
        this.study = study;
    }

    /**
     * @return health
     */
    public Byte getHealth() {
        return health;
    }

    /**
     * @param health
     */
    public void setHealth(Byte health) {
        this.health = health;
    }

    /**
     * @return return_home
     */
    public Byte getReturnHome() {
        return returnHome;
    }

    /**
     * @param returnHome
     */
    public void setReturnHome(Byte returnHome) {
        this.returnHome = returnHome;
    }

    /**
     * @return sleep_condition
     */
    public Byte getSleepCondition() {
        return sleepCondition;
    }

    /**
     * @param sleepCondition
     */
    public void setSleepCondition(Byte sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    /**
     * @return mood
     */
    public Byte getMood() {
        return mood;
    }

    /**
     * @param mood
     */
    public void setMood(Byte mood) {
        this.mood = mood;
    }

    /**
     * @return consume
     */
    public Byte getConsume() {
        return consume;
    }

    /**
     * @param consume
     */
    public void setConsume(Byte consume) {
        this.consume = consume;
    }

    /**
     * @return love_lose
     */
    public Byte getLoveLose() {
        return loveLose;
    }

    /**
     * @param loveLose
     */
    public void setLoveLose(Byte loveLose) {
        this.loveLose = loveLose;
    }

    /**
     * @return condition_text
     */
    public String getConditionText() {
        return conditionText;
    }

    /**
     * @param conditionText
     */
    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    /**
     * @return teachers_return_text
     */
    public String getTeachersReturnText() {
        return teachersReturnText;
    }

    /**
     * @param teachersReturnText
     */
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

    public WeeksText(Integer roomId, Byte study, Byte health, Byte returnHome, Byte sleepCondition, Byte mood, Byte consume, Byte loveLose, String conditionText) {
        this.roomId = roomId;
        this.study = study;
        this.health = health;
        this.returnHome = returnHome;
        this.sleepCondition = sleepCondition;
        this.mood = mood;
        this.consume = consume;
        this.loveLose = loveLose;
        this.conditionText = conditionText;
    }

    public WeeksText() {
    }
}