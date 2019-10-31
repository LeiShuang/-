package com.zfsoft.zfsoft_product.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;

import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxLogTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.modules.try_use.share_redbook.ShareListenerCallBack;
import com.zfsoft.zfsoft_product.modules.try_use.share_redbook.ShareRedBookActivity;

import java.util.Date;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 创建日期：2019/1/14 on 19:28
 * 描述:分享utils
 * 作者:Ls
 */
public class ShareUtils {

    private static String sCopeUrl;
    private static String sWeiBoUrl;
    private static String sWeChartUrl;
    private static String sBaidDuUrl;
    private static String mShareType;
    private static String mIsCopy ;
    private static String sShareTime;
    private static String mRealUrl;
    private static String mMiniRoutineUrl;//小程序提交的地址
    public static String getCurrentTime(){
        Date date = new Date();
        long time = date.getTime();
        String sTime = String.valueOf(time);
        String shareTime = sTime.substring(0, 10);//时间戳
        return shareTime;
    }
    private static ShareListenerCallBack mShareListenerCallBack;
    /**
     * @param  title 标题
     * @param  thingsId 产品Id或者报告Id
     * @param  type 产品/报告（1：产品/2：报告）
     * @param  originalUserId 原创用户id，如果是报告就填写作者用户id；商品就空串
     * @param  imageUrl 图片url
     * */
    public static void setShareInfo(Context mContext, String title, String thingsId,String type,String originalUserId,String originalUserName,String imageUrl,ShareListenerCallBack ShareListenerCallBack){
        mShareListenerCallBack = ShareListenerCallBack;

        //分享
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(mContext.getString(R.string.share));
        // titleUrl QQ和QQ空间跳转链接
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");

       /**
        * 分享到小红书
        * */
        Bitmap logoRedBook = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ico_share_redbook);
        String lableTitle =  mContext.getResources().getString(R.string.share_red_book);
        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sShareTime = getCurrentTime();
                RxActivityTool.skipActivity(mContext, ShareRedBookActivity.class);

            }

        };
        oks.setCustomerLogo(logoRedBook,lableTitle,listener3);
        /**
         * 复制链接按钮
         * */
        Bitmap logo_2 = BitmapFactory.decodeResource (mContext.getResources(), R.mipmap.ico_copy_url);

        String lable_2 = mContext.getResources().getString(R.string.share_url_name);
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sShareTime = getCurrentTime();

                String userId = DbHelper.getUserId(mContext);
                if ("1".equals(type)){
                    //说明是产品
                    sCopeUrl = Config.BASE_URL + Config.SHARE_PRODUCT + "commodityid=" +thingsId+ "&userId=" +userId + "&relevantId=" +
                            thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + "7" + "&originalUserId=" + originalUserId + "&isCopy="
                            +"1" + "&shareDateTime=" + sShareTime;
                }else {
                    sCopeUrl = Config.BASE_URL + Config.SHARE_REPORT +"id="+ thingsId + "&talented="+ originalUserId +"&userId="+userId + "&relevantId=" +
                            thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + "7" + "&originalUserId=" + originalUserId + "&isCopy="
                            +"1" + "&shareDateTime=" + sShareTime;
                }

                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text",sCopeUrl));
                ToastUtils.showCenterShortToast(mContext,"复制链接成功");
            }
        };
        oks.setCustomerLogo(logo_2, lable_2, listener2);


        /**
         * 如果需要个性化定制,使用ShareContentCustomizeCallback回调
         * */

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams shareParams) {

                if (platform.getName().equals(SinaWeibo.NAME)){
                    /**
                     * 微博分享
                     * */
                    mShareType = "2";
                    mIsCopy = "0";
                    String userId = DbHelper.getUserId(mContext);
                    sShareTime = getCurrentTime();

                    shareParams.setShareType(Platform.SHARE_WEBPAGE);

                    shareParams.setImageUrl(imageUrl);
                    if ("1".equals(type)){

                        sWeiBoUrl = Config.BASE_URL + Config.SHARE_PRODUCT + "commodityid=" +thingsId+"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                        shareParams.setText(("#来自零兔壹的产品分享# "  + title + "(想看更多?下载@零兔壹 APP:" + sWeiBoUrl + " ) ") + sWeiBoUrl);
                    }else {
                        sWeiBoUrl = Config.BASE_URL + Config.SHARE_REPORT +"id="+ thingsId + "&talented="+ originalUserId +"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                        if (TextUtils.isEmpty(originalUserName)){
                            shareParams.setText(("#来自零兔壹的报告分享# "  + title + "(想看更多?下载@零兔壹 APP:" + sWeiBoUrl + " ) ") +sWeiBoUrl);
                        }else{
                            shareParams.setText(("#" + originalUserName + "的报告分享# "  + title + "(想看更多?下载@零兔壹 APP:" + sWeiBoUrl + " ) " )+ sWeiBoUrl);
                        }

                    }
                    mRealUrl = sWeiBoUrl;

                }
                if (platform.getName().equals(Wechat.NAME)){
                    /**
                     * 微信好友分享--->小程序
                     * */
                    mShareType = "1";
                    mIsCopy = "0";
                    String userId = DbHelper.getUserId(mContext);
                    sShareTime = getCurrentTime();
                    shareParams.setShareType(Wechat.SHARE_WXMINIPROGRAM);
                    if ("1".equals(type)){
                        mMiniRoutineUrl = "pages/repoetDetail/repoetDetail?" + "commodityid=" +thingsId+"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                        //产品
                    }else{
                        //报告
                        mMiniRoutineUrl = "pages/tryDetail/tryDetail?" + "id="+ thingsId + "&talented="+ originalUserId +"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;

                    }

                        shareParams.setWxPath(mMiniRoutineUrl);
                        shareParams.setWxUserName("gh_16c10a556c2e");
                        shareParams.setTitle(title);
                        shareParams.setImageUrl(imageUrl);
                        shareParams.setUrl(mMiniRoutineUrl);
                        mRealUrl = mMiniRoutineUrl;

                }
                if (platform.getName().equals(WechatMoments.NAME)){
                    /**
                     * 微信朋友圈分享--->图文，类似微博
                     * */
                        mShareType = "6";
                        mIsCopy = "0";


                    shareParams.setShareType(Platform.SHARE_WEBPAGE);
                    String userId = DbHelper.getUserId(mContext);
                    sShareTime = getCurrentTime();
                    if ("1".equals(type)){
                        sWeChartUrl = Config.BASE_URL + Config.SHARE_PRODUCT + "commodityid=" +thingsId+"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                    }else{
                        sWeChartUrl = Config.BASE_URL + Config.SHARE_REPORT +"id="+ thingsId + "&talented="+ originalUserId +"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                    }

                    mRealUrl = sWeChartUrl;
                    shareParams.setImageUrl(imageUrl);
                    shareParams.setTitle(title);
                    shareParams.setUrl(sWeChartUrl);

                }
                if (platform.getName().equals(QQ.NAME) || platform.getName().equals(QZone.NAME)){
                    if (platform.getName().equals(QQ.NAME)){
                        mShareType = "5";
                        mIsCopy = "0";
                    }else {
                        mShareType = "3";
                        mIsCopy = "0";
                    }
                    /**
                     * QQ好友分享
                     * */
                    String userId = DbHelper.getUserId(mContext);
                    sShareTime = getCurrentTime();
                    shareParams.setShareType(Platform.SHARE_WEBPAGE);
                    shareParams.setImageUrl(imageUrl);
                    if ("1".equals(type)){

                        sBaidDuUrl = Config.BASE_URL + Config.SHARE_PRODUCT + "commodityid=" + thingsId+"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                    }else{
                        sBaidDuUrl = Config.BASE_URL + Config.SHARE_REPORT +"id="+ thingsId + "&talented="+ originalUserId +"&userId=" +userId + "&relevantId=" +
                                thingsId + "&type=" + type +"&shareSource=" + "1" + "&sharePurpose=" + mShareType + "&originalUserId=" + originalUserId + "&isCopy="
                                +"0" + "&shareDateTime=" + sShareTime;
                    }
                    mRealUrl = sBaidDuUrl;
                    shareParams.setTitle("零图壹");
                    shareParams.setText(title);
                    shareParams.setTitleUrl(sBaidDuUrl);


                }

            }
        });
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                RxLogTool.i("shareSDK","成功");
                ToastUtils.showCenterShortToast(mContext,"分享成功");
                if (mShareListenerCallBack != null) {
                    mShareListenerCallBack.shareSuccessCallBack(thingsId,type,mShareType,sShareTime,originalUserId,mIsCopy,mRealUrl);
                }

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                RxLogTool.i("shareSDK",throwable.getMessage());
                throwable.printStackTrace();

            }

            @Override
            public void onCancel(Platform platform, int i) {
                RxLogTool.i("shareSDK","取消");
            }
        });
        // 启动分享GUI
        oks.show(mContext);
    }
}
