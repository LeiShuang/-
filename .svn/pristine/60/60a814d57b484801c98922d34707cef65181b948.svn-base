package com.zfsoft.zfsoft_product.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ckw
 * on 2019/1/23.
 */
public class AddressBean implements Parcelable{
    /**
     * id : 4
     * yhm : 3
     * xm : 大哥
     * sjh : 15867776543
     * yb : 300000
     * sf : 浙江省
     * cs : 杭州市
     * xj : 西湖区
     * zq : 蒋村街道
     * xxdz : 2幢301
     * sfmrdz : 0
     * cjsj : 20190123043046
     */

    private int id;
    private String yhm;
    private String xm;
    private String sjh;
    private String yb;
    private String sf;
    private String cs;
    private String xj;
    private String zq;
    private String xxdz;
    private String sfmrdz;
    private String cjsj;

    protected AddressBean(Parcel in) {
        id = in.readInt();
        yhm = in.readString();
        xm = in.readString();
        sjh = in.readString();
        yb = in.readString();
        sf = in.readString();
        cs = in.readString();
        xj = in.readString();
        zq = in.readString();
        xxdz = in.readString();
        sfmrdz = in.readString();
        cjsj = in.readString();
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel in) {
            return new AddressBean(in);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getXj() {
        return xj;
    }

    public void setXj(String xj) {
        this.xj = xj;
    }

    public String getZq() {
        return zq;
    }

    public void setZq(String zq) {
        this.zq = zq;
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }

    public String getSfmrdz() {
        return sfmrdz;
    }

    public void setSfmrdz(String sfmrdz) {
        this.sfmrdz = sfmrdz;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(yhm);
        dest.writeString(xm);
        dest.writeString(sjh);
        dest.writeString(yb);
        dest.writeString(sf);
        dest.writeString(cs);
        dest.writeString(xj);
        dest.writeString(zq);
        dest.writeString(xxdz);
        dest.writeString(sfmrdz);
        dest.writeString(cjsj);
    }
    /*"id":4,
            "yhm":"3",
            "xm":"大哥",
            "sjh":"15867776543",
            "yb":"300000",
            "sf":"浙江省",
            "cs":"杭州市",
            "xj":"西湖区",
            "zq":"蒋村街道",
            "xxdz":"2幢301",
            "sfmrdz":"0",
            "cjsj":"20190123043046"*/


}
