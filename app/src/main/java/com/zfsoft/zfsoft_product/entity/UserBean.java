package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/24.
 * 用于我的界面
 */
public class UserBean {
    /*
    *  myfocus:我的关注
myname:我的姓名
myintroduction:我的简介
myimg:我的头像地址
myfans：我的粉丝
myscores:我的积分

    * */

    private String myname;
    private String myintroduction;
    private String myimg;
    private String myfocus;
    private String myfans;
    private String myscores;
    private String ischeck;

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getMyfocus() {
        return myfocus;
    }

    public void setMyfocus(String myfocus) {
        this.myfocus = myfocus;
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

    public String getMyimg() {
        return myimg;
    }

    public void setMyimg(String myimg) {
        this.myimg = myimg;
    }

    public String getMyfans() {
        return myfans;
    }

    public void setMyfans(String myfans) {
        this.myfans = myfans;
    }

    public String getMyscores() {
        return myscores;
    }

    public void setMyscores(String myscores) {
        this.myscores = myscores;
    }
}
