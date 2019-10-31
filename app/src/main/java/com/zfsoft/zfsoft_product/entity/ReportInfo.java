package com.zfsoft.zfsoft_product.entity;

import java.util.List;

/**
 * 创建日期：2019/1/11 on 9:47
 * 描述:报告内容实体
 * 作者:Ls
 */
public class ReportInfo {
   private int id;
   private String testreporttitle;//标题
    private String testreportdetail;//正文内容
    private String testreportdate;//时间
    private String type;//是否关注
    private int commentsum;//评论数量
    private String lx;//最上面是照片轮播图还是视频  "1"是照片  “2”是视频
   private String talentname;//名字
    private String talentintroduction;//个性签名
   private int likesum;//收藏数量
    private String haslike;//是否收藏  0是未收藏，1是已收藏
    private String hasstar;//是否点赞
    private String talenturl;//头像
    private String productPicUrl;//封面
    private String width;//图片宽度
    private String height;//图片高度

    private List<CommodityurlsBean> commodityurls;//轮播图
    private List<CommodityurlsBean> testreporturl;//轮播图（报告详情）

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }

    public List<CommodityurlsBean> getTestreporturl() {
        return testreporturl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTestreporturl(List<CommodityurlsBean> testreporturl) {
        this.testreporturl = testreporturl;
    }

    public String getHaslike() {
        return haslike;
    }

    public void setHaslike(String haslike) {
        this.haslike = haslike;
    }

    public String getHasstar() {
        return hasstar;
    }

    public void setHasstar(String hasstar) {
        this.hasstar = hasstar;
    }

    public String getTalentintroduction() {
        return talentintroduction;
    }

    public void setTalentintroduction(String talentintroduction) {
        this.talentintroduction = talentintroduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLikesum() {
        return likesum;
    }

    public void setLikesum(int likesum) {
        this.likesum = likesum;
    }

    public String getTalenturl() {
        return talenturl;
    }

    public void setTalenturl(String talenturl) {
        this.talenturl = talenturl;
    }

    public List<CommodityurlsBean> getCommodityurls() {
        return commodityurls;
    }

    public void setCommodityurls(List<CommodityurlsBean> commodityurls) {
        this.commodityurls = commodityurls;
    }

    public String getTestreportdetail() {
        return testreportdetail;
    }

    public void setTestreportdetail(String testreportdetail) {
        this.testreportdetail = testreportdetail;
    }

    public String getTestreportdate() {
        return testreportdate;
    }

    public void setTestreportdate(String testreportdate) {
        this.testreportdate = testreportdate;
    }

    public int getCommentsum() {
        return commentsum;
    }

    public void setCommentsum(int commentsum) {
        this.commentsum = commentsum;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
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