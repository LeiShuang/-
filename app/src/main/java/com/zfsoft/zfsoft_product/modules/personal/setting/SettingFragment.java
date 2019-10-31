package com.zfsoft.zfsoft_product.modules.personal.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tencent.bugly.beta.Beta;
import com.vondear.rxtool.RxActivityTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.IsLogin;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.modules.login.LoginActivity;
import com.zfsoft.zfsoft_product.modules.login.info.SetInfoActivity;
import com.zfsoft.zfsoft_product.modules.personal.account_address.AccountAddressActivity;
import com.zfsoft.zfsoft_product.modules.personal.accout_safe.AccountSafeActivity;
import com.zfsoft.zfsoft_product.modules.personal.my_platform.MyPlatformActivity;
import com.zfsoft.zfsoft_product.utils.DbHelper;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/18.
 * 设置界面
 */
public class SettingFragment extends BaseFragment {


    @Inject
    public SettingFragment() {
    }

    @BindView(R.id.rl_checkversion)
    RelativeLayout mRlCheckversion;
    @BindView(R.id.rl_back)
    RelativeLayout mBack;
    @BindView(R.id.rl_account_safe)
    RelativeLayout mRlAccountSafe;//账号安全
    @BindView(R.id.rl_account_address)
    RelativeLayout mRlAccountAddress;//我的收获地址
    @BindView(R.id.rl_account_info)
    RelativeLayout mRlSetPersonalInfo;//个人信息
    @BindView(R.id.rl_account_platform)
    RelativeLayout mRlPlatform;
    @BindView(R.id.rl_account_out)
    RelativeLayout mRlLoginOut;//退出登录

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_setting;
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
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setResult(10);
                getActivity().finish();
            }
        });
        mRlAccountSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), AccountSafeActivity.class);
            }
        });

        mRlPlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), MyPlatformActivity.class);
            }
        });

        mRlAccountAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), AccountAddressActivity.class);
                //CrashReport.testJavaCrash();
            }
        });

        mRlSetPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getContext(), SetInfoActivity.class);
            }
        });

        mRlLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLogin();
            }
        });
        mRlCheckversion.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Beta.checkUpgrade();
                                               }
                                           }
        );
    }

    @Override
    public void initPresenter() {

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

    //退出登录
    private void exitLogin() {
        setLoginIsFalse();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra("from", 1);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //把是否已经登录的标志位设为false
    private void setLoginIsFalse() {
        User user = new User();
        user.setUserid(null);
        user.setNc(null);
        user.setPhone(null);
        DbHelper.saveValueBySharedPreferences(mContext, Config.DB.USER_NAME, Config.DB.USER_KEY, user);
        IsLogin isLogin = new IsLogin();
        isLogin.setLogin(false);
        DbHelper.saveValueBySharedPreferences(mContext, Config.DB.IS_LOGIN_NAME, Config.DB.IS_LOGIN_KEY, isLogin);
    }


}