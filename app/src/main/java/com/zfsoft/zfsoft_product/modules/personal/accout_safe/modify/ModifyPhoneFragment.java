package com.zfsoft.zfsoft_product.modules.personal.accout_safe.modify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.modules.login.CountDownTextView;
import com.zfsoft.zfsoft_product.modules.personal.change_pwd.ChangePwdActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/25.
 */
public class ModifyPhoneFragment extends BaseFragment implements ModifyContract.View {

    @Inject
    ModifyPresenter modifyPresenter;

    @Inject
    public ModifyPhoneFragment() {
    }

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

    private String mPhoneNum;
    private int mType;//0修改密码，1修改手机号

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_modify_phone;
    }

    @Override
    protected void initVariables() {
        User userInfo = DbHelper.getUserInfo(mContext);
        mPhoneNum = userInfo.getPhone();
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mType = bundle.getInt("type");
    }

    @Override
    protected void operateViews(View view) {
        count();

        if(mType == 0){
            mTitle.setText("修改密码");
            mEtPhone.setHint("请输入手机号");

        }else {
            mTitle.setText("手机号设置");
            mEtPhone.setHint("请输入旧手机号");
        }
        if(mPhoneNum != null){
            mEtPhone.setText(mPhoneNum);
        }

    }

    @Override
    protected void initListener() {
        mRlGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mType == 0){//修改密码
                    modifyPresenter.confirmSmsCode(Config.HSK,mPhoneNum,DbHelper.getUserId(mContext),"ChangePassword",mEtValidation.getText().toString().trim());
                }else {//修改手机号
                    modifyPresenter.confirmSmsCode(Config.HSK,mPhoneNum,DbHelper.getUserId(mContext),"modifyPhone",mEtValidation.getText().toString().trim());
                }

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
        modifyPresenter.takeView(this);
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
                        if(mType == 0){//修改密码
                            modifyPresenter.getSmsCode(Config.HSK,mPhoneNum,DbHelper.getUserId(mContext),"ChangePassword");
                        }else {//修改手机号
                            modifyPresenter.getSmsCode(Config.HSK,mPhoneNum,DbHelper.getUserId(mContext),"modifyPhone");
                        }
                    }
                })
                .setOnCountDownTickListener(new CountDownTextView.OnCountDownTickListener() {
                    @Override
                    public void onTick(long untilFinished) {
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
            Intent intent = new Intent(getContext(), ChangePwdActivity.class);
            intent.putExtra("type",mType);
            startActivity(intent);
            ToastUtils.showCenterToast(getContext(),"验证成功");
        }else {
            ToastUtils.showCenterToast(getContext(),"验证失败");
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
        if(signBean != null && signBean.getMsgtype() != null && signBean.getMsgtype().equals("1")){
            Intent intent = new Intent(getContext(), ChangePwdActivity.class);
            intent.putExtra("type",mType);
            startActivity(intent);
        }else {
            ToastUtils.showCenterToast(getContext(),"验证失败");
        }

    }

    @Override
    public void confirmSmsCodeFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }
}
