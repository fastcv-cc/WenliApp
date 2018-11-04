package com.xiaohei.auser.wenliapp.wenlientity;

import java.io.Serializable;

/**
 * Created by machenike on 2018/11/2.
 * 未阅读的周记
 */

public class NoReadWeekText extends NewWeektext implements Serializable{

    private String buildName;
    private String roomName;

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
}
