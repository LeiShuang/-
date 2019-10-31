package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class LoginBean {
    /*
    * ":{"msgtype":"1","userid":"7"}}
    * */
    private String msgtype;
    private String userid;
    private String nc;

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
