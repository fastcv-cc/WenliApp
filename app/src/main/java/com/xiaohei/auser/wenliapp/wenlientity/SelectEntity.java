package com.xiaohei.auser.wenliapp.wenlientity;

/**
 * Created by Auser on 2018/4/16.
 */

public class SelectEntity {

    private String name;
    private int img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public SelectEntity(String name, int img) {
        this.name = name;
        this.img = img;
    }
}
