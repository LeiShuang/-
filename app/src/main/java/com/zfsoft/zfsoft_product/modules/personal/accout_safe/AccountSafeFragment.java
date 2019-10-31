package com.zfsoft.zfsoft_product.modules.personal.accout_safe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.ThirdBindBean;
import com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify.ModifyPhoneActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by ckw
 * on 2019/1/18.
 */
public class AccountSafeFragment extends BaseFragment implements View.OnClickListener, AccountSafeContract.View {
    @Inject
    public AccountSafeFragment() {
    }

    @Inject
    AccountSafePresenter mAccountSafePresenter;

    @BindView(R.id.rl_back)
    RelativeLayout mBack;
    @BindView(R.id.rl_modify_pwd)
    RelativeLayout mRlModifyPwd;//修改密码item
    @BindView(R.id.rl_modify_phone)
    RelativeLayout mRlModifyPhone;//修改手机号item

    @BindView(R.id.tv_wechat_bind_state)
    TextView mTvWechatState;
    @BindView(R.id.tv_sina_bind_state)
    TextView mTvSinaState;
    @BindView(R.id.tv_qq_bind_state)
    TextView mTvQqState;

    private int mCurrentIndex;

    private List<ThirdBindBean> mThirdBindStateList;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_account_safe;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        mAccountSafePresenter.getThirdBindState(Config.HSK,DbHelper.getUserId(getContext()));
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mRlModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ModifyPhoneActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });

        mRlModifyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ModifyPhoneActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        mTvWechatState.setOnClickListener(this);
        mTvSinaState.setOnClickListener(this);
        mTvQqState.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_wechat_bind_state:
                mCurrentIndex = 1;
                if(mTvWechatState.getText().toString().trim().equals("未绑定")){
                    Platform mWechat = ShareSDK.getPlatform(Wechat.NAME);
                    if(mWechat.isAuthValid()){
                        mWechat.removeAccount(true);
                    }
                    mWechat.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            Log.d("----", "onComplete: 授权完成");
                            String userName = platform.getDb().getUserName();//获取用户名字
                            String avatar = platform.getDb().getUserIcon(); //获取用户头像
                            String userId = platform.getDb().getUserId();

                            mAccountSafePresenter.bindThirdPlatform(Config.HSK, DbHelper.getUserId(getContext()),"1","",
                                    userId,userName,"1",avatar);
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            Log.d("----", "onComplete: 授权失败");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showCenterToast(getContext(),"授权失败");
                                }
                            });
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            Log.d("----", "onComplete: 授权取消");
                            ToastUtils.showCenterToast(getContext(),"授权取消");
                        }
                    });
                    mWechat.showUser(null);
                }else {
                    if (mThirdBindStateList != null){
                        for (int i = 0; i < mThirdBindStateList.size(); i++) {
                            ThirdBindBean thirdBindBean = mThirdBindStateList.get(i);
                            if(thirdBindBean.getType().equals("1")){
                                mAccountSafePresenter.unbindThirdPlatform(Config.HSK,DbHelper.getUserId(getContext()),thirdBindBean.getOpenId());
                            }
                        }
                    }


                }

                break;
            case R.id.tv_sina_bind_state:
                mCurrentIndex = 2;
                if(mTvSinaState.getText().toString().trim().equals("未绑定")){
                    Platform mWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                    if(mWeibo.isAuthValid()){
                        mWeibo.removeAccount(true);
                    }
                    mWeibo.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            Log.d("----", "onComplete: 授权完成");
                            String userId = String.valueOf(hashMap.get("id")) ;
                            String userName = String.valueOf(hashMap.get("name")) ;
                            String avatar = (String) hashMap.get("avatar_hd");
                            mAccountSafePresenter.bindThirdPlatform(Config.HSK, DbHelper.getUserId(getContext()),"1","",
                                    userId,userName,"2",avatar);
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            Log.d("----", "onComplete: 授权失败");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showCenterToast(getContext(),"授权失败");
                                }
                            });
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            Log.d("----", "onComplete: 授权取消");
                            ToastUtils.showCenterToast(getContext(),"授权取消");
                        }
                    });
                    mWeibo.showUser(null);
                }else {
                    if(mThirdBindStateList != null){
                        for (int i = 0; i < mThirdBindStateList.size(); i++) {
                            ThirdBindBean thirdBindBean = mThirdBindStateList.get(i);
                            if(thirdBindBean.getType().equals("2")){
                                mAccountSafePresenter.unbindThirdPlatform(Config.HSK,DbHelper.getUserId(getContext()),thirdBindBean.getOpenId());
                            }
                        }
                    }

                }

                break;
            case R.id.tv_qq_bind_state:
                mCurrentIndex = 3;
                if(mTvQqState.getText().toString().trim().equals("未绑定")){
                    Platform mQQ = ShareSDK.getPlatform(QQ.NAME);
                    if(mQQ.isAuthValid()){
                        mQQ.removeAccount(true);
                    }
                    mQQ.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            Log.d("----", "onComplete: 授权完成");
                            String userName = String.valueOf(hashMap.get("nickname"));
                            String avatar = String.valueOf(hashMap.get("figureurl_qq"));
                            String userId = platform.getDb().getUserId();
                            mAccountSafePresenter.bindThirdPlatform(Config.HSK, DbHelper.getUserId(getContext()),"1","",
                                    userId,userName,"3",avatar);
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            Log.d("----", "onComplete: 授权失败");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showCenterToast(getContext(),"授权失败");
                                }
                            });
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            Log.d("----", "onComplete: 授权取消");
                            ToastUtils.showCenterToast(getContext(),"授权取消");
                        }
                    });
                    mQQ.showUser(null);
                }else {
                    if (mThirdBindStateList != null){
                        for (int i = 0; i < mThirdBindStateList.size(); i++) {
                            ThirdBindBean thirdBindBean = mThirdBindStateList.get(i);
                            if(thirdBindBean.getType().equals("3")){
                                mAccountSafePresenter.unbindThirdPlatform(Config.HSK,DbHelper.getUserId(getContext()),thirdBindBean.getOpenId());
                            }
                        }
                    }

                }

                break;
        }
    }

    @Override
    public void initPresenter() {
        mAccountSafePresenter.takeView(this);
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        if (immersionBar == null) {
            return;
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.statusBarColor(R.color.colorWhite)
                .init();
    }


    @Override
    public void getThirdBindStateSuccess(List<ThirdBindBean> list) {
        mThirdBindStateList = list;
        if(list != null){
            for (int i = 0; i < list.size(); i++) {
                ThirdBindBean thirdBindBean = list.get(i);
                if (thirdBindBean.getType().equals("1")){//微信
                    if(thirdBindBean.getOpenId() != null && !thirdBindBean.getOpenId().equals("")){//未绑定
                        mTvWechatState.setText("已绑定");
                    }else {
                        mTvWechatState.setText("未绑定");
                    }
                }else if(thirdBindBean.getType().equals("2")){//微博
                    if(thirdBindBean.getOpenId() != null && !thirdBindBean.getOpenId().equals("")){//未绑定
                        mTvSinaState.setText("已绑定");
                    }else {
                        mTvSinaState.setText("未绑定");
                    }
                }else if(thirdBindBean.getType().equals("3")){//微博
                    if(thirdBindBean.getOpenId() != null && !thirdBindBean.getOpenId().equals("")){//未绑定
                        mTvQqState.setText("已绑定");
                    }else {
                        mTvQqState.setText("未绑定");
                    }
                }
            }
        }
    }

    @Override
    public void getThirdBindStateFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void unbindThirdPlatformSuccess(SignBean signBean) {
        if(signBean.getMsgtype().equals("1")){
            ToastUtils.showCenterToast(getContext(),"解绑成功");
            if(mCurrentIndex == 1){
                mTvWechatState.setText("未绑定");
            }else if(mCurrentIndex == 2){
                mTvSinaState.setText("未绑定");
            }else if(mCurrentIndex == 3){
                mTvQqState.setText("未绑定");
            }
        }else {
            ToastUtils.showCenterToast(getContext(),"解绑失败");
        }
    }

    @Override
    public void unbindThirdPlatformFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void bindThirdPlatformSuccess(SignBean signBean) {
        if(signBean.getMsgtype().equals("1")){
            ToastUtils.showCenterToast(getContext(),"绑定成功");
            if(mCurrentIndex == 1){
                mTvWechatState.setText("已绑定");
            }else if(mCurrentIndex == 2){
                mTvSinaState.setText("已绑定");
            }else if(mCurrentIndex == 3){
                mTvQqState.setText("已绑定");
            }
        }else {
            ToastUtils.showCenterToast(getContext(),"绑定失败");
        }
    }

    @Override
    public void bindThirdPlatformFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }
}
