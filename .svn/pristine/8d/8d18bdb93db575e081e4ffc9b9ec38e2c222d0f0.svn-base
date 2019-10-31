package com.zfsoft.zfsoft_product.modules.personal.change_pwd;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.common.Config;
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
 * on 2019/1/18.
 */
public class ChangePwdFragment extends BaseFragment implements ChangePwdContract.View {

    @Inject
    public ChangePwdFragment() {
    }

    @Inject
    ChangePwdPresenter mChangePwdPresenter;

    @BindView(R.id.rl_modify_container)
    RelativeLayout mRlGoBack;//返回
    @BindView(R.id.tv_modify_title)
    TextView mTitle;//标题

    @BindView(R.id.et_input_phone)
    EditText mEtPhone;//手机号
    @BindView(R.id.et_validation)
    EditText mEtValidation;//验证码
    @BindView(R.id.count_down_view)
    CountDownTextView mCountDownTextView;//倒计时

    @BindView(R.id.rl_next)
    RelativeLayout mRlGoNext;//下一步

    @BindView(R.id.ll_tip_container)
    LinearLayout mTipContainer;
    @BindView(R.id.tv_tip_change)
    TextView mTvShowTip;
    @BindView(R.id.tv_gohome)
    TextView mTvGoHome;

    private int mType;//0修改密码，1修改手机号

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_change_pwd;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mType = bundle.getInt("type");
    }

    @Override
    protected void operateViews(View view) {

        if(mType == 0){
            mTitle.setText("修改密码");
            mEtPhone.setHint("请输入新密码");
            mEtValidation.setHint("请确认新密码");
            mEtPhone.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mEtValidation.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mCountDownTextView.setVisibility(View.GONE);
        }else {
            count();
            mTitle.setText("手机号设置");
            mEtPhone.setHint("请输入新手机号");
        }
    }

    @Override
    protected void initListener() {
        mRlGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mType == 0){//修改密码
                    if(mEtPhone.getText().toString().trim().equals(mEtValidation.getText().toString().trim())){
                        mChangePwdPresenter.setPassword(Config.HSK, DbHelper.getUserId(mContext),mEtPhone.getText().toString().trim());
                    }else {
                        ToastUtils.showCenterToast(getContext(),"两次输入的密码不一致！");
                    }
                }else {
                    if(PhoneUtils.isMobileNO(mEtPhone.getText().toString().trim())){
                        mChangePwdPresenter.setPhoneNum(Config.HSK, DbHelper.getUserId(mContext),mEtPhone.getText().toString().trim(),mEtValidation.getText().toString().trim());
                    }else {
                        ToastUtils.showCenterToast(getContext(),"请输入正确的手机号！");
                    }
                }

            }
        });

        mTvGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), HomeActivity.class);
            }
        });

        mRlGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initPresenter() {
        mChangePwdPresenter.takeView(this);
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
                        if(mType == 1){
                            if(PhoneUtils.isMobileNO(mEtPhone.getText().toString().trim())){
                                mChangePwdPresenter.getSmsCode(Config.HSK,mEtPhone.getText().toString().trim(),DbHelper.getUserId(mContext),"ChangePhoneNum");
                            }else {
                                ToastUtils.showCenterToast(getContext(),"请输入正确格式的手机号");
                            }
                        }
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

                        mCountDownTextView.startCountDown(60);
                    }
                });
    }

    @Override
    public void modifySuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if(msgtype.equals("1")){
            if(mType == 1){
                String phone = mEtPhone.getText().toString().trim();
                User userInfo = DbHelper.getUserInfo(mContext);
                userInfo.setPhone(phone);
                DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, userInfo);
            }
            mTipContainer.setVisibility(View.VISIBLE);
        }else {
            ToastUtils.showCenterToast(getContext(),"修改失败");
        }
    }

    @Override
    public void modifyFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
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
    public void confirmSmsCodeSuccess(SignBean signBean) {

    }

    @Override
    public void confirmSmsCodeFailure(String errorMsg) {

    }
}
