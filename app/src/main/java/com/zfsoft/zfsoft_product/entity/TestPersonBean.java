package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/24.
 */
public class TestPersonBean {
    /*
    *
    * talented 试用达人id
    * talenturl：试用达人头像地址
talentname：试用达人姓名
talentfans：试用达人粉丝数量
talentreport:试用达人报告数量

    * */

    private String talented;
    private String talenturl;
    private String talentname;
    private String talentfans;
    private String talentreport;
    private String type;//是否关注

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTalented() {
        return talented;
    }

    public void setTalented(String talented) {
        this.talented = talented;
    }

    public String getTalentname() {
        return talentname;
    }

    public void setTalentname(String talentname) {
        this.talentname = talentname;
    }

    public String getTalentfans() {
        return talentfans;
    }

    public void setTalentfans(String talentfans) {
        this.talentfans = talentfans;
    }

    public String getTalentreport() {
        return talentreport;
    }

    public void setTalentreport(String talentreport) {
        this.talentreport = talentreport;
    }

    public String getTalenturl() {

        return talenturl;
    }

    public void setTalenturl(String talenturl) {
        this.talenturl = talenturl;
    }
}