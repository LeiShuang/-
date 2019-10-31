package com.zfsoft.zfsoft_product.entity;

import java.util.List;

/**
 * 创建日期：2019/1/12 on 19:18
 * 描述:产品信息实体
 * 作者:Ls
 */
public class ProductInfo {
    private List<String> bannerInfo;//轮播图
    private String productName;//产品标题
    private String productSize;//产品一个的大小
    private String productNumber;//数量
    private String productPrice;//价格
    private String productTime;//时间
    private String fireNumber;//火爆度
    private String showPic;//封面
    private  List<String> picLists;//头像集合
    private String userNumber;//多少人申请
    private String map4Url;//播放地址

    public String getFireNumber() {
        return fireNumber;
    }

    public void setFireNumber(String fireNumber) {
        this.fireNumber = fireNumber;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public List<String> getBannerInfo() {
        return bannerInfo;
    }

    public void setBannerInfo(List<String> bannerInfo) {
        this.bannerInfo = bannerInfo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public List<String> getPicLists() {
        return picLists;
    }

    public void setPicLists(List<String> picLists) {
        this.picLists = picLists;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getMap4Url() {
        return map4Url;
    }

    public void setMap4Url(String map4Url) {
        this.map4Url = map4Url;
    }
}
