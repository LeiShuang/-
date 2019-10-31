package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/24.
 */
public class TestReportBean {
    /*
    id 试用报告id
    talented  试用达人id
    * testreporturl：试用报告图片地址
talenturl：试用报告达人姓名头像
testreporttitle：试用报告名称
talentname:试用达人姓名
"lx":"1"
likesum:点赞数

    * */
    private String talented;
    private int id;
    private String testreporturl;
    private String talenturl;
    private String testreporttitle;
    private String talentname;
    private String productPicUrl;
    private String likesum;
    private String width;
    private String  height;
    private String shzt;
    private String shztmc;
    private String lx;//类型  "1"是轮播图，"2"是视频

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getTalented() {
        return talented;
    }

    public void setTalented(String talented) {
        this.talented = talented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestreporturl() {
        return testreporturl;
    }

    public void setTestreporturl(String testreporturl) {
        this.testreporturl = testreporturl;
    }

    public String getTalenturl() {
        return talenturl;
    }

    public void setTalenturl(String talenturl) {
        this.talenturl = talenturl;
    }

    public String getTestreporttitle() {
        return testreporttitle;
    }

    public void setTestreporttitle(String testreporttitle) {
        this.testreporttitle = testreporttitle;
    }

    public String getTalentname() {
        return talentname;
    }

    public void setTalentname(String talentname) {
        this.talentname = talentname;
    }

    public String getLikesum() {
        return likesum;
    }

    public void setLikesum(String likesum) {
        this.likesum = likesum;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}