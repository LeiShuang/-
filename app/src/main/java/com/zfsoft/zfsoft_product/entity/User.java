package com.zfsoft.zfsoft_product.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @date: 2017/3/14
 * @Description: 全局的用户对象
 */
public class User extends RealmObject {

    /**
     * 		userid：用户id<br>
     * 		myimg:我的头像地址<br>
     * 		nc：用户昵称<br>
     * 		xm：我的姓名<br>
     * 		xb：我的性别（0：男 1：女 2：未知）<br>
     * 		sr：生日<br>
     * 		grjj：我的个人简介<br>
     * 		zy：我的职业<br>
     * 		szdq：所在地区<br>
     * 		dz：我的地址<br>
     * 		 "xhszh" 小红书账号
"xhsnc" 小红书昵称
        "xhszy"* 小红书主页
        "xhsfans"* 小红书粉丝数


        */

    @PrimaryKey
    private String userid;//用户id 唯一标识符
    private String ID;
    private String myimg;//我的头像
    private String nc; //昵称
    private String txlj;//图像路径
    private String xm;//姓名
    private String xb;//性别
    private String sr;//生日
    private String zy;//职业
    private String dz;//详细地址
    private String szdq;//所在地区
    private String grjj;//个人简介
    private String phone;
    private String redAccount;
    private String redName;
    private String redHome;
    private String redFans;


    public String getRedAccount() {
        return redAccount;
    }

    public void setRedAccount(String redAccount) {
        this.redAccount = redAccount;
    }

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public String getRedHome() {
        return redHome;
    }

    public void setRedHome(String redHome) {
        this.redHome = redHome;
    }

    public String getRedFans() {
        return redFans;
    }

    public void setRedFans(String redFans) {
        this.redFans = redFans;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMyimg() {
        return myimg;
    }

    public void setMyimg(String myimg) {
        this.myimg = myimg;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getTxlj() {
        return txlj;
    }

    public void setTxlj(String txlj) {
        this.txlj = txlj;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }



    public String getGrjj() {
        return grjj;
    }

    public void setGrjj(String grjj) {
        this.grjj = grjj;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getSzdq() {
        return szdq;
    }

    public void setSzdq(String szdq) {
        this.szdq = szdq;
    }
}