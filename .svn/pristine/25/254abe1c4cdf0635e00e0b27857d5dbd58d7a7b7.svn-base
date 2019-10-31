package com.zfsoft.zfsoft_product.modules.login.third_phone;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gyf.barlibrary.ImmersionBar;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseActivity;
import com.zfsoft.zfsoft_product.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by ckw
 * on 2019/3/19.
 */
public class ThirdPhoneActivity extends BaseActivity {


    private String mUserName;
    private String mUserId;
    private String mAvatar;
    private String mType;

    @Inject
    ThirdPhoneFragment mThirdPhoneFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mThirdPhoneFragment,R.id.fragment_container);
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mUserId = bundle.getString("userId");
        mUserName = bundle.getString("userName");
        mAvatar = bundle.getString("avatar");
        mType = bundle.getString("type");
        Bundle temp = new Bundle();
        temp.putString("userId",mUserId);
        temp.putString("userName",mUserName);
        temp.putString("avatar",mAvatar);
        temp.putString("type",mType);
        mThirdPhoneFragment.setArguments(temp);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.login_bar_color).init();
    }
}
