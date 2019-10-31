package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/2/22.
 */
public class ConcernBean {
    /*

                imageurl:头像地址

attentionname:关注人昵称
attentionintroduction:关注人简介
attentiondynamic:关注人动态
attentionnid:关注人id
attentionnstatus:关注状态（0互粉；其他不互粉）
zt:(0关注；1 取消关注)

 "fansname":"卧清秋",
                "fansintroduction":"不好说",
                "fansdynamic":"",
fansid：关注人id
fansstatus:关注状态（0互粉；其他不互粉）

    *
    * */

    private String attentionname;
    private String attentionintroduction;
    private String attentiondynamic;
    private String attentionnid;
    private String attentionnstatus;

    private String fansname;
    private String fansintroduction;
    private String fansdynamic;
    private String fansid;
    private String fansstatus;

    private String zt;
    private String imageurl;

    public String getAttentionnid() {
        return attentionnid;
    }

    public void setAttentionnid(String attentionnid) {
        this.attentionnid = attentionnid;
    }

    public String getAttentionnstatus() {
        return attentionnstatus;
    }

    public void setAttentionnstatus(String attentionnstatus) {
        this.attentionnstatus = attentionnstatus;
    }

    public String getFansid() {
        return fansid;
    }

    public void setFansid(String fansid) {
        this.fansid = fansid;
    }

    public String getFansstatus() {
        return fansstatus;
    }

    public void setFansstatus(String fansstatus) {
        this.fansstatus = fansstatus;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFansname() {
        return fansname;
    }

    public void setFansname(String fansname) {
        this.fansname = fansname;
    }

    public String getFansintroduction() {
        return fansintroduction;
    }

    public void setFansintroduction(String fansintroduction) {
        this.fansintroduction = fansintroduction;
    }

    public String getFansdynamic() {
        return fansdynamic;
    }

    public void setFansdynamic(String fansdynamic) {
        this.fansdynamic = fansdynamic;
    }

    public String getAttentionname() {
        return attentionname;
    }

    public void setAttentionname(String attentionname) {
        this.attentionname = attentionname;
    }

    public String getAttentionintroduction() {
        return attentionintroduction;
    }

    public void setAttentionintroduction(String attentionintroduction) {
        this.attentionintroduction = attentionintroduction;
    }

    public String getAttentiondynamic() {
        return attentiondynamic;
    }

    public void setAttentiondynamic(String attentiondynamic) {
        this.attentiondynamic = attentiondynamic;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
