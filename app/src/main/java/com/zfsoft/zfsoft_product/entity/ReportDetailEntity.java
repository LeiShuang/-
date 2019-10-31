package com.zfsoft.zfsoft_product.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2019/1/8 on 15:58
 * 描述:评论实体
 * 作者:Ls
 */
public class ReportDetailEntity implements MultiItemEntity,Serializable{
    private DiscussFatherEntity mDiscussFatherEntity;
    private DiscussChildEntity mDiscussChildEntity;
    private ConmmentNumer mCommentNumber;
    private int itemType;

    public ReportDetailEntity(int itemType) {

        this.itemType = itemType;
        mDiscussFatherEntity = new DiscussFatherEntity();
        mDiscussChildEntity = new DiscussChildEntity();
        mCommentNumber = new ConmmentNumer();
    }

    public static final int VIEW_ITEM_TYPE_FATHER = 1;
    public static final int VIEW_ITEM_TYPE_CHILD = 2;
    public static final int VIEW_ITEM_TPE_COMMENT = 3;

    public ConmmentNumer getCommentNumber() {
        return mCommentNumber;
    }

    public void setCommentNumber(ConmmentNumer commentNumber) {
        mCommentNumber = commentNumber;
    }

    public DiscussFatherEntity getDiscussFatherEntity() {
        return mDiscussFatherEntity;
    }

    public void setDiscussFatherEntity(DiscussFatherEntity discussFatherEntity) {
        mDiscussFatherEntity = discussFatherEntity;
    }

    public DiscussChildEntity getDiscussChildEntity() {
        return mDiscussChildEntity;
    }

    public void setDiscussChildEntity(DiscussChildEntity discussChildEntity) {
        mDiscussChildEntity = discussChildEntity;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public class DiscussFatherEntity implements Serializable {
        public DiscussFatherEntity() {
        }

        private int fatherId;//一级评论id
        private boolean hasstar;//是否点赞
        private String fatherUserId;//
        private String fatherPicUrl;//一级评论头像
        private String fatherName;//一级评论姓名
        private String discussTime;//评论时间
        private int discussStarNumber;//点赞数量
        private String discussContent;//评论内容
        private int discussNumber;

        public boolean isHasstar() {
            return hasstar;
        }

        public void setHasstar(boolean hasstar) {
            this.hasstar = hasstar;
        }

        public int getDiscussNumber() {
            return discussNumber;
        }

        public void setDiscussNumber(int discussNumber) {
            this.discussNumber = discussNumber;
        }

        public int getFatherId() {
            return fatherId;
        }

        public void setFatherId(int fatherId) {
            this.fatherId = fatherId;
        }

        public String getFatherUserId() {
            return fatherUserId;
        }

        public void setFatherUserId(String fatherUserId) {
            this.fatherUserId = fatherUserId;
        }

        public String getFatherPicUrl() {
            return fatherPicUrl;
        }

        public void setFatherPicUrl(String fatherPicUrl) {
            this.fatherPicUrl = fatherPicUrl;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getDiscussTime() {
            return discussTime;
        }

        public void setDiscussTime(String discussTime) {
            this.discussTime = discussTime;
        }

        public int getDiscussStarNumber() {
            return discussStarNumber;
        }

        public void setDiscussStarNumber(int discussStarNumber) {
            this.discussStarNumber = discussStarNumber;
        }

        public String getDiscussContent() {
            return discussContent;
        }

        public void setDiscussContent(String discussContent) {
            this.discussContent = discussContent;
        }
    }

    public class DiscussChildEntity implements Serializable {
        public DiscussChildEntity() {

        }

        private int id;
        private String name;
        private String childUserId;//用户id
        private String commment;
        private String childTime;//二级评论头像
        private String relayName;//回复给谁
        private String childUrl;//二级评论头像

        public String getChildUserId() {
            return childUserId;
        }

        public void setChildUserId(String childUserId) {
            this.childUserId = childUserId;
        }

        public String getChildTime() {
            return childTime;
        }

        public void setChildTime(String childTime) {
            this.childTime = childTime;
        }

        public String getRelayName() {
            return relayName;
        }

        public void setRelayName(String relayName) {
            this.relayName = relayName;
        }

        public String getChildUrl() {
            return childUrl;
        }

        public void setChildUrl(String childUrl) {
            this.childUrl = childUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommment() {
            return commment;
        }

        public void setCommment(String commment) {
            this.commment = commment;
        }
    }

    public class ConmmentNumer implements Serializable {
        public ConmmentNumer() {

        }

        private int number;
        private int fatherStarNumber;
        private int fatherDiscussId;
        private boolean hasstar;
        private String fatherName;
        private String fatherContent;
        private String fatherDiscussTime;
        private String fatherDiscussUrl;
        private String fatherUserId;

        public boolean isHasstar() {
            return hasstar;
        }

        public void setHasstar(boolean hasstar) {
            this.hasstar = hasstar;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getFatherStarNumber() {
            return fatherStarNumber;
        }

        public void setFatherStarNumber(int fatherStarNumber) {
            this.fatherStarNumber = fatherStarNumber;
        }

        public int getFatherDiscussId() {
            return fatherDiscussId;
        }

        public void setFatherDiscussId(int fatherDiscussId) {
            this.fatherDiscussId = fatherDiscussId;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getFatherContent() {
            return fatherContent;
        }

        public void setFatherContent(String fatherContent) {
            this.fatherContent = fatherContent;
        }

        public String getFatherDiscussTime() {
            return fatherDiscussTime;
        }

        public void setFatherDiscussTime(String fatherDiscussTime) {
            this.fatherDiscussTime = fatherDiscussTime;
        }

        public String getFatherDiscussUrl() {
            return fatherDiscussUrl;
        }

        public void setFatherDiscussUrl(String fatherDiscussUrl) {
            this.fatherDiscussUrl = fatherDiscussUrl;
        }

        public String getFatherUserId() {
            return fatherUserId;
        }

        public void setFatherUserId(String fatherUserId) {
            this.fatherUserId = fatherUserId;
        }
    }
}
