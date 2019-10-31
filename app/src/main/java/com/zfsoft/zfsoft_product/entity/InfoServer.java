package com.zfsoft.zfsoft_product.entity;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2019/1/12 on 14:30
 * 描述:假数据帮助类
 * 作者:Ls
 */
public class InfoServer {
    private static List<ReportInfo> mReportInfos;
    private static ProductInfo mProductInfo;
  /*  public static List<ReportInfo> getReportList(int size){
        mReportInfos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ReportInfo info = new ReportInfo();
            info.setShowPicUrl("http://dpic.tiankong.com/fx/g1/QJ6846644164.jpg");
            info.setReportTitle("报告标题" + i);
            info.setPersonalName("张麻子"+ i);
            info.setPersonalPhotoUrl("http://tupian.qqw21.com/article/UploadPic/2016-4/201641121524755187.jpg");
            info.setStartNumer("" + i);
            mReportInfos.add(info);
        }
        return mReportInfos;
    }*/

    public static List<ProductInfo> getProductListInfo(int size) {
        List<String> imageLists = new ArrayList<>();
        List<String> userPics = new ArrayList<>();
        List<ProductInfo> productLists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mProductInfo = new ProductInfo();
            for (int j = 0; j < 5; j++) {
                imageLists.add("http://dpic.tiankong.com/fx/g1/QJ6846644164.jpg");
                userPics.add("http://tupian.qqw21.com/article/UploadPic/2016-4/201641121524755187.jpg");
            }
            mProductInfo.setBannerInfo(imageLists);
            mProductInfo.setPicLists(userPics);
            mProductInfo.setProductName("屈臣氏美白面膜" + i);
            mProductInfo.setProductPrice("单价 : 230");
            mProductInfo.setProductSize("50ml");
            mProductInfo.setProductNumber("数量 :1200");
            mProductInfo.setProductTime("1382400000");
            mProductInfo.setUserNumber("共350人申请");
            mProductInfo.setMap4Url("http://200024424.vod.myqcloud.com/200024424_709ae516bdf811e6ad39991f76a4df69.f20.mp4");
            productLists.add(mProductInfo);
        }
        return productLists;

    }


    /**
     * 获取评论数据
     */

    public static List<ReportDetailEntity> getDiscussInfo(CommentsBean commentsBean) {
        List<ReportDetailEntity> discussInfos = new ArrayList<>();
        int fatherSum = commentsBean.getSize();

        for (int i = 0; i < fatherSum; i++) {
            ReportDetailEntity reportDetailEntity = new ReportDetailEntity(ReportDetailEntity.VIEW_ITEM_TYPE_FATHER);
            reportDetailEntity.getDiscussFatherEntity().setFatherId(commentsBean.getData().get(i).getCommentid());
            reportDetailEntity.getDiscussFatherEntity().setFatherName(commentsBean.getData().get(i).getCommentname());
            reportDetailEntity.getDiscussFatherEntity().setFatherPicUrl(commentsBean.getData().get(i).getCommenturl());
            reportDetailEntity.getDiscussFatherEntity().setDiscussNumber(commentsBean.getData().get(i).getTestreportreplysum());
            reportDetailEntity.getDiscussFatherEntity().setDiscussTime(commentsBean.getData().get(i).getCommentdate());
            reportDetailEntity.getDiscussFatherEntity().setDiscussStarNumber(commentsBean.getData().get(i).getCommentlikesum());
            reportDetailEntity.getDiscussFatherEntity().setHasstar(commentsBean.getData().get(i).isHaslike());
            reportDetailEntity.getDiscussFatherEntity().setDiscussContent(commentsBean.getData().get(i).getCommentdetail());
            reportDetailEntity.getDiscussFatherEntity().setFatherUserId(commentsBean.getData().get(i).getUserid());
            discussInfos.add(reportDetailEntity);
            //二级评论总共条数
            int childCommentsSumSize = commentsBean.getData().get(i).getTestreportreplysum();
            int realCommentsChildSize = commentsBean.getData().get(i).getTestreportreplyList().size();


            if (childCommentsSumSize == 0) {
                //当子评论条数为空时,没有查看更多
            } else if (childCommentsSumSize <= 3) {
                //当子评论条数小于3时,没有查看更多
                for (int j = 0; j < realCommentsChildSize; j++) {
                    ReportDetailEntity reportChildEntity = new ReportDetailEntity(ReportDetailEntity.VIEW_ITEM_TYPE_CHILD);
                    reportChildEntity.getDiscussChildEntity().setId(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplayid());
                    reportChildEntity.getDiscussChildEntity().setName(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplyname());
                    reportChildEntity.getDiscussChildEntity().setRelayName(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplytoname());
                    reportChildEntity.getDiscussChildEntity().setCommment(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplydetail());
                    reportChildEntity.getDiscussChildEntity().setChildUserId(commentsBean.getData().get(i).getTestreportreplyList().get(j).getUserid());
                    reportChildEntity.getDiscussChildEntity().setChildUrl(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplyurl());
                    discussInfos.add(reportChildEntity);
                }

            } else {
                //当子评论条数大于3时,有查看更多
                for (int j = 0; j < 3; j++) {

                    ReportDetailEntity reportChildEntity = new ReportDetailEntity(ReportDetailEntity.VIEW_ITEM_TYPE_CHILD);
                    reportChildEntity.getDiscussChildEntity().setId(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplayid());
                    reportChildEntity.getDiscussChildEntity().setName(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplyname());
                    reportChildEntity.getDiscussChildEntity().setRelayName(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplytoname());
                    reportChildEntity.getDiscussChildEntity().setCommment(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplydetail());
                    reportChildEntity.getDiscussChildEntity().setChildUserId(commentsBean.getData().get(i).getTestreportreplyList().get(j).getUserid());
                    reportChildEntity.getDiscussChildEntity().setChildUrl(commentsBean.getData().get(i).getTestreportreplyList().get(j).getReplyurl());
                    discussInfos.add(reportChildEntity);
                }

                ReportDetailEntity reportDetailEntity3 = new ReportDetailEntity(ReportDetailEntity.VIEW_ITEM_TPE_COMMENT);
                reportDetailEntity3.getCommentNumber().setNumber(childCommentsSumSize);
                reportDetailEntity3.getCommentNumber().setFatherUserId(commentsBean.getData().get(i).getUserid());
                reportDetailEntity3.getCommentNumber().setFatherContent(commentsBean.getData().get(i).getCommentdetail());
                reportDetailEntity3.getCommentNumber().setFatherDiscussId(commentsBean.getData().get(i).getCommentid());
                reportDetailEntity3.getCommentNumber().setFatherDiscussTime(commentsBean.getData().get(i).getCommentdate());
                reportDetailEntity3.getCommentNumber().setFatherName(commentsBean.getData().get(i).getCommentname());
                reportDetailEntity3.getCommentNumber().setFatherStarNumber(commentsBean.getData().get(i).getTestreportreplysum());
                reportDetailEntity3.getCommentNumber().setFatherDiscussUrl(commentsBean.getData().get(i).getCommenturl());
                reportDetailEntity3.getCommentNumber().setHasstar(commentsBean.getData().get(i).isHaslike());
                discussInfos.add(reportDetailEntity3);
            }
        }

        return discussInfos;
    }


    /**
     * 获取个人信息
     */
    public static UserInfo getUserInfo() {
        UserInfo info = new UserInfo();
        info.setName("有梦想不睡觉");
        info.setAttentionNumber("100");
        info.setFensNumber("10002");
        info.setStarNumber("6666");
        info.setHasAttention(true);
        info.setPersonalSpeak("I hava a dream!");
        info.setPersonPicUrl("http://tupian.qqw21.com/article/UploadPic/2016-4/201641121524755187.jpg");
        return info;
    }


    public static List<ThingsInfoEntity> getThingsList(int index) {
        List<ThingsInfoEntity> thingsInfoEntities = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            ThingsInfoEntity entity = new ThingsInfoEntity();
            entity.setCommoditytitle("屈臣氏美白面膜" + i);
            entity.setCommodityprice("200");
            entity.setCommoditybrowse(500);
            entity.setCommodityparameters("50ml");
            entity.setCommoditysum(10);
            thingsInfoEntities.add(entity);
        }
        return thingsInfoEntities;
    }
}
