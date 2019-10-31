package com.zfsoft.zfsoft_product.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingle.widget.LoadingView;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.IsLogin;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.modules.login.third_phone.ThirdPhoneActivity;
import com.zfsoft.zfsoft_product.modules.login.validation.ValidationActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.PhoneUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.HashMap;

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
 * on 2019/1/22.
 */
public class NewLoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener {

    private String mLoginPhone;
    private Platform mWechat;
    private Platform mWeibo;
    private Platform mQQ;

    @Inject
    public NewLoginFragment() {
    }

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.tv_type_sms)
    TextView mTvTypeSms;
    @BindView(R.id.view_sms)
    View mViewSms;
    @BindView(R.id.tv_type_pwd)
    TextView mTvTypePwd;
    @BindView(R.id.view_pwd)
    View mViewPwd;
    @BindView(R.id.et_login)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.btn_third)
    ImageView mBtnThird;
    @BindView(R.id.btn_third_weibo)
    ImageView mBtnThirdWeibo;
    @BindView(R.id.btn_third_wechat)
    ImageView mBtnThirdWechat;

    private String mType = "sms";//登陆方式 验证码sms 密码pwd

    private String mThirdType;//三方登录的类型
    private String mUserId;
    private String mUserName;
    private String mAvatar;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginPresenter.dropView();
    }

    @Override
    protected void initListener() {
        mBtnThirdWeibo.setOnClickListener(this);
        mBtnThird.setOnClickListener(this);
        mBtnThirdWechat.setOnClickListener(this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginPhone = mEtPhone.getText().toString().trim();
                String pwd = mEtPassword.getText().toString();

                if(mType.equals("pwd")){
                    if (judgePhoneValid(mLoginPhone)){
                        mLoginPresenter.loginByPassword(Config.HSK, mLoginPhone,pwd);
                    }

                }else {
                    if (judgePhoneValid(mLoginPhone)){
                        Intent intent = new Intent(getContext(), ValidationActivity.class);
                        intent.putExtra("phone", mLoginPhone);
                        startActivity(intent);

                    }
                }
            }
        });
        mTvTypePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTypePwd.setTextColor(getResources().getColor(R.color.colorWhite));
                mTvTypeSms.setTextColor(getResources().getColor(R.color.login_change_color));
                mViewPwd.setVisibility(View.VISIBLE);
                mViewSms.setVisibility(View.INVISIBLE);
                mType = "pwd";
                mEtPassword.setVisibility(View.VISIBLE);
            }
        });
        mTvTypeSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTypeSms.setTextColor(getResources().getColor(R.color.colorWhite));
                mTvTypePwd.setTextColor(getResources().getColor(R.color.login_change_color));
                mViewPwd.setVisibility(View.INVISIBLE);
                mViewSms.setVisibility(View.VISIBLE);
                mType = "sms";
                mEtPassword.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initPresenter() {
        mLoginPresenter.takeView(this);
    }

    private boolean judgePhoneValid(String phone){
        if(phone.isEmpty()){
            ToastUtils.showCenterToast(getContext(),"手机号不能为空哦");
            return false;
        }
        if(!PhoneUtils.isMobileNO(phone)){
            ToastUtils.showCenterToast(getContext(),"请输入正确的手机号");
            return false;
        }

        return true;
    }



    //记住用户登录的状态
    private void setUserIsLogin() {
        IsLogin isLogin = new IsLogin();
        isLogin.setLogin(true);
        DbHelper.saveValueBySharedPreferences(mContext, Config.DB.IS_LOGIN_NAME, Config.DB.IS_LOGIN_KEY, isLogin);
    }

    @Override
    public void loginByPasswordSuccess(LoginBean loginBean) {
        if(loginBean.getMsgtype().equals("1")){
            String userid = loginBean.getUserid();
            User user = new User();
            user.setUserid(userid);
            user.setNc(loginBean.getNc());
            user.setPhone(mLoginPhone);
            DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
            setUserIsLogin();
            RxActivityTool.skipActivity(getContext(), HomeActivity.class);
        }else if(loginBean.getMsgtype().equals("2")){
            ToastUtils.showCenterToast(getContext(),"登录失败");
        }else if(loginBean.getMsgtype().equals("3")){
            ToastUtils.showCenterToast(getContext(),"账号异常");
        }

    }

    @Override
    public void loginByPasswordFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void loginByPlatformSuccess(LoginBean loginBean) {
        if(loginBean.getMsgtype().equals("1")){//msgtype 1已经注册过
            String userid = loginBean.getUserid();
            User user = new User();
            user.setUserid(userid);
            user.setNc(loginBean.getNc());
            user.setPhone(mLoginPhone);
            DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
            setUserIsLogin();
            RxActivityTool.skipActivity(getContext(), HomeActivity.class);
        }else if(loginBean.getMsgtype().equals("2")){//该手机号未注册，需要跳注册界面
            Intent intent = new Intent(getContext(),ThirdPhoneActivity.class);
            intent.putExtra("userId",mUserId);
            intent.putExtra("userName",mUserName);
            intent.putExtra("avatar",mAvatar);
            intent.putExtra("type",mThirdType);
            startActivity(intent);

        }
    }

    @Override
    public void loginByPlatformFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_third_wechat:
                mWechat = ShareSDK.getPlatform(Wechat.NAME);
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
                        mThirdType = "1";
                        mUserId = userId;
                        mUserName = userName;
                        mAvatar = avatar;

                        mLoginPresenter.loginByThirdPlatform(Config.HSK,"1","",userId,userName,"","1",avatar);


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
                break;
            case R.id.btn_third_weibo:
                mWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                if(mWeibo.isAuthValid()){
                    mWeibo.removeAccount(true);
                }
                mWeibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.d("----", "onComplete: 授权完成");
                        String id = String.valueOf(hashMap.get("id")) ;
                        String name = String.valueOf(hashMap.get("name")) ;
                        String avatar = (String) hashMap.get("avatar_hd");
                        mThirdType = "2";
                        mUserId = id;
                        mUserName = name;
                        mAvatar = avatar;

                        mLoginPresenter.loginByThirdPlatform(Config.HSK,"1","",id,name,"","2",avatar);

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
                break;
            case R.id.btn_third:
                mQQ = ShareSDK.getPlatform(QQ.NAME);
                if(mQQ.isAuthValid()){
                    mQQ.removeAccount(true);
                }
                mQQ.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.d("----", "onComplete: 授权完成");
                        String nickName = String.valueOf(hashMap.get("nickname"));
                        String avatar = String.valueOf(hashMap.get("figureurl_qq"));
                        String userId = platform.getDb().getUserId();
                        mThirdType = "3";
                        mUserId = userId;
                        mUserName = nickName;
                        mAvatar = avatar;

                        mLoginPresenter.loginByThirdPlatform(Config.HSK,"1","",userId,nickName,"","3",avatar);

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
                break;
        }
    }


}