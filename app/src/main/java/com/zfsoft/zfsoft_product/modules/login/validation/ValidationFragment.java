package com.zfsoft.zfsoft_product.modules.login.validation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.IsLogin;
import com.zfsoft.zfsoft_product.entity.LoginBean;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.modules.login.CountDownTextView;
import com.zfsoft.zfsoft_product.modules.login.info.SetInfoActivity;
import com.zfsoft.zfsoft_product.modules.login.user_agreement.UserAgreementActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;


import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.inject.Inject;

import butterknife.BindView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by ckw
 * on 2019/1/14.
 */
public class ValidationFragment extends BaseFragment implements ValidationContract.View {


    @Inject
    public ValidationFragment() {
    }

    @Inject
    ValidationPresenter mValidationPresenter;

    @BindView(R.id.tv_send_validation)
    TextView mTvSendValidationTip;//验证码发送提示信息
    @BindView(R.id.et_validation)
    EditText mEtValidation;//输入验证码框
    @BindView(R.id.count_down_view)
    CountDownTextView mCountDownTextView;//倒计时控件
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_tip_agreement)
    TextView mTvTipAgreement;
    @BindView(R.id.tv_tip_agreement_left)
    TextView mTvTipLeft;
    @BindView(R.id.et_validation_pwd)
    EditText mEtPwd;


    private String mPhoneNum;
    private boolean mIsRegister = true;


    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_validation;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mPhoneNum = bundle.getString("phone");
    }

    @Override
    protected void operateViews(View view) {

        mValidationPresenter.confirmRegisterState(Config.HSK,mPhoneNum);
        count();

    }

    @Override
    protected void initListener() {
        mTvTipAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), UserAgreementActivity.class);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsRegister){
                    mValidationPresenter.loginByValidate(Config.HSK,mPhoneNum,mEtValidation.getText().toString().trim());
                }else {
                    //走注册流程
                    mValidationPresenter.register(Config.HSK,mPhoneNum,mEtValidation.getText().toString().trim(),mEtPwd.getText().toString());
                }
            }
        });


    }

    @Override
    public void initPresenter() {
        mValidationPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mValidationPresenter.dropView();
    }

    //记住用户登录的状态
    private void setUserIsLogin() {
        IsLogin isLogin = new IsLogin();
        isLogin.setLogin(true);
        DbHelper.saveValueBySharedPreferences(mContext, Config.DB.IS_LOGIN_NAME, Config.DB.IS_LOGIN_KEY, isLogin);
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
//                        mTvSendValidationTip.setVisibility(View.INVISIBLE);
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mValidationPresenter.getSmsCode(Config.HSK,mPhoneNum,"","Login");
                        mCountDownTextView.startCountDown(60);
                    }
                });


    }


    @Override
    public void loginByValidateSuccess(LoginBean loginBean) {
        String msgtype = loginBean.getMsgtype();
        if(msgtype.equals("1")){
            User user = new User();
            user.setUserid(loginBean.getUserid());
            user.setNc(loginBean.getNc());
            user.setPhone(mPhoneNum);
            DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
            setUserIsLogin();
            ToastUtils.showCenterToast(getContext(),"登录成功");
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }else if(msgtype.equals("2")){
            ToastUtils.showCenterToast(getContext(),"登录失败");
        }else if(msgtype.equals("3")){
            ToastUtils.showCenterToast(getContext(),"账号异常");
        }

    }

    @Override
    public void loginByValidateFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void getSmsCodeSuccess(SignBean signBean) {
        if(signBean != null && signBean.getMsgtype() != null && signBean.getMsgtype().equals("1")){
            ToastUtils.showCenterToast(getContext(),"验证码已发送，请注意查收");
            mTvSendValidationTip.setVisibility(View.VISIBLE);
            mTvSendValidationTip.setText("请输入"+mPhoneNum+"收到的验证码");
        }else {
            mTvSendValidationTip.setVisibility(View.INVISIBLE);
            ToastUtils.showCenterToast(getContext(),"验证码发送失败");
        }
    }

    @Override
    public void getSmsCodeFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void getRegisterStateSuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if(msgtype.equals("1")){
            mIsRegister = true;
        }else if(msgtype.equals("2")){
            //设置下划线
            mIsRegister = false;
            mTvTipAgreement.setVisibility(View.VISIBLE);
            mTvTipAgreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            mTvTipLeft.setVisibility(View.VISIBLE);
            mEtPwd.setVisibility(View.VISIBLE);
        }else{
            mIsRegister = true;
        }
    }

    @Override
    public void getRegisterStateFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void getRegisterSuccess(LoginBean loginBean) {
        String msgtype = loginBean.getMsgtype();
        if(msgtype.equals("1")){
            User user = new User();
            user.setUserid(loginBean.getUserid());
            user.setNc(loginBean.getNc());
            user.setPhone(mPhoneNum);
            DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
            setUserIsLogin();
            ToastUtils.showCenterToast(getContext(),"登录成功");
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }else if(msgtype.equals("2")){
            ToastUtils.showCenterToast(getContext(),"登录失败");
        }else if(msgtype.equals("3")){
            ToastUtils.showCenterToast(getContext(),"账号异常");
        }
    }

    @Override
    public void getRegisterFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

}
