package com.zfsoft.zfsoft_product.entity;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class TryNewProductBean {
   /*
    "commodityprice":"675.00",
            "commodityid":1,
            "commodityurl":"http://62.234.72.97/profile/media/cptpk/20190119160219.jpg"
            */


   private String commodityprice;
   private String commodityid;
   private String commodityurl;
   private String commodityparameters;
   private String sqzt;

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public String getCommodityparameters() {
        return commodityparameters;
    }

    public void setCommodityparameters(String commodityparameters) {
        this.commodityparameters = commodityparameters;
    }

    public String getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(String commodityprice) {
        this.commodityprice = commodityprice;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    public String getCommodityurl() {
        return commodityurl;
    }

    public void setCommodityurl(String commodityurl) {
        this.commodityurl = commodityurl;
    }
}
