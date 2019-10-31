package com.zfsoft.zfsoft_product.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.modules.login.validation.ValidationActivity;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/12.
 * 已废弃
 */
public class LoginFragment extends BaseFragment {

    @Inject
    public LoginFragment() {
    }

    @BindView(R.id.tv_login_tip)
    TextView mTvLoginTip;//登录字体
    @BindView(R.id.tv_register_tip)
    TextView mTvRegisterTip;//注册字体
    @BindView(R.id.et_login)
    EditText mEtLogin;//输入框
//    @BindView(R.id.et_pwd)
//    EditText mEtPwd;//密码输入框
    @BindView(R.id.btn_go)
    ImageButton mBtnGo;//下一步
//    @BindView(R.id.tv_change_login)
//    TextView mTvChangeLogin;//切换 验证码/密码登录

    private String mType = "login";//登录或者注册 login register



    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_new_login;
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
    protected void initListener() {
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String phone = mEtLogin.getText().toString();
                    if(isMobileNO(phone)){
                        Intent intent = new Intent(getContext(), ValidationActivity.class);
                        intent.putExtra("phone",phone);
                        if(mType.equals("login")){
                            intent.putExtra("type","login");
                        }else {
                            intent.putExtra("type","register");
                        }
                        startActivity(intent);
                    }else {
                        ToastUtils.showCenterToast(getContext(),"请输入正确的手机号");
                    }



            }
        });

        mTvRegisterTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mType.equals("login")){//目前是登录
                    mTvRegisterTip.setText("登录");
                    mTvLoginTip.setText("注册");
                    mType = "register";
                }else {//目前是注册
                    mTvRegisterTip.setText("注册");
                    mTvLoginTip.setText("登录");
                    mType = "login";
                }

            }
        });

    }

    @Override
    public void initPresenter() {

    }

    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */

        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，
        // "[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // \\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8])|(166))\\d{8}$";
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

}