package com.zfsoft.zfsoft_product.entity;

/**
 * 创建日期：2019/1/31 on 19:07
 * 描述:评论详情一级实体
 * 作者:Ls
 */
public class DiscussDetailFirstEntity {
    private int commentid;//id
    private String commentdate;//时间
    private  String commentdetail;//内容
    private String userid;//用户id
    private String commenturl;//头像url
    private String commentname;//名字
    private int commentlikesum;//点赞数
    private int testreportreplysum;//回复数
    private String haslike;//是否点赞

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getCommentdetail() {
        return commentdetail;
    }

    public void setCommentdetail(String commentdetail) {
        this.commentdetail = commentdetail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommenturl() {
        return commenturl;
    }

    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }

    public String getCommentname() {
        return commentname;
    }

    public void setCommentname(String commentname) {
        this.commentname = commentname;
    }

    public int getCommentlikesum() {
        return commentlikesum;
    }

    public void setCommentlikesum(int commentlikesum) {
        this.commentlikesum = commentlikesum;
    }

    public int getTestreportreplysum() {
        return testreportreplysum;
    }

    public void setTestreportreplysum(int testreportreplysum) {
        this.testreportreplysum = testreportreplysum;
    }

    public String getHaslike() {
        return haslike;
    }

    public void setHaslike(String haslike) {
        this.haslike = haslike;
    }
}
