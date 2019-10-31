package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/3/20.
 */
public class ThirdBindBean {
    /*
    * openId：唯一标示
type:类型（微信：1；QQ：3；微博：2）
username:昵称
status：绑定状态（解绑/绑定）
    *
    * */

    private String openId;
    private String type;
    private String username;
    private String status;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
