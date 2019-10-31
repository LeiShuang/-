package com.zfsoft.zfsoft_product.modules.login.third_phone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.IsLogin;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.modules.login.CountDownTextView;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.PhoneUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/3/19.
 */
public class ThirdPhoneFragment extends BaseFragment implements ThirdPhoneContract.View {

    @Inject
    public ThirdPhoneFragment() {
    }

    @BindView(R.id.et_login_third)
    EditText mEtPhone;
    @BindView(R.id.et_sms_code)
    EditText mEtCode;
    @BindView(R.id.count_down_view)
    CountDownTextView mCountDownTextView;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private String mPhoneNum;

    private String mUserName;
    private String mUserId;
    private String mAvatar;
    private String mType;

    @Inject
    ThirdPhonePresenter mThirdPhonePresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_third_phone;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mUserId = bundle.getString("userId");
        mUserName = bundle.getString("userName");
        mAvatar = bundle.getString("avatar");
        mType = bundle.getString("type");
    }

    @Override
    protected void operateViews(View view) {
        count();
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThirdPhonePresenter.loginByThirdPhone(Config.HSK,mUserId,mUserName,mType,"1","",mPhoneNum,mEtCode.getText().toString().trim(),mAvatar);
            }
        });
    }

    @Override
    public void initPresenter() {
        mThirdPhonePresenter.takeView(this);
    }

    /**
     * 验证码代码
     * */
    private void count() {
        mCountDownTextView
                .setNormalText("获取验证码")
                .setCountDownText("重新获取(", "s)")
                .setCloseKeepCountDown(false)//关闭页面保持倒计时开关
                .setCountDownClickable(false)//倒计时期间点击事件是否生效开关
                .setShowFormatTime(false)//是否格式化时间
                .setIntervalUnit(TimeUnit.SECONDS)
                .setOnCountDownStartListener(new CountDownTextView.OnCountDownStartListener() {
                    @Override
                    public void onStart() {

                    }
                })
                .setOnCountDownTickListener(new CountDownTextView.OnCountDownTickListener() {
                    @Override
                    public void onTick(long untilFinished) {
                        //剩余的时间
                    }
                })
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPhoneNum = mEtPhone.getText().toString().trim();
                        if(PhoneUtils.isMobileNO(mPhoneNum)){
                            mThirdPhonePresenter.getSmsCode(Config.HSK,mPhoneNum,"","Login");
                            mCountDownTextView.startCountDown(60);
                        }else {
                            ToastUtils.showCenterToast(getContext(),"请输入正确格式的手机号");
                        }

                    }
                });


    }

    @Override
    public void getSmsCodeSuccess(SignBean signBean) {
        if(signBean != null && signBean.getMsgtype() != null && signBean.getMsgtype().equals("1")){
            ToastUtils.showCenterToast(getContext(),"验证码已发送，请注意查收");
        }else {
            ToastUtils.showCenterToast(getContext(),"验证码发送失败");
        }
    }

    @Override
    public void getSmsCodeFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void loginByThirdPhoneSuccess(LoginBean loginBean) {
        if(loginBean.getMsgtype().equals("1")){
            String userid = loginBean.getUserid();
            User user = new User();
            user.setUserid(userid);
            user.setNc(loginBean.getNc());
            user.setPhone(mPhoneNum);
            DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
            setUserIsLogin();
            RxActivityTool.skipActivity(getContext(), HomeActivity.class);
        }else if(loginBean.getMsgtype().equals("2")){
            ToastUtils.showCenterToast(getContext(),"登录失败");
        }else if(loginBean.getMsgtype().equals("3")){
            ToastUtils.showCenterToast(getContext(),"账号异常");
        }
    }

    //记住用户登录的状态
    private void setUserIsLogin() {
        IsLogin isLogin = new IsLogin();
        isLogin.setLogin(true);
        DbHelper.saveValueBySharedPreferences(mContext, Config.DB.IS_LOGIN_NAME, Config.DB.IS_LOGIN_KEY, isLogin);
    }

    @Override
    public void loginByThirdPhoneFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }
}
