package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/29.
 */
public class OtherBean {
    /*
    * myname:我的姓名
myintroduction:我的简介
myfans：我的粉丝
myfocus:我的关注
likesum:我的点赞
myimg

    * */

    private String myname;
    private String myimg;
    private String myintroduction;
    private int myfans;
    private int myfocus;
    private int likesum;
    private String type;

    public String getMyimg() {
        return myimg;
    }

    public void setMyimg(String myimg) {
        this.myimg = myimg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getMyintroduction() {
        return myintroduction;
    }

    public void setMyintroduction(String myintroduction) {
        this.myintroduction = myintroduction;
    }

    public int getMyfans() {
        return myfans;
    }

    public void setMyfans(int myfans) {
        this.myfans = myfans;
    }

    public int getMyfocus() {
        return myfocus;
    }

    public void setMyfocus(int myfocus) {
        this.myfocus = myfocus;
    }

    public int getLikesum() {
        return likesum;
    }

    public void setLikesum(int likesum) {
        this.likesum = likesum;
    }
}
