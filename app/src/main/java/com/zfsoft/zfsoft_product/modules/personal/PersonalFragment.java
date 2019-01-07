package com.zfsoft.zfsoft_product.modules.personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.di.ActivityScoped;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * 创建日期：2018/12/24 on 10:55
 * 描述:
 * 作者:Ls
 */
@ActivityScoped
public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_share)
    ImageView mIvShare;


    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initVariables() {

    }

    @Inject
    public PersonalFragment() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {
        mIvShare.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
       int key = v.getId();
       if (key == R.id.iv_share){
           //分享
           OnekeyShare oks = new OnekeyShare();
           //关闭sso授权
           oks.disableSSOWhenAuthorize();
           // title标题，微信、QQ和QQ空间等平台使用
           oks.setTitle(getString(R.string.share));
           // titleUrl QQ和QQ空间跳转链接
           // text是分享文本，所有平台都需要这个字段
           oks.setText("我是分享文本");
           //QQ需要设置
           oks.setTitleUrl("http://www.sina.com/");
           oks.setImageUrl("http://wx2.sinaimg.cn/large/94cea970ly1fmj0cjixn8j20dw0dw766.jpg");
           // url在微信、微博，Facebook等平台中使用
           oks.setUrl("http://www.baidu.com/");
           // comment是我对这条分享的评论，仅在人人网使用

           // 构造一个图片分享图标
           Bitmap logo_1 = BitmapFactory.decodeResource (getResources(), R.drawable.ico_photo_share);

           String lable_1 = getResources().getString(R.string.share_photo_name);
           View.OnClickListener listener1 = new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   RxToast.showToast("点击了图片分享");
               }
           };
           oks.setCustomerLogo(logo_1, lable_1, listener1);

           Bitmap logo_2 = BitmapFactory.decodeResource (getResources(), R.drawable.ico_url_share);

           String lable_2 = getResources().getString(R.string.share_url_name);
           View.OnClickListener listener2 = new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   RxToast.showToast("点击了链接复制");
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
                       shareParams.setText("分享微博，让生活更加美好");
                       shareParams.setImageUrl("http://wx2.sinaimg.cn/large/94cea970ly1fmj0cjixn8j20dw0dw766.jpg");
                       shareParams.setShareType(Platform.SHARE_APPS);
                   }
                   if (platform.getName().equals(Wechat.NAME)){
//                       shareParams.setTitle("如何使用");
                       shareParams.setText("让生活更加美好");
                     /*  shareParams.setImageUrl("http://wx2.sinaimg.cn/large/94cea970ly1fmj0cjixn8j20dw0dw766.jpg");
                       shareParams.setUrl("http://www.baidu.com/");*/
                       shareParams.setShareType(Platform.SHARE_TEXT);
                   }
                   if (platform.getName().equals(QQ.NAME)){
                       shareParams.setTitle("这是标题");
                       //shareParams.setTitleUrl("");
                       shareParams.setText("购物让生活更美好");
                       shareParams.setUrl("http://www.baidu.com/");
                       shareParams.setImageUrl("http://wx2.sinaimg.cn/large/94cea970ly1fmj0cjixn8j20dw0dw766.jpg");
                   }
               }
           });
            oks.setCallback(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    RxLogTool.i("shareSDK","成功");
                    RxToast.showToast("成功");
                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {
                    RxLogTool.i("shareSDK",throwable.getMessage());
                    throwable.printStackTrace();
                    RxToast.showToast("失败");
                }

                @Override
                public void onCancel(Platform platform, int i) {
                    RxToast.showToast("取消");
                }
            });
           // 启动分享GUI
           oks.show(getContext());
         }

       }

}
